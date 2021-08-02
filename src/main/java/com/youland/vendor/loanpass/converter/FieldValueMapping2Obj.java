// Copyright 2021 YouLand Inc. (youland.com)
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.youland.vendor.loanpass.converter;

import com.google.common.collect.Maps;
import com.youland.lib.core.AnnotationTarget;
import com.youland.lib.core.ReflectionUtil;
import com.youland.lib.core.TagObj;
import com.youland.vendor.loanpass.model.AnyOfFieldValue;
import com.youland.vendor.loanpass.model.FieldValueMapping;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

import lombok.NonNull;

/**
 * The reverse conversion of {@link Obj2FieldValueMapping}
 */
public class FieldValueMapping2Obj {
    private static final Class[] KNOWN_ANNOTATIONS_ORDERED = {
            TagObj.class,
            TagCalc.class,
            TagPriceScr.class,
            TagDurationPriceScr.class,
            Tag.class,
            TagDuration.class
    };

    private static String getFieldId(Annotation annotation) {
        String fieldId;

        if (annotation instanceof TagCalc)
            fieldId = ((TagCalc) annotation).value().getValue();
        else if (annotation instanceof TagPriceScr)
            fieldId = ((TagPriceScr) annotation).value().getValue();
        else if (annotation instanceof TagDurationPriceScr)
            fieldId = ((TagDurationPriceScr) annotation).value().getValue();
        else if (annotation instanceof Tag)
            fieldId = ((Tag) annotation).value().getValue();
        else if (annotation instanceof TagDuration)
            fieldId = ((TagDuration) annotation).value().getValue();
        else
            throw new NotImplementedException(String.format("%s", annotation));

        return fieldId;
    }

    private final List<AnnotationTarget> targets;

    public int getTargetCount() {return targets.size();}

    public FieldValueMapping2Obj(@NonNull Object source) {
        targets = ReflectionUtil.buildTargets(source
                , KNOWN_ANNOTATIONS_ORDERED
                , false);
    }

    private static <T extends FieldValueMapping> void updateTarget(AnnotationTarget target, T field) {
        AnyOfFieldValue fieldValue = field.getValue();
        Object value = AnyOfFieldValue.getFieldValue(fieldValue);
        try {
            FieldUtils.writeField(target.getField(), target.getObj(), value, true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T extends FieldValueMapping> int convert(@NonNull Iterable<T> fields) {
        Map<String, T> fieldById = Maps.uniqueIndex(fields, T::getFieldId);
        return convert(fieldById);
    }

    public <T extends FieldValueMapping> int convert(@NonNull Map<String, T> fieldById) {
        int countUpdate = 0;
        for (AnnotationTarget target : targets) {
            String fieldId = getFieldId(target.getAnnotation());
            T field = fieldById.get(fieldId);
            if (field == null)
                continue;

            updateTarget(target, field);
            countUpdate++;
        }
        return countUpdate;
    }

    public static <TResult, T extends FieldValueMapping> TResult create(@NonNull Iterable<T> fields
            , @NonNull Class<TResult> targetClass) {
        Map<String, T> fieldById = Maps.uniqueIndex(fields, T::getFieldId);
        return create(fieldById, targetClass);
    }

    public static <TResult, T extends FieldValueMapping> TResult create(@NonNull Map<String, T> fieldById
            , @NonNull Class<TResult> targetClass) {
        TResult obj = ReflectionUtil.create(targetClass);
        FieldValueMapping2Obj converter = new FieldValueMapping2Obj(obj);
        converter.convert(fieldById);
        return obj;
    }
}

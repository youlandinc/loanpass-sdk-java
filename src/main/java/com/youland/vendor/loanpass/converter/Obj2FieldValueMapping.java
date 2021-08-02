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

import com.youland.lib.core.AnnotationTarget;
import com.youland.lib.core.ReflectionUtil;
import com.youland.lib.core.TagObj;
import com.youland.vendor.loanpass.generated.KnownFieldId;
import com.youland.vendor.loanpass.model.FieldValueMapping;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.annotation.Annotation;
import java.util.List;

import lombok.NonNull;

/**
 * To convert a source object with fields containing annotation type of
 * {@link Tag}, {@link TagDuration} and {@link TagObj}
 * to a list of {@link FieldValueMapping}
 */
public class Obj2FieldValueMapping {

    private static final Class[] KNOWN_ANNOTATIONS_ORDERED = {
            TagObj.class,
            Tag.class,
            TagDuration.class,
    };

    private final List<AnnotationTarget> targets;

    public Obj2FieldValueMapping(@NonNull Object source) {
        targets = ReflectionUtil.buildTargets(source, KNOWN_ANNOTATIONS_ORDERED, true);
    }

    private static KnownFieldId getKnownFieldId(Annotation annotation) {
        KnownFieldId fieldId;
        Tag tag;
        TagDuration tagDuration;
        if (annotation instanceof Tag) {
            tag = (Tag) annotation;
            fieldId = tag.value();
        } else if (annotation instanceof TagDuration) {
            tagDuration = (TagDuration) annotation;
            fieldId = tagDuration.value();
        }
        else
            throw new NotImplementedException(String.format("%s", annotation));

        return fieldId;
    }

    private static void addField(AnnotationTarget target, FieldValueMapping.IBuilder builder) {
        Object value = null;
        try {
            value = FieldUtils.readField(target.getField(), target.getObj(), true);
            if (value == null)
                return;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Object extra = null;
        if (target.getAnnotation() instanceof TagDuration)
            extra = ((TagDuration) target.getAnnotation()).unit();

        KnownFieldId fieldId = getKnownFieldId(target.getAnnotation());
        builder.field(fieldId, value, extra);
    }

    /**
     * Convert an object (nested objects included) with annotations to a List<FieldValueMapping>
     * @return List<FieldValueMapping>
     */
    public List<FieldValueMapping> convert() {
        FieldValueMapping.IBuilder fieldBuilder = FieldValueMapping.builder();

        for (AnnotationTarget target : targets) {
            addField(target, fieldBuilder);
        }

        List<FieldValueMapping> result = fieldBuilder.build();
        return result;
    }
}

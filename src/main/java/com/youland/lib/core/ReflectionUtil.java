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

package com.youland.lib.core;

import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import lombok.NonNull;

public class ReflectionUtil {
    public static <T> T create(final Class<T> classToCreate) {
        final Constructor<T> constructor;
        try {
            constructor = classToCreate.getDeclaredConstructor();
            final T result = constructor.newInstance();
            return result;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Annotation getAnnotation(Field field, final Class[] knownAnnotations) {
        for (Class aClass : knownAnnotations) {
            Annotation annotation = field.getAnnotation(aClass);
            if (annotation != null)
                return annotation;
        }
        return null;
    }

    /**
     * Build targeted fields that were annotated for reflection
     * @param sourceTop
     * @param knownAnnotations
     * @param bIgnoreFieldValueNull
     * @return
     */
    public static List<AnnotationTarget> buildTargets(@NonNull Object sourceTop
            , @NonNull final Class[] knownAnnotations
            , boolean bIgnoreFieldValueNull) {

        assert knownAnnotations.length > 0 : String.format("knownAnnotations is empty!");

        Deque<Object> stack = new ArrayDeque<>();
        stack.add(sourceTop);

        List<AnnotationTarget> result = new ArrayList<>();
        try {
            while (!stack.isEmpty()) {
                Object source = stack.pop();
                if (source == null)
                    continue;

                Field[] fields = source.getClass().getDeclaredFields();
                for (Field field : fields) {
                    Annotation annotation = getAnnotation(field, knownAnnotations);
                    if (annotation == null)
                        continue;

                    Object value = FieldUtils.readField(field, source, true);
                    if (bIgnoreFieldValueNull && value == null)
                        continue;

                    if (annotation instanceof TagObj) {
                        if (value != null) //ltang: Nested object is null
                            stack.add(value);
                    }
                    else {
                        AnnotationTarget target = new AnnotationTarget(source, field, annotation);
                        result.add(target);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (result.isEmpty())
            throw new IllegalArgumentException
                    (String.format("No annotation in the list of %s was found in %s"
                            , Arrays.toString(knownAnnotations), result.getClass()));

        return result;
    }
}

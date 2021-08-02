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

package com.youland.vendor.loanpass.model.field;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.youland.lib.core.serializer.ValueObservable;
import com.youland.lib.core.serializer.ValueObservableSerializer;

import java.util.Map;

import lombok.Getter;
import lombok.ToString;

/**
 * FieldValueTypeOpt exclusively returned by Get Configurations
 * Identical to {@link FieldValueOpt}
 */
@Getter
@ToString
@JsonSerialize(using = ValueObservableSerializer.class)
public enum FieldValueTypeOpt implements ValueObservable<String> {
    ENUM ("enum"),
    STRING ("string"),
    NUMBER ("number"),
    DURATION ("duration");

    public static final Map<String, FieldValueTypeOpt> VALUE2ENUM
            = ValueObservable.createMapValue2Obj(FieldValueTypeOpt.values());

    private final String value;

    FieldValueTypeOpt(String value) {
        this.value = value;
    }

    public void assertSame(FieldValueTypeOpt actual, String context) {
        assert this == actual
                : String.format("%s Expected type of %s vs. Actual type of %s"
                    , context
                    , this.name()
                    , actual.name());
    }
}

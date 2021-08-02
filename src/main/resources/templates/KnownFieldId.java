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

package gencode@package;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Map;

import lombok.Getter;
import lombok.ToString;

import com.youland.lib.core.serializer.ValueObservable;
import com.youland.lib.core.serializer.ValueObservableSerializer;
import com.youland.vendor.loanpass.model.field.FieldValueTypeOpt;

import static com.youland.vendor.loanpass.model.field.FieldValueTypeOpt.ENUM;
import static com.youland.vendor.loanpass.model.field.FieldValueTypeOpt.NUMBER;
import static com.youland.vendor.loanpass.model.field.FieldValueTypeOpt.STRING;
import static com.youland.vendor.loanpass.model.field.FieldValueTypeOpt.DURATION;

@Getter
@ToString
@JsonSerialize(using = ValueObservableSerializer.class)
@javax.annotation.Generated(value="gencode@gen-tool", date="gencode@timestamp")
/**
 * Generated code from LoanPass API for static checking
 */
public enum gencode@class implements ValueObservable<String> {
    gencode@enum

    public static final Map<String, gencode@class> VALUE2ENUM
            = ValueObservable.createMapValue2Obj(gencode@class.values());

    private final String value;

    private final FieldValueTypeOpt fieldValueType;

    private final String fieldValueRef;
    public boolean hasFieldValueRef() {return fieldValueRef != null; }

    gencode@class(String value
                    , FieldValueTypeOpt fieldValueType) {
        this(value, fieldValueType, null);
    }

    gencode@class(String value
                    , FieldValueTypeOpt fieldValueType
                    , String fieldValueRef) {
        this.value = value;
        this.fieldValueType = fieldValueType;
        this.fieldValueRef = fieldValueRef;
    }
}

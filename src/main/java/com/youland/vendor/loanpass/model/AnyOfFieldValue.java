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

package com.youland.vendor.loanpass.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.youland.lib.core.ReflectionUtil;
import com.youland.vendor.loanpass.model.field.FieldValueAsDuration;
import com.youland.vendor.loanpass.model.field.FieldValueAsEnum;
import com.youland.vendor.loanpass.model.field.FieldValueAsNumber;
import com.youland.vendor.loanpass.model.field.FieldValueAsString;
import com.youland.vendor.loanpass.model.field.FieldValueOpt;
import com.youland.vendor.loanpass.serializer.AnyOfFieldValueDeserializer;
import com.youland.vendor.loanpass.util.JavaGenCommon;

import org.apache.commons.lang3.NotImplementedException;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

@Data
@JsonDeserialize(using = AnyOfFieldValueDeserializer.class)
public abstract class AnyOfFieldValue {
    public static final String FIELD_NAME_TYPE = "type";

    @Setter(AccessLevel.NONE)
    private FieldValueOpt type = FieldValueOpt.ENUM;

    @SuppressWarnings("unused")
    protected AnyOfFieldValue() { }
    protected AnyOfFieldValue(FieldValueOpt type) { this.type = type; }

    public abstract AnyOfFieldValue fromJson(@NonNull JsonNode node);

    public static FieldValueOpt getTypeFromJson(@NonNull JsonNode node)  {
        String typeAsSt = node.get(FIELD_NAME_TYPE).asText();
        FieldValueOpt type = FieldValueOpt.VALUE2ENUM.get(typeAsSt);
        assert type != null : String.format("Invalid enum value: '%s'", typeAsSt);
        return type;
    }

    public static Object getFieldValue(AnyOfFieldValue field) {
        if (field == null)
            return null;

        Object value = null;
        FieldValueOpt type = field.getType();
        switch (type) {
            case NUMBER:
                FieldValueAsNumber numberField = (FieldValueAsNumber) field;
                value = numberField.getValue();
                break;
            case STRING:
                FieldValueAsString textField = (FieldValueAsString) field;
                value = textField.getValue();
                break;
            case ENUM:
                FieldValueAsEnum enumField = (FieldValueAsEnum) field;
                String variantId = enumField.getVariantId();
                value = JavaGenCommon.toJavaVarName(variantId);
                break;
            case DURATION:
                FieldValueAsDuration duraField = (FieldValueAsDuration) field;
                value = duraField.getCount();
                break;
            default:
                throw new NotImplementedException(String.format("Unsupported type '%s'", type));
        }
        return value;
    }
}

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

package com.youland.vendor.loanpass.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.youland.vendor.loanpass.model.AnyOfFieldValueType;
import com.youland.vendor.loanpass.model.field.FieldValueTypeAsDuration;
import com.youland.vendor.loanpass.model.field.FieldValueTypeAsEnum;
import com.youland.vendor.loanpass.model.field.FieldValueTypeAsNumber;
import com.youland.vendor.loanpass.model.field.FieldValueTypeAsString;
import com.youland.vendor.loanpass.model.field.FieldValueTypeOpt;

import java.io.IOException;

public class AnyOfFieldValueTypeDeserializer extends StdDeserializer<AnyOfFieldValueType> {

    @SuppressWarnings("unused")
    public AnyOfFieldValueTypeDeserializer() {
        this(null);
    }

    public AnyOfFieldValueTypeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public AnyOfFieldValueType deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        FieldValueTypeOpt type = AnyOfFieldValueType.getTypeFromJson(node);
        AnyOfFieldValueType obj;
        switch (type) {
            case ENUM:
                obj = new FieldValueTypeAsEnum().fromJson(node);
                break;
            case STRING:
                obj = new FieldValueTypeAsString().fromJson(node);
                break;
            case NUMBER:
                obj = new FieldValueTypeAsNumber().fromJson(node);
                break;
            case DURATION:
                obj = new FieldValueTypeAsDuration().fromJson(node);
                break;
            default:
                throw new IllegalArgumentException(String.format("Failed to deserialize type of '%s'", type));
        }
        return obj;
    }
}
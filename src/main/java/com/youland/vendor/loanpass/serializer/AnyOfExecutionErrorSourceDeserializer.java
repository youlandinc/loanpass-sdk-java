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
import com.youland.vendor.loanpass.model.AnyOfExecutionErrorSource;
import com.youland.vendor.loanpass.model.error.ExecutionErrorSourceAsCalculatedField;
import com.youland.vendor.loanpass.model.error.ExecutionErrorSourceAsDataTableLookup;
import com.youland.vendor.loanpass.model.error.ExecutionErrorSourceAsPricingCalculation;
import com.youland.vendor.loanpass.model.error.ExecutionErrorSourceAsRule;
import com.youland.vendor.loanpass.model.error.ExecutionErrorSourceOpt;

import java.io.IOException;

public class AnyOfExecutionErrorSourceDeserializer extends StdDeserializer<AnyOfExecutionErrorSource> {

    @SuppressWarnings("unused")
    public AnyOfExecutionErrorSourceDeserializer() {
        this(null);
    }

    public AnyOfExecutionErrorSourceDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public AnyOfExecutionErrorSource deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        ExecutionErrorSourceOpt type = AnyOfExecutionErrorSource.getTypeFromJson(node);
        AnyOfExecutionErrorSource obj;
        switch (type) {
            case RULE:
                obj = new ExecutionErrorSourceAsRule().fromJson(node);
                break;
            case CALCULATED_FIELD:
                obj = new ExecutionErrorSourceAsCalculatedField().fromJson(node);
                break;
            case DATA_TABLE_LOOKUP:
                obj = new ExecutionErrorSourceAsDataTableLookup().fromJson(node);
                break;
            case PRICING_CALCULATION:
                obj = new ExecutionErrorSourceAsPricingCalculation().fromJson(node);
                break;
            default:
                throw new IllegalArgumentException(String.format("Failed to deserialize type of '%s'", type));
        }
        return obj;
    }
}
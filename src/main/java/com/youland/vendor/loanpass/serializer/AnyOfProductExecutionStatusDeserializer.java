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
import com.youland.vendor.loanpass.model.AnyOfProductExecutionStatus;
import com.youland.vendor.loanpass.model.product.ProductExecutionStatusAsError;
import com.youland.vendor.loanpass.model.product.ProductExecutionStatusAsNoPricing;
import com.youland.vendor.loanpass.model.product.ProductExecutionStatusAsOk;
import com.youland.vendor.loanpass.model.product.ProductExecutionStatusAsRejected;
import com.youland.vendor.loanpass.model.product.ProductExecutionStatusOpt;

import java.io.IOException;

public class AnyOfProductExecutionStatusDeserializer extends StdDeserializer<AnyOfProductExecutionStatus> {

    @SuppressWarnings("unused")
    public AnyOfProductExecutionStatusDeserializer() {
        this(null);
    }

    public AnyOfProductExecutionStatusDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public AnyOfProductExecutionStatus deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        ProductExecutionStatusOpt type = AnyOfProductExecutionStatus.getTypeFromJson(node);
        AnyOfProductExecutionStatus obj;
        switch (type) {
            case NO_PRICING:
                obj = new ProductExecutionStatusAsNoPricing().fromJson(node);
                break;
            case ERROR:
                obj = new ProductExecutionStatusAsError().fromJson(node);
                break;
            case REJECTED:
                obj = new ProductExecutionStatusAsRejected().fromJson(node);
                break;
            case OK:
                obj = new ProductExecutionStatusAsOk().fromJson(node);
                break;
            default:
                throw new IllegalArgumentException(String.format("Failed to deserialize type of '%s'", type));
        }
        return obj;
    }
}
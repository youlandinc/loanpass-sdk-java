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
import com.youland.vendor.loanpass.model.AnyOfPriceScenarioStatus;
import com.youland.vendor.loanpass.model.price.PriceScenarioStatusAsApproved;
import com.youland.vendor.loanpass.model.price.PriceScenarioStatusAsError;
import com.youland.vendor.loanpass.model.price.PriceScenarioStatusAsMissingConfig;
import com.youland.vendor.loanpass.model.price.PriceScenarioStatusAsRejected;
import com.youland.vendor.loanpass.model.price.PriceScenarioStatusOpt;

import java.io.IOException;

public class AnyOfPriceScenarioStatusDeserializer extends StdDeserializer<AnyOfPriceScenarioStatus> {

    @SuppressWarnings("unused")
    public AnyOfPriceScenarioStatusDeserializer() {
        this(null);
    }

    public AnyOfPriceScenarioStatusDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public AnyOfPriceScenarioStatus deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        PriceScenarioStatusOpt type = AnyOfPriceScenarioStatus.getTypeFromJson(node);
        AnyOfPriceScenarioStatus obj;
        switch (type) {
            case ERROR:
                obj = new PriceScenarioStatusAsError().fromJson(node);
                break;
            case REJECTED:
                obj = new PriceScenarioStatusAsRejected().fromJson(node);
                break;
            case APPROVED:
                obj = new PriceScenarioStatusAsApproved().fromJson(node);
                break;
            case MISSING_CONFIGURATION:
                obj = new PriceScenarioStatusAsMissingConfig().fromJson(node);
                break;
            default:
                throw new IllegalArgumentException(String.format("Failed to deserialize type of '%s'", type));
        }
        return obj;
    }
}

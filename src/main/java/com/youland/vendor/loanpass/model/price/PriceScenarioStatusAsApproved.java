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

package com.youland.vendor.loanpass.model.price;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youland.vendor.loanpass.model.AnyOfPriceScenarioStatus;
import com.youland.vendor.loanpass.model.MarginAdjustmentValue;
import com.youland.vendor.loanpass.model.PriceAdjustmentValue;
import com.youland.vendor.loanpass.model.RateAdjustmentValue;
import com.youland.vendor.loanpass.model.StipulationValue;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NonNull;

@Data
public class PriceScenarioStatusAsApproved extends AnyOfPriceScenarioStatus {

    public static final String FIELD_NAME_PRICEADJUSTMENTS = "priceAdjustments";
    public static final String FIELD_NAME_MARGINADJUSTMENTS = "marginAdjustments";
    public static final String FIELD_NAME_RATEADJUSTMENTS = "rateAdjustments";
    public static final String FIELD_NAME_STIPULATIONS = "stipulations";

    private List<PriceAdjustmentValue> priceAdjustments;
    private List<MarginAdjustmentValue> marginAdjustments;
    private List<RateAdjustmentValue> rateAdjustments;
    private List<StipulationValue> stipulations;

    public PriceScenarioStatusAsApproved() { super(PriceScenarioStatusOpt.APPROVED); }

    public PriceScenarioStatusAsApproved fromJson(@NonNull JsonNode node)  {
        JsonNode subNode;

        ObjectMapper mapper = new ObjectMapper();

        subNode = node.get(FIELD_NAME_PRICEADJUSTMENTS);
        priceAdjustments = mapper.convertValue(subNode, new TypeReference<>() {});

        subNode = node.get(FIELD_NAME_MARGINADJUSTMENTS);
        marginAdjustments = mapper.convertValue(subNode, new TypeReference<>() {});

        subNode = node.get(FIELD_NAME_RATEADJUSTMENTS);
        rateAdjustments = mapper.convertValue(subNode, new TypeReference<>() {});

        subNode = node.get(FIELD_NAME_STIPULATIONS);
        stipulations = mapper.convertValue(subNode, new TypeReference<>() {});

        return this;
    }
}

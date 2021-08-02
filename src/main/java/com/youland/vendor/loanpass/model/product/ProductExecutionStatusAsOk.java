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

package com.youland.vendor.loanpass.model.product;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youland.vendor.loanpass.model.AnyOfProductExecutionStatus;
import com.youland.vendor.loanpass.model.PriceScenarioResult;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NonNull;

@Data
public class ProductExecutionStatusAsOk extends AnyOfProductExecutionStatus {
    public static final String FIELD_NAME_RATESHEETEFFECTIVETIMESTAMP = "rateSheetEffectiveTimestamp";
    public static final String FIELD_NAME_PRICESCENARIOS = "priceScenarios";

    private String rateSheetEffectiveTimestamp;
    private List<PriceScenarioResult> priceScenarios;

    public ProductExecutionStatusAsOk() { super(ProductExecutionStatusOpt.OK); }

    public ProductExecutionStatusAsOk fromJson(@NonNull JsonNode node)  {
        this.rateSheetEffectiveTimestamp = (node.get(FIELD_NAME_RATESHEETEFFECTIVETIMESTAMP)).asText();

        JsonNode jsonNodeArray = node.get(FIELD_NAME_PRICESCENARIOS);
        ObjectMapper mapper = new ObjectMapper();
        priceScenarios = mapper.convertValue(jsonNodeArray, new TypeReference<>() {});
        return this;
    }
}

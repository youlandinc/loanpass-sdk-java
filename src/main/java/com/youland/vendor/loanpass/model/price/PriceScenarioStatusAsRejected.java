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
import com.youland.vendor.loanpass.model.ExecutionError;
import com.youland.vendor.loanpass.model.Rejection;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NonNull;

@Data
public class PriceScenarioStatusAsRejected extends AnyOfPriceScenarioStatus {

    public static final String FIELD_NAME_REJECTIONS = "rejections";
    public static final String FIELD_NAME_ERRORS = "errors";

    private List<Rejection> rejections;
    private List<ExecutionError> errors;

    public PriceScenarioStatusAsRejected() { super(PriceScenarioStatusOpt.REJECTED); }

    public PriceScenarioStatusAsRejected fromJson(@NonNull JsonNode node)  {
        JsonNode jsonNodeArray;

        ObjectMapper mapper = new ObjectMapper();

        jsonNodeArray = node.get(FIELD_NAME_REJECTIONS);
        rejections = mapper.convertValue(jsonNodeArray, new TypeReference<>() {});

        jsonNodeArray = node.get(FIELD_NAME_ERRORS);
        errors = mapper.convertValue(jsonNodeArray, new TypeReference<>() {});
        return this;
    }
}

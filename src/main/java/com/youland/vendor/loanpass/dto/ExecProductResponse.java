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

package com.youland.vendor.loanpass.dto;

import com.youland.vendor.loanpass.model.AnyOfProductExecutionStatus;
import com.youland.vendor.loanpass.model.FieldValueMapping;
import com.youland.vendor.loanpass.model.PriceScenarioResult;
import com.youland.vendor.loanpass.model.product.ProductExecutionStatusAsOk;

import java.util.List;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

/**
 * https://api.loanpass.io/v1/swagger#/All%20Endpoints/execute_product
 */
@Data
@ToString(callSuper = true)
public class ExecProductResponse extends ReqResBase {
    private String productId;
    private String productName;
    private String productCode;
    private String investorName;
    private String investorCode;
    private Boolean isPricingEnabled;
    private List<FieldValueMapping> productFields;
    private List<FieldValueMapping> calculatedFields;
    private AnyOfProductExecutionStatus status;

    public List<PriceScenarioResult> getPriceScenarios() {
        if (status instanceof ProductExecutionStatusAsOk) {
            ProductExecutionStatusAsOk okStatus = (ProductExecutionStatusAsOk) status;
            return okStatus.getPriceScenarios();
        }
        return null;
    }
}

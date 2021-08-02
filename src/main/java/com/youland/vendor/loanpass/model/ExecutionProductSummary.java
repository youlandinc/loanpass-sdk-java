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

import com.youland.vendor.loanpass.model.product.ProductSummaryStatusAsApproved;

import java.util.List;

import lombok.Data;

@Data
public class ExecutionProductSummary {
    private String productId;
    private String productName;
    private String productCode;
    private String investorName;
    private String investorCode;
    private Boolean isPricingEnabled;

    private AnyOfProductSummaryStatus status;

    public List<PriceScenarioSummary> getPriceScenarios() {
        if (status instanceof ProductSummaryStatusAsApproved)
        {
            ProductSummaryStatusAsApproved statusApproved = (ProductSummaryStatusAsApproved) status;
            return statusApproved.getPriceScenarios();
        }
        return null;
    }
}

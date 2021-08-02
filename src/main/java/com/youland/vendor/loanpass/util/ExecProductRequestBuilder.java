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

package com.youland.vendor.loanpass.util;

import com.youland.vendor.loanpass.dto.ExecProductRequest;
import com.youland.vendor.loanpass.dto.ExecProductRequest.IBuilder;
import com.youland.vendor.loanpass.dto.ExecSummaryRequest;
import com.youland.vendor.loanpass.model.FieldValueMapping;

import java.time.Instant;
import java.util.List;

import lombok.NonNull;

/**
 * Default builder
 */
public class ExecProductRequestBuilder implements ExecProductRequest.IBuilder {
    private final ExecSummaryRequest.IBuilder builderBase = ExecSummaryRequest.builder();

    private String productId;

    @Override
    public IBuilder withTime(@NonNull Instant instant) {
        builderBase.withTime(instant);
        return this;
    }

    @Override
    public IBuilder withField(@NonNull FieldValueMapping field) {
        builderBase.withField(field);
        return this;
    }

    @Override
    public IBuilder withFields(@NonNull List<FieldValueMapping> fields) {
        builderBase.withFields(fields);
        return this;
    }

    @Override
    public IBuilder withProdId(@NonNull String prodId) {
        productId = prodId;
        return this;
    }

    @Override
    public ExecProductRequest build() {
        ExecSummaryRequest requestBase = builderBase.build();
        ExecProductRequest request = new ExecProductRequest(productId, requestBase);
        return request;
    }
}

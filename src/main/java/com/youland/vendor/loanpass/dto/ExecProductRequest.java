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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.youland.vendor.loanpass.model.FieldValueMapping;
import com.youland.vendor.loanpass.util.ExecProductRequestBuilder;

import java.time.Instant;
import java.util.List;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@JsonPropertyOrder({"productId"})
public class ExecProductRequest extends ExecSummaryRequest {
    @JsonProperty(required = true)
    private String productId;

    public ExecProductRequest() {}

    public ExecProductRequest(@NonNull String prodId
            , @NonNull Instant time
            , @NonNull List<FieldValueMapping> fields) {
        super(time, fields);
        productId = prodId;
    }

    public ExecProductRequest(@NonNull String prodId
            , @NonNull ExecSummaryRequest request) {
        this(prodId, request.getTime(), request.getCreditApplicationFields());
    };

    public static IBuilder builder() {
        return new ExecProductRequestBuilder();
    }

    public interface IBuilder extends ExecSummaryRequest.IBuilder {
        IBuilder withTime(Instant instant);
        IBuilder withField(FieldValueMapping field);
        IBuilder withFields(List<FieldValueMapping> fields);

        IBuilder withProdId(String prodId);
        ExecProductRequest build();
    }
}

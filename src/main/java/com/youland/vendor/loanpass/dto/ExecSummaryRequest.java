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
import com.youland.vendor.loanpass.model.FieldValueMapping;
import com.youland.vendor.loanpass.util.ExecSummaryRequestBuilder;
import com.youland.vendor.loanpass.util.FieldValueMappingBuilder;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class ExecSummaryRequest extends ReqResBase {
    @JsonProperty(required = true)
    private List<FieldValueMapping> creditApplicationFields = new ArrayList<>();

    public ExecSummaryRequest() {}

    public ExecSummaryRequest(@NonNull Instant time, @NonNull List<FieldValueMapping> fields) {
        super(time);
        creditApplicationFields = fields;
    }

    public static FieldValueMapping.IBuilder fieldsBuilder() {
        return new FieldValueMappingBuilder();
    }

    public static IBuilder builder() {
        return new ExecSummaryRequestBuilder();
    }

    public interface IBuilder {
        IBuilder withTime(Instant instant);
        IBuilder withField(FieldValueMapping field);
        IBuilder withFields(List<FieldValueMapping> fields);
        ExecSummaryRequest build();
    }
}

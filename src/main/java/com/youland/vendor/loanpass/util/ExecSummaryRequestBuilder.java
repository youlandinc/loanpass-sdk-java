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

import com.youland.vendor.loanpass.dto.ExecSummaryRequest;
import com.youland.vendor.loanpass.dto.ExecSummaryRequest.IBuilder;
import com.youland.vendor.loanpass.model.FieldValueMapping;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NonNull;

/**
 * Default builder
 */
public class ExecSummaryRequestBuilder implements ExecSummaryRequest.IBuilder {
    @Getter
    private Instant time = Instant.now();

    @Getter
    private List<FieldValueMapping> fields = new ArrayList<>();

    @Override
    public IBuilder withTime(@NonNull Instant instant) {
        time = instant;
        return this;
    }

    @Override
    public IBuilder withField(@NonNull FieldValueMapping field) {
        this.fields.add(field);
        return this;
    }

    @Override
    public IBuilder withFields(@NonNull List<FieldValueMapping> fields) {
        this.fields.addAll(fields);
        return this;
    }

    @Override
    public ExecSummaryRequest build() {
        Instant timeCopy = Instant.from(this.time);
        List<FieldValueMapping> fieldsCopy = new ArrayList<>(fields);

        ExecSummaryRequest request = new ExecSummaryRequest(timeCopy, fieldsCopy);
        return request;
    }
}

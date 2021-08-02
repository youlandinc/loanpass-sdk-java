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

package com.youland.vendor.loanpass.model.field;

import com.fasterxml.jackson.databind.JsonNode;
import com.youland.vendor.loanpass.model.AnyOfFieldValueType;
import com.youland.vendor.loanpass.model.StringFieldFormat;

import lombok.Data;
import lombok.NonNull;

@Data
public class FieldValueTypeAsDuration extends AnyOfFieldValueType {
    public static final String FIELD_NAME_MINIMUMDAYS = "minimumDays";
    public static final String FIELD_NAME_MAXIMUMDAYS = "maximumDays";
    public static final String FIELD_NAME_MINIMUMMONTHS = "minimumMonths";
    public static final String FIELD_NAME_MAXIMUMMONTHS = "maximumMonths";

    private Double minimumDays;
    private Double maximumDays;
    private Double minimumMonths;
    private Double maximumMonths;

    public FieldValueTypeAsDuration() { super(FieldValueTypeOpt.DURATION); }

    public FieldValueTypeAsDuration fromJson(@NonNull JsonNode node)  {
        minimumDays = (node.get(FIELD_NAME_MINIMUMDAYS)).asDouble();
        maximumDays = (node.get(FIELD_NAME_MAXIMUMDAYS)).asDouble();
        minimumMonths = (node.get(FIELD_NAME_MINIMUMMONTHS)).asDouble();
        maximumMonths = (node.get(FIELD_NAME_MAXIMUMMONTHS)).asDouble();
        return this;
    }
}

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
import com.youland.vendor.loanpass.model.NumberFieldStyle;
import com.youland.vendor.loanpass.model.StringFieldFormat;

import lombok.Data;
import lombok.NonNull;

@Data
public class FieldValueTypeAsNumber extends AnyOfFieldValueType {
    public static final String FIELD_NAME_MINIMUM = "minimum";
    public static final String FIELD_NAME_MAXIMUM = "maximum";
    public static final String FIELD_NAME_PRECISION = "precision";
    public static final String FIELD_NAME_STYLE = "style";

    private Double minimum;
    private Double maximum;
    private Integer precision;

    private NumberFieldStyle style;

    public FieldValueTypeAsNumber() { super(FieldValueTypeOpt.NUMBER); }

    public FieldValueTypeAsNumber fromJson(@NonNull JsonNode node)  {
        minimum = (node.get(FIELD_NAME_MINIMUM)).asDouble();
        maximum = (node.get(FIELD_NAME_MAXIMUM)).asDouble();
        precision = (node.get(FIELD_NAME_PRECISION)).asInt();

        String styleAsSt = (node.get(FIELD_NAME_STYLE)).asText();
        this.style =  NumberFieldStyle.VALUE2ENUM.get(styleAsSt);
        return this;
    }
}

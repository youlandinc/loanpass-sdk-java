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
import com.youland.vendor.loanpass.model.AnyOfFieldValue;

import lombok.Data;
import lombok.NonNull;

@Data
public class FieldValueAsEnum extends AnyOfFieldValue {
    public static final String FIELD_NAME_ENUMTYPEID = "enumTypeId";
    public static final String FIELD_NAME_VARIANTID = "variantId";

    private String enumTypeId;
    private String variantId;

    public FieldValueAsEnum() {
        super(FieldValueOpt.ENUM);
    }

    public FieldValueAsEnum(@NonNull String enumTypeId, @NonNull String variantId) {
        this();
        this.enumTypeId = enumTypeId;
        this.variantId = variantId;
    }

    @Override
    public FieldValueAsEnum fromJson(@NonNull JsonNode node)  {
        this.enumTypeId = (node.get(FIELD_NAME_ENUMTYPEID)).asText();
        this.variantId = (node.get(FIELD_NAME_VARIANTID)).asText();
        return this;
    }
}

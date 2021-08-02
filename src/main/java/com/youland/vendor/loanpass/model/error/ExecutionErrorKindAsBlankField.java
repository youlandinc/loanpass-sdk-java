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

package com.youland.vendor.loanpass.model.error;

import com.fasterxml.jackson.databind.JsonNode;
import com.youland.vendor.loanpass.model.AnyOfExecutionErrorKind;
import com.youland.vendor.loanpass.model.AnyOfFieldValue;
import com.youland.vendor.loanpass.model.field.FieldValueAsString;
import com.youland.vendor.loanpass.model.field.FieldValueOpt;

import lombok.Data;
import lombok.NonNull;

@Data
public class ExecutionErrorKindAsBlankField extends AnyOfExecutionErrorKind {
    public static final String FIELD_NAME_FIELDID = "fieldId";

    private String fieldId;

    public ExecutionErrorKindAsBlankField() { super(ExecutionErrorKindOpt.BLANK_FIELD); }

    public ExecutionErrorKindAsBlankField fromJson(@NonNull JsonNode node)  {
        this.fieldId = (node.get(FIELD_NAME_FIELDID)).asText();
        return this;
    }
}

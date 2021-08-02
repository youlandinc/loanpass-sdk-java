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

import com.fasterxml.jackson.databind.JsonNode;
import com.youland.vendor.loanpass.model.AnyOfFieldValue;
import com.youland.vendor.loanpass.model.field.FieldValueAsDuration;
import com.youland.vendor.loanpass.model.field.FieldValueAsEnum;
import com.youland.vendor.loanpass.model.field.FieldValueAsNumber;
import com.youland.vendor.loanpass.model.field.FieldValueAsString;
import com.youland.vendor.loanpass.model.field.FieldValueOpt;
import com.youland.vendor.loanpass.model.field.FieldValueTypeOpt;

import org.apache.commons.lang3.NotImplementedException;

import lombok.NonNull;

public class FieldValueFactory {
    public AnyOfFieldValue create(FieldValueOpt type) {
        AnyOfFieldValue obj;
        switch (type) {
            case ENUM:
                obj = new FieldValueAsEnum();
                break;
            case NUMBER:
                obj = new FieldValueAsNumber();
                break;
            case STRING:
                obj = new FieldValueAsString();
                break;
            case DURATION:
                obj = new FieldValueAsDuration();
                break;
            default:
                throw new NotImplementedException(String.format("Unknown type of '%s'", type));
        }
        return obj;
    }

    public AnyOfFieldValue create(@NonNull JsonNode node) {
        FieldValueOpt type = AnyOfFieldValue.getTypeFromJson(node);
        AnyOfFieldValue obj = this.create(type);
        return obj.fromJson(node);
    }
}

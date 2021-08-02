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

import com.youland.vendor.loanpass.generated.KnownFieldId;
import com.youland.vendor.loanpass.util.FieldValueMappingBuilder;

import java.util.List;

import lombok.Data;
import lombok.NonNull;

@Data
public class FieldValueMapping {
    private String fieldId;
    private AnyOfFieldValue value;

    public FieldValueMapping() {}

    public FieldValueMapping(String fieldId, AnyOfFieldValue value) {
        this.fieldId = fieldId;
        this.value = value;
    }

    public static IBuilder builder() {
        return new FieldValueMappingBuilder();
    }

    public interface IBuilder {
        List<FieldValueMapping> build();

        //Weak type version => Dynamic but unsafe
        IBuilder fieldEnum(String fieldId, String enumTypeId, String variantId);
        IBuilder fieldNumber(String fieldId, double value);
        IBuilder fieldString(String fieldId, String value);
        IBuilder fieldDuration(String fieldId, double count, DurationUnit unit);

        //Strong typed version => Rigid and safe
        IBuilder field(KnownFieldId fieldId, @NonNull Object value, Object...extra);

        IBuilder fieldEnum(KnownFieldId fieldId, String variantId);
        IBuilder fieldNumber(KnownFieldId fieldId, double value);
        IBuilder fieldString(KnownFieldId fieldId, String value);
        IBuilder fieldDuration(KnownFieldId fieldId, double count, DurationUnit unit);
    }
}

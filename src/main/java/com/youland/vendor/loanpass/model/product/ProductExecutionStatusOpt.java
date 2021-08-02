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

package com.youland.vendor.loanpass.model.product;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.youland.lib.core.serializer.ValueObservable;
import com.youland.lib.core.serializer.ValueObservableSerializer;

import java.util.Map;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonSerialize(using = ValueObservableSerializer.class)
public enum ProductExecutionStatusOpt implements ValueObservable<String> {
    ERROR ("error"),
    NO_PRICING ("no-pricing"),
    REJECTED("rejected"),
    OK ("ok");

    public static final Map<String, ProductExecutionStatusOpt> VALUE2ENUM
            = ValueObservable.createMapValue2Obj(ProductExecutionStatusOpt.values());

    private final String value;

    ProductExecutionStatusOpt(String value) {
        this.value = value;
    }
}

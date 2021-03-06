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

import com.youland.vendor.loanpass.model.EnumType;
import com.youland.vendor.loanpass.model.FieldDefinition;
import com.youland.vendor.loanpass.model.ProductFieldDefinition;

import java.util.List;

import lombok.Data;

/**
 * https://api.loanpass.io/v1/swagger#/All%20Endpoints/get_configuration
 */
@Data
public class ConfigResponse {
    private List<FieldDefinition> creditApplicationFields;
    private List<ProductFieldDefinition> productFields;
    private List<EnumType> enumerations;
}

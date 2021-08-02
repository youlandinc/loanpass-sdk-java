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

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.youland.lib.core.serializer.ValueObservable;
import com.youland.lib.core.serializer.ValueObservableSerializer;
import com.youland.vendor.loanpass.model.field.FieldValueTypeOpt;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonSerialize(using = ValueObservableSerializer.class)
public enum DurationUnit implements ValueObservable<String> {
    DAYS("days"),
    WEEKS("weeks"),
    FORTNIGHTS("fortnights"),
    HALF_MONTHS("half-months"),
    MONTHS("months"),
    QUARTERS("quarters"),
    YEARS("years");

    public static final Map<String, DurationUnit> VALUE2ENUM
            = ValueObservable.createMapValue2Obj(DurationUnit.values());

    private final String value;
    DurationUnit(String value) { this.value = value; }
}

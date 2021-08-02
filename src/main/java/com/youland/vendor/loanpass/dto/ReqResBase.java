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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@ToString
@JsonPropertyOrder({"uri", "currentTime"})
public abstract class ReqResBase {
    protected ReqResBase() {}

    protected ReqResBase(@NonNull Instant time) {
        this.setTime(time);
    }

    @Getter @Setter
    private String uri;

    /**
     * e.g., 2021-04-24T00:13:25.008Z
     * ltang: LoanPass uses time of the request to select the version of pricing rules.
     * So if the currentTime is not current, older pricing rules would be applied.
     */
    @JsonProperty(value = "currentTime", required = true)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String currentTimeAsSt = DateTimeFormatter.ISO_INSTANT.format(Instant.now());
    private boolean hasCurrentTimeAsSt() {
        return currentTimeAsSt != null && !currentTimeAsSt.isEmpty();
    }

    @JsonIgnore
    private Instant time;

    public Instant getTime() {
        if (!hasCurrentTimeAsSt())
            return null;
        time = Instant.parse(currentTimeAsSt);
        return time;
    }

    public void setTime(@NonNull Instant instant) {
        this.currentTimeAsSt = DateTimeFormatter.ISO_INSTANT.format(instant);
        this.time = instant;
    }
}

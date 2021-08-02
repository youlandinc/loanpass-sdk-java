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

package com.youland.vendor.loanpass;

import java.time.Duration;

import lombok.Data;
import lombok.NonNull;

@Data
public class Settings {
    public static int DEFAULT_QUERY_TIMEOUT_MILLISEC = 5000;

    private String apiUrl;
    private String apiKey;
    private int maxMemSizeByte = 2 * 1024 * 1024; //2 MB

    private int queryTimeoutMillisec = DEFAULT_QUERY_TIMEOUT_MILLISEC;

    public Settings() {};

    public Settings(@NonNull String apiUrl, @NonNull String apiKey) {
        this.apiUrl = apiUrl;
        this.apiKey = apiKey;
    };

    public Duration getQueryTimeout() {return Duration.ofMillis(queryTimeoutMillisec);}

    @Override
    public String toString() {
        return String.format("apiUrl = %s, maxMemSizeByte = %d", apiUrl, maxMemSizeByte);
    }
}

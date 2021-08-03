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

package com.youland.lib.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.stream.Collectors;

import lombok.NonNull;

public class FileUtil {
    public static String readTextFromRes(@NonNull Class<?> clazz
            , @NonNull String filename) throws IOException {
        InputStream input = clazz.getClassLoader().getResourceAsStream(filename);
        Objects.requireNonNull(input);

        String text;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            text = reader.lines().collect(Collectors.joining("\n"));
        }
        return text;
    }
}
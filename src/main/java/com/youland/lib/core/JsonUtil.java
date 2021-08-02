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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import lombok.NonNull;

public class JsonUtil {
    private static final ObjectMapper _mapper
            = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public static String stringify(Object obj) {
        if (obj == null)
            return null;
        String json = null;
        try {
            json = _mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static <T> T file2Obj(@NonNull String filename, Class<T> classOfT) throws IOException {
        return file2Obj(filename, classOfT, _mapper);
    }

    public static <T> T file2Obj(@NonNull String filename
                , Class<T> classOfT
                , ObjectMapper mapper) throws IOException {
        if (mapper == null)
            mapper = _mapper;
        try (Reader reader = Files.newBufferedReader(Paths.get(filename))){
            return mapper.readValue(reader, classOfT);
        }
    }

    public static <T> T deepClone(@NonNull T origin) throws JsonProcessingException {
        return deepClone(origin, _mapper);
    }

    public static <T> T deepClone(@NonNull T origin, ObjectMapper mapper) throws JsonProcessingException {
        if (mapper == null)
            mapper = _mapper;
        T deepCopy = (T) mapper
                .readValue(mapper.writeValueAsString(origin), origin.getClass());
        return deepCopy;
    }
}

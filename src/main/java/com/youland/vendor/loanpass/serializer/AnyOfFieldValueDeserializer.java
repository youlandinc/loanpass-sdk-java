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

package com.youland.vendor.loanpass.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.youland.vendor.loanpass.model.AnyOfFieldValue;
import com.youland.vendor.loanpass.util.FieldValueFactory;

import java.io.IOException;

public class AnyOfFieldValueDeserializer extends StdDeserializer<AnyOfFieldValue> {

    @SuppressWarnings("unused")
    public AnyOfFieldValueDeserializer() {
        this(null);
    }

    public AnyOfFieldValueDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public AnyOfFieldValue deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        FieldValueFactory factory = new FieldValueFactory();
        return factory.create(node);
    }
}
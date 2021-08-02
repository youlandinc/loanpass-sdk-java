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
import com.youland.vendor.loanpass.model.AnyOfRejectionSource;
import com.youland.vendor.loanpass.model.rejection.RejectionSourceAsRule;
import com.youland.vendor.loanpass.model.rejection.RejectionSourceOpt;

import java.io.IOException;

public class AnyOfRejectionSourceDeserializer extends StdDeserializer<AnyOfRejectionSource> {

    @SuppressWarnings("unused")
    public AnyOfRejectionSourceDeserializer() {
        this(null);
    }

    public AnyOfRejectionSourceDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public AnyOfRejectionSource deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        RejectionSourceOpt type = AnyOfRejectionSource.getTypeFromJson(node);
        AnyOfRejectionSource obj;
        switch (type) {
            case RULE:
                obj = new RejectionSourceAsRule().fromJson(node);
                break;
            default:
                throw new IllegalArgumentException(String.format("Failed to deserialize type of '%s'", type));
        }
        return obj;
    }
}
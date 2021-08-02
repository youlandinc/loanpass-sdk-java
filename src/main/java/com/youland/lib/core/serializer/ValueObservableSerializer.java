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

package com.youland.lib.core.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * Use case: To serialize Enum using its custom value instead of its name.
 */
public class ValueObservableSerializer extends StdSerializer<ValueObservable> {
    @SuppressWarnings("unused")
    public ValueObservableSerializer() {
        this(null);
    }

    public ValueObservableSerializer(Class<ValueObservable> t) {
        super(t);
    }

    @Override
    public void serialize(ValueObservable value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(value.getValue().toString());
    }
}

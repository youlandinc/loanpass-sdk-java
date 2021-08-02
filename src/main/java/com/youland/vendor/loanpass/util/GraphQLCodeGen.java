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

package com.youland.vendor.loanpass.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import static com.youland.vendor.loanpass.util.JavaGenCommon.NAME_SEPARATOR_JAVA;
import static com.youland.vendor.loanpass.util.JavaGenCommon.NAME_SEPARATOR_NON_JAVA;

public class GraphQLCodeGen {
    @Getter @Setter private String saveFileName;
    public boolean hasSaveFileName() {return saveFileName != null; }

    @Getter @Setter private boolean appending = false;

    public GraphQLCodeGen() { }

    public GraphQLCodeGen(String saveFileName) {
        this.saveFileName = saveFileName;
    }

    public String generateEnum(@NonNull String enumName
                                , @NonNull Iterable<String> values) throws IOException {
        enumName = enumName.toUpperCase().replace(NAME_SEPARATOR_NON_JAVA, NAME_SEPARATOR_JAVA);

        StringBuilder st = new StringBuilder();
        for (String value : values) {
            //GraphQL enum naming convention
            value = value
                    .toUpperCase()
                    .replace(NAME_SEPARATOR_NON_JAVA, NAME_SEPARATOR_JAVA);

            st.append("\t")
                    .append(value)
                    .append(System.lineSeparator());
        }

        if (st.length() > 0) {
            st.insert(0, String.format("enum %s {\n", enumName));
            st.append("}")
                    .append(System.lineSeparator())
                    .append(System.lineSeparator());
        }

        String code = st.toString();

        if (hasSaveFileName()) {
            if (!appending)
                Files.write(Path.of(saveFileName), code.getBytes());
            else
                Files.write(Path.of(saveFileName), code.getBytes()
                        , StandardOpenOption.CREATE
                        , StandardOpenOption.APPEND);
        }
        return code;
    }
}

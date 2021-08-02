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

import com.youland.vendor.loanpass.generated.KnownEnumId;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

class GraphQLCodeGenTest {
    private static final String FOLDER_OUTPUT ="./temp";
    private static final String FILENAME_OUTPUT
            = String.format("%s/lp-types.graphqls.codegen", FOLDER_OUTPUT);

    @BeforeAll
    public static void setupClass() throws IOException {
        Files.createDirectories(Paths.get(FOLDER_OUTPUT));
    }

    @AfterAll
    public static void tearDownClass() { }

    /**
     * Generate GraphQL schema containing enum types from KnownEnumId.
     * If you don't see the enum types of interest, you need to re-generate KnownEnumId.java
     * @throws IOException
     */
    @Test
    void test_generateEnum() throws IOException {
        //Arrange
        KnownEnumId enums[] = KnownEnumId.values();

        GraphQLCodeGen codeGen = new GraphQLCodeGen(FILENAME_OUTPUT);
        codeGen.setAppending(false);

        //Action
        KnownEnumId enumId = enums[0];
        Set<String> variantIds = enumId.getVariantIds();
        String st = codeGen.generateEnum(enumId.name(), variantIds);
        Assertions.assertFalse(st.isBlank());
        System.out.println(st);

        codeGen.setAppending(true);
        for (int i = 1; i < enums.length; i++) {
            enumId = enums[i];
            variantIds = enumId.getVariantIds();
            st = codeGen.generateEnum(enumId.name(), variantIds);

            //Assert
            Assertions.assertFalse(st.isBlank());
            System.out.println(st);
        }
    }
}
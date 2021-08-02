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

import com.youland.vendor.loanpass.dto.ConfigResponse;
import com.youland.vendor.loanpass.dto.ConfigResponseTest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class EnumType2JavaGenTest {
    private static final String FOLDER_OUTPUT ="./temp";

    @BeforeAll
    public static void setupClass() throws IOException {
        Files.createDirectories(Paths.get(FOLDER_OUTPUT));
    }

    @AfterAll
    public static void tearDownClass() { }

    /**
     * Generate Java code containing enum types from "./src/test/testdata/config/response_sample.json"
     * If you don't see the enum types of interest, you need to: (1) update response_sample.json first using
     * Postman; (2) Run {@link FieldDef2JavaGenTest} to update {@link KnownFieldId}; (3) finally run
     * this unit test to re-generate {@link KnownEnumId}
     * @throws IOException
     */
    @Test
    void test_generateCode() throws IOException {
        //Arrange
        ConfigResponse response = ConfigResponseTest.createResponse();
        EnumType2JavaGen codegen = new EnumType2JavaGen(response.getEnumerations());
        codegen.setSaveFolder(FOLDER_OUTPUT);

        //Action
        String st = codegen.generateCode(true);

        //Assert
        Assertions.assertFalse(st.isBlank());
        System.out.println(st);
    }
}
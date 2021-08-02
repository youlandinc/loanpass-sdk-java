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

import com.youland.lib.core.JsonUtil;
import com.youland.vendor.loanpass.dto.ExecProductRequest;
import com.youland.vendor.loanpass.model.DurationUnit;
import com.youland.vendor.loanpass.model.FieldValueMapping;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

class ExecProductRequestBuilderTest {
    @BeforeAll
    public static void setupClass() { }

    @AfterAll
    public static void tearDownClass() { }

    @Test
    void test_build() {
        //Arrange
        FieldValueMapping.IBuilder fieldBuilder = new FieldValueMappingBuilder();
        List<FieldValueMapping> fields = fieldBuilder
                .fieldEnum("enumId", "a", "1")
                .fieldNumber("numId", 2)
                .fieldString("stId", "some text")
                .fieldDuration("durId", 3, DurationUnit.DAYS)
                .build();

        ExecProductRequest.IBuilder builder = new ExecProductRequestBuilder();
        builder.withTime(Instant.now())
                .withFields(fields)
                .withProdId("some product id");

        //Action
        ExecProductRequest request = builder.build();

        //Assert
        Assertions.assertNotNull(request);
        System.out.println(JsonUtil.stringify(request));
    }

    @Test
    void test_build_NoProdId() {
        //Arrange
        //Action
        //Assert
        Assertions.assertThrows(NullPointerException.class, () -> {
            new ExecProductRequestBuilder().build();
        });
    }
}
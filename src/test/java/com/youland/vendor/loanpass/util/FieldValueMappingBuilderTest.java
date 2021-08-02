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
import com.youland.vendor.loanpass.generated.KnownFieldId;
import com.youland.vendor.loanpass.model.DurationUnit;
import com.youland.vendor.loanpass.model.FieldValueMapping;
import com.youland.vendor.loanpass.model.FieldValueMapping.IBuilder;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

class FieldValueMappingBuilderTest {

    @BeforeAll
    public static void setupClass() { }

    @AfterAll
    public static void tearDownClass() { }

    @Test
    void test_build() {
        //Arrange
        IBuilder builder = new FieldValueMappingBuilder();
        builder.fieldEnum("enumId", "a", "1")
                .fieldNumber("numId", 2)
                .fieldString("stId", "some text")
                .fieldDuration("durId", 3, DurationUnit.DAYS);

        //Action
        List<FieldValueMapping> fields  = builder.build();

        //Assert
        Assertions.assertNotNull(fields);
        System.out.println(JsonUtil.stringify(fields));
    }

    @Test
    void test_build_safe() {
        //Arrange
        IBuilder builder = new FieldValueMappingBuilder();
        builder.fieldEnum(KnownFieldId.LOAN_PURPOSE, "refinance")
                .fieldNumber(KnownFieldId.BASE_LOAN_AMOUNT, 2)
                .fieldString(KnownFieldId.STATE, "some text")
                .fieldDuration(KnownFieldId.DESIRED_LOAN_TERM, 3, DurationUnit.DAYS);

        //Action
        List<FieldValueMapping> fields  = builder.build();

        //Assert
        Assertions.assertNotNull(fields);
        System.out.println(JsonUtil.stringify(fields));
    }

    @Test
    void test_build_safe_mismatch_enum_variant_id() {
        //Arrange
        //Action
        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new FieldValueMappingBuilder()
                    .fieldEnum(KnownFieldId.LOAN_PURPOSE,"invalid variant id");
        });
    }

    @Test
    void test_build_safe_mismatch_enum() {
        //Arrange
        //Action
        //Assert
        Assertions.assertThrows(AssertionError.class, () -> {
            new FieldValueMappingBuilder()
                    .fieldEnum(KnownFieldId.BASE_LOAN_AMOUNT,"1");
        });
    }

    @Test
    void test_build_safe_mismatch_string() {
        //Arrange
        //Action
        //Assert
        Assertions.assertThrows(AssertionError.class, () -> {
            new FieldValueMappingBuilder()
                    .fieldString(KnownFieldId.LOAN_PURPOSE, "some string");
        });
    }

    @Test
    void test_build_safe_mismatch_duration() {
        //Arrange
        //Action
        //Assert
        Assertions.assertThrows(AssertionError.class, () -> {
            new FieldValueMappingBuilder()
                    .fieldDuration(KnownFieldId.BASE_LOAN_AMOUNT, 3, DurationUnit.DAYS);
        });
    }

    @Test
    void test_Adding_Duplicated() {
        //Arrange
        //Action
        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new FieldValueMappingBuilder()
                    .fieldEnum("duplicatedId", "a", "1")
                    .fieldEnum("duplicatedId", "b", "2");
        });
    }
}
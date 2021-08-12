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

package com.youland.vendor.loanpass.converter;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.youland.lib.core.JsonUtil;
import com.youland.lib.core.TagObj;
import com.youland.vendor.loanpass.generated.KnownFieldId;
import com.youland.vendor.loanpass.model.DurationUnit;
import com.youland.vendor.loanpass.model.FieldValueMapping;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Obj2FieldValueMappingTest {

    public enum Loan_Purpose {
        PURCHASE,
        REFINANCE
    }

    @JsonAutoDetect(fieldVisibility = Visibility.ANY)
    public static class AnnotatedInputNested {
        @Tag(KnownFieldId.AFTER_REPAIR_VALUE_ARV)
        private Double afterRepairValue = 88.0;
    }

    @JsonAutoDetect(fieldVisibility = Visibility.ANY)
    public static class AnnotatedInput {
        @Tag(KnownFieldId.LOAN_PURPOSE)
        private Loan_Purpose loanPurpose = Loan_Purpose.REFINANCE;

        @Tag(KnownFieldId.STATE)
        private String propertyState = "CA";

        @Tag(KnownFieldId.BASE_LOAN_AMOUNT)
        private Double loanAmount = 99.0;

        @TagDuration(value = KnownFieldId.DESIRED_LOAN_TERM, unit = DurationUnit.MONTHS)
        private Double loanTerm = 11.0;

        @TagObj
        private AnnotatedInputNested nestedInput = new AnnotatedInputNested();
    }

    public static List<FieldValueMapping> CreateFields(Object source) {
        var converter = new Obj2FieldValueMapping(source);
        List<FieldValueMapping> fields = converter.convert();
        return fields;
    }

    @BeforeAll
    public static void setupClass() { }

    @AfterAll
    public static void tearDownClass() { }

    @Test
    void test_convert_NoTag() {
        //Arrange
        //Action
        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Obj2FieldValueMapping(new Object()).convert();
        });
    }

    @Test
    void test_convert_Ok() {
        //Arrange
        AnnotatedInput source = new AnnotatedInput();

        //Action
        var result = CreateFields(source);

        //Assert
        System.out.println(JsonUtil.stringify(result));

        Assertions.assertNotNull(result);
        Assertions.assertEquals(5, result.size());
    }
}
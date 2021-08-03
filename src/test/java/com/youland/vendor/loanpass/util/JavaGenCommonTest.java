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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JavaGenCommonTest {
    @Test
    void test_toJavaVarName() {
        //Arrange
        String input = "three-month-bank-statement";
        String expected = "THREE_MONTH_BANK_STATEMENT";

        //Act
        String result = JavaGenCommon.toJavaVarName(input);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void test_toJavaVarName_With_Prefix_Int() {
        //Arrange
        String input = "3-month-bank-statement";
        String expected = "_3_MONTH_BANK_STATEMENT";

        //Act
        String result = JavaGenCommon.toJavaVarName(input);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void test_toJavaVarNameReverse() {
        //Arrange
        String input = "THREE_MONTH_BANK_STATEMENT";
        String expected = "three-month-bank-statement";

        //Act
        String result = JavaGenCommon.toJavaVarNameReverse(input);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void test_toJavaVarNameReverse_With_Prefix_Separator() {
        //Arrange
        String input = "_3_MONTH_BANK_STATEMENT";
        String expected = "3-month-bank-statement";

        //Act
        String result = JavaGenCommon.toJavaVarNameReverse(input);

        //Assert
        assertEquals(expected, result);
    }
}
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

package com.youland.vendor.loanpass;

import com.youland.lib.core.JsonUtil;
import com.youland.vendor.loanpass.dto.ConfigResponse;
import com.youland.vendor.loanpass.dto.ExecProductRequest;
import com.youland.vendor.loanpass.dto.ExecProductRequestTest;
import com.youland.vendor.loanpass.dto.ExecProductResponse;
import com.youland.vendor.loanpass.dto.ExecSummaryRequest;
import com.youland.vendor.loanpass.dto.ExecSummaryRequestTest;
import com.youland.vendor.loanpass.dto.ExecSummaryResponse;
import com.youland.vendor.loanpass.exception.ResponseException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;

import java.io.IOException;

class LoanPassClientTest {
    private static final String LP_API_KEY_NOT_FOUND = "Test disabled because LP_API_KEY was not set in system environment variables";
    private static final String LOANPASS_API_URL = "https://api.loanpass.io/v1";

    private static ILoanPassClient loanpassClient;
    private static boolean hasLoanpassClient() {return loanpassClient != null;}

    private static Settings createConfig() {
        var api_key = System.getenv("LP_API_KEY");
        if (api_key == null) {
            System.err.println(LP_API_KEY_NOT_FOUND);
            return null;
        }
        Settings config = new Settings(LOANPASS_API_URL, api_key);
        return config;
    }

    @BeforeAll
    public static void setupClass() {
        Settings config = createConfig();
        if (config != null)
            loanpassClient = new LoanPassClient(config);
    }

    @AfterAll
    public static void tearDownClass() { }

    @Test
    void test_constructor() {
        //Arrange
        //Action
        //Assert
        Assertions.assertThrows(NullPointerException.class, () -> {
            new LoanPassClient(null);
        });
    }

    @Test
    @EnabledIf(value="hasLoanpassClient", disabledReason=LP_API_KEY_NOT_FOUND)
    void test_getConfiguration() {
        //Arrange

        //Action
        ConfigResponse response = loanpassClient.getConfiguration();

        //Assert
        Assertions.assertNotNull(response);

        System.out.println(JsonUtil.stringify(response));
    }

    @Test
    @EnabledIf(value="hasLoanpassClient", disabledReason=LP_API_KEY_NOT_FOUND)
    void test_postExecSummary_ResponseException() {
        //Arrange
        ExecSummaryRequest request = new ExecSummaryRequest();
        request.setCreditApplicationFields(null);

        //Action
        try{
            loanpassClient.postExecSummary(request);
            Assertions.assertFalse(true);
        } catch (ResponseException e) {
            //Assert
            Assertions.assertFalse(false);
            System.out.println(e);
        }
    }

    @Test
    @EnabledIf(value="hasLoanpassClient", disabledReason=LP_API_KEY_NOT_FOUND)
    void test_postExecSummary_NoInput() throws ResponseException {
        //Arrange
        ExecSummaryRequest request = new ExecSummaryRequest();

        //Action
        ExecSummaryResponse response = loanpassClient.postExecSummary(request);

        //Assert
        Assertions.assertNotNull(response);
        System.out.println(JsonUtil.stringify(response));
    }

    @Test
    @EnabledIf(value="hasLoanpassClient", disabledReason=LP_API_KEY_NOT_FOUND)
    void test_postExecSummary() throws ResponseException, IOException {
        //Arrange
        ExecSummaryRequest request = ExecSummaryRequestTest.createRequest();
        System.out.println(JsonUtil.stringify(request));

        //Action
        ExecSummaryResponse response = loanpassClient.postExecSummary(request);

        //Assert
        Assertions.assertNotNull(response);
        System.out.println(JsonUtil.stringify(response));
    }

    @Test
    @EnabledIf(value="hasLoanpassClient", disabledReason=LP_API_KEY_NOT_FOUND)
    void test_postExecProduct_ResponseException() {
        //Arrange
        ExecProductRequest request = new ExecProductRequest();
        request.setCreditApplicationFields(null);

        //Action
        try{
            loanpassClient.postExecProduct(request);
            Assertions.assertFalse(true);
        } catch (ResponseException e) {
            //Assert
            Assertions.assertFalse(false);
            System.out.println(e);
        }
    }

    @Test
    @EnabledIf(value="hasLoanpassClient", disabledReason=LP_API_KEY_NOT_FOUND)
    @Disabled("ExecProductRequestTest.createRequestOk needs to be provided with a valid request2response_ok.json")
    void test_postExecProduct_Ok() throws ResponseException, IOException {
        //Arrange
        ExecProductRequest request = ExecProductRequestTest.createRequestOk();
        System.out.println(JsonUtil.stringify(request));

        //Action
        ExecProductResponse response = loanpassClient.postExecProduct(request);

        //Assert
        Assertions.assertNotNull(response);
        System.out.println(JsonUtil.stringify(response));
    }
}
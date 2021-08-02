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

import com.youland.vendor.loanpass.dto.ConfigResponse;
import com.youland.vendor.loanpass.dto.ExecProductRequest;
import com.youland.vendor.loanpass.dto.ExecProductResponse;
import com.youland.vendor.loanpass.dto.ExecSummaryRequest;
import com.youland.vendor.loanpass.dto.ExecSummaryResponse;
import com.youland.vendor.loanpass.exception.ResponseException;

import reactor.core.publisher.Mono;

public interface ILoanPassClient {
    Settings getSettings();

    ConfigResponse getConfiguration();

    Mono<ExecSummaryResponse> postExecSummaryAsync(ExecSummaryRequest request);
    ExecSummaryResponse postExecSummary(ExecSummaryRequest request) throws ResponseException;

    Mono<ExecProductResponse> postExecProductAsync(ExecProductRequest request);
    ExecProductResponse postExecProduct(ExecProductRequest request) throws ResponseException;
}

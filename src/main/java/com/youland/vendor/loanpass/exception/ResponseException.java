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

package com.youland.vendor.loanpass.exception;

import com.youland.vendor.loanpass.dto.ErrorResponse;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public class ResponseException extends Exception {

    private int httpStatus;
    private String uri;
    private String request;

    private String requestId;
    private String errorCode;

    public ResponseException(String message) {
        super(message);
    }

    public ResponseException(ErrorResponse response) {
        this(response.getMessage());

        this.requestId = response.getRequestId();
        this.errorCode = response.getErrorCode();
    }
}
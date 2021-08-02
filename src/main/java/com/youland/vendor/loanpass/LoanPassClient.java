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

import com.google.common.net.HttpHeaders;
import com.youland.lib.core.JsonUtil;
import com.youland.vendor.loanpass.dto.ConfigResponse;
import com.youland.vendor.loanpass.dto.ErrorResponse;
import com.youland.vendor.loanpass.dto.ExecProductRequest;
import com.youland.vendor.loanpass.dto.ExecProductResponse;
import com.youland.vendor.loanpass.dto.ExecSummaryRequest;
import com.youland.vendor.loanpass.dto.ExecSummaryResponse;
import com.youland.vendor.loanpass.dto.ReqResBase;
import com.youland.vendor.loanpass.exception.ResponseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import lombok.NonNull;
import reactor.core.publisher.Mono;

/**
 * Wrapping around LoanPass' REST API
 * https://api.loanpass.io/v1/swagger#/ *
 */
public class LoanPassClient implements  ILoanPassClient {
    private static final String REST_Configuration = "/configuration";
    private static final String REST_EXECUTE_PRODUCT = "/execute-product";
    private static final String REST_EXECUTE_SUMMARY = "/execute-summary";

    private final Logger logger = LoggerFactory.getLogger(LoanPassClient.class);

    final Settings settings;
    private final WebClient webClient;

    public LoanPassClient(@NonNull Settings settings) {
        this.settings = settings;
        logger.info(this.settings.toString());

        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(settings.getMaxMemSizeByte()))
                .build();

        webClient = WebClient.builder()
                .exchangeStrategies(exchangeStrategies)
                .baseUrl(settings.getApiUrl())
                .defaultHeader(HttpHeaders.AUTHORIZATION, settings.getApiKey())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .filter(defaultErrorHandlingFilter())
                .build();
    }

    public Settings getSettings() {return this.settings; }

    private String getFullUri(String uri) {
        return String.format("%s%s", settings.getApiUrl(), uri);
    }

    /**
     * When NOT HttpStatus.OK, return LoanPass's custom exception if available.
     * Otherwise return standard WebClientResponseException.
     * @return
     */
    private static ExchangeFilterFunction defaultErrorHandlingFilter() {
        return ExchangeFilterFunction.ofResponseProcessor(response -> {
            HttpStatus statusCode = response.statusCode();
            if (statusCode.equals(HttpStatus.OK)) {
                //ltang: No error -> Pass through
                return Mono.just(response);
            } else {
                if (response.headers().contentLength().getAsLong() <= 0)
                    //ltang: Return standard WebClientResponseException
                    return response.createException().flatMap(Mono::error);
                else {
                    //ltang: Return LoanPass's custom exception
                    //return response.bodyToMono(ResponseException.class).flatMap(Mono::error);
                    return response.bodyToMono(ErrorResponse.class)
                            .flatMap(errorResponse -> {
                                ResponseException ex = new ResponseException(errorResponse);
                                ex.setHttpStatus(statusCode.value());
                                return Mono.error(ex);
                            });
                }
            }
        });
    }

    private static ResponseException extractRootCause(Throwable e, ReqResBase extra) {
        if (e == null)
            return null;
        if (e.getCause() instanceof ResponseException) {
            ResponseException root = (ResponseException) e.getCause();
            if (extra != null) {
                root.setUri(extra.getUri());
                root.setRequest(JsonUtil.stringify(extra));
            }
            return root;
        }
        return null;
    }

    @Override
    public ConfigResponse getConfiguration() {
        ConfigResponse response = null;
        final String uri = REST_Configuration;
        try {
            ResponseSpec spec = webClient
                    .get()
                    .uri(uri)
                    .retrieve();

            //String st = spec.bodyToMono(String.class).block(settings.getQueryTimeout());
            response = spec.bodyToMono(ConfigResponse.class).block(settings.getQueryTimeout());
            return response;

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Mono<ExecSummaryResponse> postExecSummaryAsync(@NonNull ExecSummaryRequest request) {
        final String uri = REST_EXECUTE_SUMMARY;
        request.setUri(getFullUri(uri));
        Mono<ExecSummaryResponse> result = null;
        try {
            ResponseSpec spec =
                    webClient
                            .post()
                            .uri(uri)
                            .body(BodyInserters.fromValue(request))
                            .retrieve();

            result = spec.bodyToMono(ExecSummaryResponse.class).flatMap(response -> {
                response.setUri(getFullUri(uri));
                return Mono.just(response);
            });

            return result;
        }
        catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ExecSummaryResponse postExecSummary(@NonNull ExecSummaryRequest request) throws ResponseException {
        try {
            Mono<ExecSummaryResponse> temp = this.postExecSummaryAsync(request);
            return temp.block(settings.getQueryTimeout());
        }
        catch (Exception e) {
            ResponseException root = extractRootCause(e, request);
            if (root != null) throw root; else throw e;
        }
    }

    @Override
    public Mono<ExecProductResponse> postExecProductAsync(@NonNull ExecProductRequest request) {
        final String uri = REST_EXECUTE_PRODUCT;
        request.setUri(getFullUri(uri));
        Mono<ExecProductResponse> result = null;
        try {
            ResponseSpec spec =
                    webClient
                            .post()
                            .uri(uri)
                            .body(BodyInserters.fromValue(request))
                            .retrieve();

            result = spec.bodyToMono(ExecProductResponse.class).flatMap(response -> {
                response.setUri(getFullUri(uri));
                return Mono.just(response);
            });

            return result;
        }
        catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ExecProductResponse postExecProduct(@NonNull ExecProductRequest request) throws ResponseException {
        try {
            Mono<ExecProductResponse> temp = this.postExecProductAsync(request);
            return temp.block(settings.getQueryTimeout());
        }
        catch (Exception e) {
            ResponseException root = extractRootCause(e, request);
            if (root != null) throw root; else throw e;
        }
    }
}

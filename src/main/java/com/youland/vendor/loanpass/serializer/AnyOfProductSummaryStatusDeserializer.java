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

package com.youland.vendor.loanpass.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.youland.vendor.loanpass.model.AnyOfProductSummaryStatus;
import com.youland.vendor.loanpass.model.product.ProductSummaryStatusOpt;
import com.youland.vendor.loanpass.model.product.ProductSummaryStatusAsApproved;
import com.youland.vendor.loanpass.model.product.ProductSummaryStatusAsAvailable;
import com.youland.vendor.loanpass.model.product.ProductSummaryStatusAsOthers;

import java.io.IOException;

public class AnyOfProductSummaryStatusDeserializer extends StdDeserializer<AnyOfProductSummaryStatus> {

    @SuppressWarnings("unused")
    public AnyOfProductSummaryStatusDeserializer() {
        this(null);
    }

    public AnyOfProductSummaryStatusDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public AnyOfProductSummaryStatus deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        ProductSummaryStatusOpt type = AnyOfProductSummaryStatus.getTypeFromJson(node);
        AnyOfProductSummaryStatus obj;
        switch (type) {
            case AVAILABLE:
                obj = new ProductSummaryStatusAsAvailable().fromJson(node);
                break;
            case APPROVED:
                obj = new ProductSummaryStatusAsApproved().fromJson(node);
                break;
            default:
                obj = new ProductSummaryStatusAsOthers(type);
        }
        return obj;
    }
}
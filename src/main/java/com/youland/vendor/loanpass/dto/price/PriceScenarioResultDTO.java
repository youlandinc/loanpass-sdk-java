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

package com.youland.vendor.loanpass.dto.price;

import com.youland.vendor.loanpass.converter.FieldValueMapping2Obj;
import com.youland.vendor.loanpass.converter.TagCalc;
import com.youland.vendor.loanpass.converter.TagDurationPriceScr;
import com.youland.vendor.loanpass.converter.TagPriceScr;
import com.youland.vendor.loanpass.generated.KnownCalcFieldId;
import com.youland.vendor.loanpass.generated.KnownPriceScenarioFieldId;
import com.youland.vendor.loanpass.model.DurationUnit;
import com.youland.vendor.loanpass.model.PriceScenarioResult;
import com.youland.vendor.loanpass.model.price.PriceScenarioStatusOpt;

import java.util.ArrayList;
import java.util.List;

import com.youland.vendor.loanpass.price.IPriceInfo;
import lombok.Data;
import lombok.NonNull;

/**
 * Mapping fields of {@link PriceScenarioResult}
 */
@Data
public class PriceScenarioResultDTO implements IPriceInfo {
    private Object tag;

    @TagDurationPriceScr(value= KnownPriceScenarioFieldId.RATE_LOCK_PERIOD, unit = DurationUnit.DAYS)
    private Double rateLockPeriod;

    @TagPriceScr(KnownPriceScenarioFieldId.BASE_PRICE)
    private Double basePrice;

    @TagPriceScr(value = KnownPriceScenarioFieldId.BASE_INTEREST_RATE)
    private Double baseInterestRate;

    @TagCalc(KnownCalcFieldId.EST_PAYMENT)
    private Double estPayment;

    @TagCalc(KnownCalcFieldId.INITIAL_IO_PAYMENT)
    private Double initialIOPayment;

    @TagCalc(KnownCalcFieldId.INITIAL_PI_PAYMENT)
    private Double initialPIPayment;

    @TagCalc(KnownCalcFieldId.START_PRICE)
    private Double startPrice;

    @TagCalc(KnownCalcFieldId.START_INTEREST_RATE)
    private Double startInterestRate;

    @TagCalc(KnownCalcFieldId.ADJUSTED_PRICE)
    private Double adjustedPrice;

    @TagCalc(KnownCalcFieldId.ADJUSTED_INTEREST_RATE)
    private Double adjustedInterestRate;

    @TagCalc(KnownCalcFieldId.ADJUSTED_RATE_LOCK_PERIOD)
    private Double adjustedRateLockPeriod;

    @TagCalc(KnownCalcFieldId.DSCR)
    private Double dscr;

    @TagCalc(KnownCalcFieldId.EST_APR_PERCENT)
    private Double estAprPercent;

    @Override
    public boolean priceEnabled() { return getPrice() != null; }

    @Override
    public Double getLockPeriod() { return adjustedRateLockPeriod; }

    @Override
    public Double getPrice() { return adjustedPrice; }

    /**
     * TODO ltang: Need a better way to handle non-approved price scenarios
     * @param prices
     * @return
     */
    public static List<PriceScenarioResultDTO> fromApprovedOnly(@NonNull Iterable<PriceScenarioResult> prices) {
        List<PriceScenarioResultDTO> result = new ArrayList<>();
        for (PriceScenarioResult price : prices) {
            PriceScenarioStatusOpt type = price.getStatus().getType();
            if (type != PriceScenarioStatusOpt.APPROVED)
                continue;

            PriceScenarioResultDTO dto = new PriceScenarioResultDTO();

            FieldValueMapping2Obj converter = new FieldValueMapping2Obj(dto);

            converter.convert(price.getCalculatedFields());
            converter.convert(price.getPriceScenarioFields());

            dto.setTag(price);
            result.add(dto);
        }
        return result;
    }
}

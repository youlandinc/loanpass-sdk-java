package com.youland.vendor.loanpass.price;

import lombok.Data;

/**
 * Two references for locking the optimal interest rate:
 * (1) lock period = 30-day
 * (2) Price = 100
 * */
@Data
public class PriceFinderCriteria {
    //ltang: LoanPass rules already include 2 points for profit margin.
    //https://loanpass.atlassian.net/servicedesk/customer/portal/2/LPS-613
    public static final Double TARGETED_PRICE_MIN_DEFAULT = 100.0;
    public static final Double REFERRED_PERIOD_DEFAULT = 30.0; //Days

    private Double targetedPriceMin = TARGETED_PRICE_MIN_DEFAULT;
    private Double preferredPeriod = REFERRED_PERIOD_DEFAULT;
}

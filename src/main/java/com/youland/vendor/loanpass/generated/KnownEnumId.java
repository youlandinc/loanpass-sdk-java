package com.youland.vendor.loanpass.generated;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.youland.lib.core.serializer.ValueObservable;
import com.youland.lib.core.serializer.ValueObservableSerializer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString
@JsonSerialize(using = ValueObservableSerializer.class)
@javax.annotation.Generated(value="com.youland.vendor.loanpass.util.EnumType2JavaGen", date="2021-08-01T02:55:16.016470Z")
/**
 * Generated code from LoanPass API for static checking
 */
public enum KnownEnumId implements ValueObservable<String> {
    //Count: 37
    MORTGAGE_LATES_IN_LAST_12_MOS ("mortgage-lates-in-last-12-mos", Set.of("0-x-30","1-x-30","2-x-30","1-x-60","1-x-90","0-x-30-x-24-months")),
    FHA_ASSIGNMENT_DATE_RANGE ("fha-assignment-date-range", Set.of("1-26-2015-today-16-30-yr","6-3-2013-today-0-15-yr","6-3-2013-today-refi-only-16-30-yr","6-3-2013-1-25-2015","4-1-2013-6-2-2013","6-11-2012-6-2-2013","6-11-2012-3-31-2013","4-9-2012-6-10-2012","4-18-2011-4-8-2012","10-4-2010-4-17-2011","4-5-2010-10-3-2010","10-1-2008-4-4-2010")),
    FHA_REFI_ENDORSEMENT_DATE_RANGE ("fha-refi-endorsement-date-range", Set.of("after-5-31-2009","on-or-before-5-31-2009")),
    GOVERNMENT_REFINANCE_TYPE ("government-refinance-type", Set.of("cash-out-va","full-documentation","interest-rate-reduction-refinance-loan","none","other","simple-refinance","streamline-with-appraisal","streamline-without-appraisal")),
    VA_VETERAN_TYPE ("va-veteran-type", Set.of("national-guard","regular-military","reserves")),
    COMMUNITY_LENDING_PRODUCT_TYPE ("community-lending-product-type", Set.of("fhlmc-home-one","fhlmc-home-possible","fnma-high-ltv-refinance","fnma-home-ready","fnma-home-style","none")),
    CONDO_TYPE ("condo-type", Set.of("low-rise-1-3-stories","mid-rise-4-8-stories","high-rise-9-stories")),
    MI_OPTION ("mi-option", Set.of("borrower-paid","lender-paid","mi-not-required","split-mi","single-finance-mi")),
    ADVERSE_ACCOUNTS ("adverse-accounts", Set.of("not-applicable","0-23-months-ago","24-months-ago")),
    PRODUCT_CATEGORY ("product-category", Set.of("alternative-mortgage","bridge","government","jumbo","mortgage","rental")),
    RECENT_MORTGAGE_HISTORY ("recent-mortgage-history", Set.of("not-applicable","no-mortgage-history-within-the-last-120-days","no-mortgage-history-for-longer-than-120-days")),
    SUB_PRODUCT_CATEGORY ("sub-product-category", Set.of("stabilized-bridge","fix-flip")),
    OCCUPANCY_TYPE ("occupancy-type", Set.of("investment","primary-residence","second-home")),
    DEED_IN_LIEU ("deed-in-lieu", Set.of("not-applicable","settled-within-last-year","settled-1-2-years-ago","settled-2-years-ago","settled-60-months-ago")),
    INDEX_TYPE ("index-type", Set.of("bank-prime-loan","certificate-of-deposit-index","constant-maturity-treasury","cost-of-savings-index","eleventh-district-cost-of-funds-index","libor","other","sofr","treasury-bill","twelve-month-treasury-average")),
    SECONDARY_FINANCING ("secondary-financing", Set.of("none","fixed-second","heloc")),
    YES_NO ("yes-no", Set.of("no","yes")),
    WHOLESALE_COMPENSATION ("wholesale-compensation", Set.of("borrower-paid","lender-paid")),
    LOAN_TERM_TYPE ("loan-term-type", Set.of("preset","range")),
    INTEREST_ONLY_PERIOD ("interest-only-period", Set.of("no","yes")),
    BORROWER_TYPE ("borrower-type", Set.of("us-citizen","perm-res-alien","non-perm-res-alien","foreign-nationals","itin","business-llc")),
    CHANNEL ("channel", Set.of("correspondent","wholesale")),
    BANKRUPTCY ("bankruptcy", Set.of("not-applicable","0-11-months-ago","less-than-2-years-ago","2-3-years-ago","3-years-ago","60-months-ago")),
    PREPAYMENT_PENALTY ("prepayment-penalty", Set.of("buy-out-prepay","1-year-prepay","2-year-prepay","3-year-prepay","4-year-prepay","5-year-prepay")),
    REFINANCE_TYPE ("refinance-type", Set.of("cash-out","limited-cash-out","no-cash-out")),
    LOAN_PURPOSE ("loan-purpose", Set.of("purchase","refinance","assumption","mortgage-modification")),
    LENDER_COMP_PERCENT ("lender-comp-percent", Set.of("0-000","0-125","0-250","0-375","0-500","0-625","0-750","0-875","1-000","1-125","1-250","1-375","1-500","1-625","1-750","1-875","2-000","2-125","2-250","2-375","2-500","2-625","2-750","2-875","3-000")),
    FORBEARANCE ("forbearance", Set.of("not-applicable","within-last-90-days","within-last-180-days","7-18-months-ago","18-months-ago")),
    DOCUMENTATION_TYPE ("documentation-type", Set.of("full-documentation","alt-doc-bank-statements","3-month-bank-statement","3-month-bank-statements-business","self-prepared-profit-loss","voe","asset-depletion","atr-in-full","dscr")),
    INTEREST_RATE_ROUNDING_TYPE ("interest-rate-rounding-type", Set.of("down","nearest","no-rounding","up")),
    FORECLOSURE ("foreclosure", Set.of("not-applicable","0-11-months-ago","less-than-2-years-ago","2-3-years-ago","3-years-ago","60-months-ago")),
    ATTACHMENT_TYPE ("attachment-type", Set.of("attached","detached","semi-detached")),
    SHORT_SALES ("short-sales", Set.of("not-applicable","settled-within-last-year","settled-1-2-years-ago","settled-2-years-ago","settled-60-months-ago")),
    MORTGAGE_TYPE ("mortgage-type", Set.of("conventional","fha","local-agency","other","public-and-indian-housing","state-agency","usda-rural-development","va")),
    AMORTIZATION_TYPE ("amortization-type", Set.of("adjustable-rate","fixed","gem","gpm","graduated-payment-arm","other","rate-improvement-mortgage","step")),
    PROPERTY_TYPE ("property-type", Set.of("adult-care-facility","condominium","condotel-pu-dtel","cooperative","manufactured-home","non-warranted-condominium","other","pud","rural","single-family","townhouse","two-to-four-family","short-term-rental")),
    LIEN_PRIORITY ("lien-priority", Set.of("first","fourth","other","second","third"));

    public static final Map<String, KnownEnumId> VALUE2ENUM
            = ValueObservable.createMapValue2Obj(KnownEnumId.values());

    public static final Map<String, Set<String>> VARIANTID2VALUES
            = createMapVariantId2Values(KnownEnumId.values());

    private final String value;
    private final Set<String> variantIds;

    KnownEnumId(String value, Set<String> variantIds) {
        this.value = value;
        this.variantIds = variantIds;
    }

    public static String boolToYesNo(boolean bValue) {
        return bValue ? "yes" : "no";
    }

    public static boolean isValid(@NonNull String value) {
        return VALUE2ENUM.containsKey(value);
    }

    public static boolean isValidVariantId(@NonNull String variantId) {
        return isValidVariantId(variantId, null);
    }

    public static boolean isValidVariantId(@NonNull String variantId, String enumValue) {
        Set<String> values = VARIANTID2VALUES.get(variantId);
        if (values == null)
            return false;
        return enumValue == null ? true : values.contains(enumValue);
    }

    private static Map<String, Set<String>> createMapVariantId2Values(KnownEnumId enums[]) {
        Map<String, Set<String>> map = new HashMap();
        for (KnownEnumId enumId : enums) {
            for (String variantId: enumId.variantIds) {
                Set<String> enumSet = map.get(variantId);
                if (enumSet == null) {
                    enumSet = new HashSet<>();
                    map.put(variantId, enumSet);
                }
                enumSet.add(enumId.value);
            }
        }
        return map;
    }
}
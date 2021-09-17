package com.youland.vendor.loanpass.generated;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Map;

import lombok.Getter;
import lombok.ToString;

import com.youland.lib.core.serializer.ValueObservable;
import com.youland.lib.core.serializer.ValueObservableSerializer;
import com.youland.vendor.loanpass.model.field.FieldValueTypeOpt;

import static com.youland.vendor.loanpass.model.field.FieldValueTypeOpt.ENUM;
import static com.youland.vendor.loanpass.model.field.FieldValueTypeOpt.NUMBER;
import static com.youland.vendor.loanpass.model.field.FieldValueTypeOpt.STRING;
import static com.youland.vendor.loanpass.model.field.FieldValueTypeOpt.DURATION;

@Getter
@ToString
@JsonSerialize(using = ValueObservableSerializer.class)
@javax.annotation.Generated(value="com.youland.vendor.loanpass.util.FieldDef2JavaGen", date="2021-08-01T02:55:15.943561Z")
/**
 * Generated code from LoanPass API for static checking
 */
public enum KnownFieldId implements ValueObservable<String> {
    //Count: 59
    LOAN_PURPOSE ("field@loan-purpose", ENUM, "loan-purpose"),
    FIRST_TIME_HOMEBUYER ("field@first-time-homebuyer", ENUM, "yes-no"),
    REFINANCE_TYPE ("field@refinance-type", ENUM, "refinance-type"),
    BORROWER_TYPE ("field@borrower-type", ENUM, "borrower-type"),
    OCCUPANCY_TYPE ("field@occupancy-type", ENUM, "occupancy-type"),
    PROPERTY_RENTAL_INCOME ("field@property-rental-income", NUMBER),
    PROPERTY_TYPE ("field@property-type", ENUM, "property-type"),
    PROPERTY_TYPE_OTHER_DESCRIPTION ("field@property-type-other-description", STRING),
    CONDO_TYPE ("field@condo-type", ENUM, "condo-type"),
    DECISION_CREDIT_SCORE ("field@decision-credit-score", NUMBER),
    NUMBER_OF_UNITS ("field@number-of-units", NUMBER),
    BASE_LOAN_AMOUNT ("field@base-loan-amount", NUMBER),
    CASH_OUT_AMOUNT ("field@cash-out-amount", NUMBER),
    APPRAISED_VALUE ("field@appraised-value", NUMBER),
    PURCHASE_PRICE ("field@purchase-price", NUMBER),
    DESIRED_PRODUCT_CATEGORY ("field@desired-product-category", ENUM, "product-category"),
    SUB_PRODUCT_CATEGORY ("field@sub-product-category", ENUM, "sub-product-category"),
    TOTAL_COST_OF_PROJECT ("field@total-cost-of-project", NUMBER),
    REHAB_FUNDS ("field@rehab-funds", ENUM, "yes-no"),
    AFTER_REPAIR_VALUE_ARV ("field@after-repair-value-arv", NUMBER),
    ATTACHMENT_TYPE ("field@attachment-type", ENUM, "attachment-type"),
    DOCUMENTATION_TYPE ("field@documentation-type", ENUM, "documentation-type"),
    PREPAYMENT_PENALTY ("field@prepayment-penalty", ENUM, "prepayment-penalty"),
    STATE ("field@state", STRING),
    COUNTY ("field@county", STRING),
    WAIVE_ESCROWS ("field@waive-escrows", ENUM, "yes-no"),
    DESIRED_LOAN_TERM ("field@desired-loan-term", DURATION),
    DESIRED_MORTGAGE_TYPE ("field@desired-mortgage-type", ENUM, "mortgage-type"),
    DESIRED_MORTGAGE_TYPE_OTHER ("field@desired-mortgage-type-other", STRING),
    MI_OPTION ("field@mi-option", ENUM, "mi-option"),
    DESIRED_AMORTIZATION_TYPE ("field@desired-amortization-type", ENUM, "amortization-type"),
    DESIRED_AMORTIZATION_TYPE_OTHER ("field@desired-amortization-type-other", STRING),
    DESIRED_RATE_LOCK_PERIOD ("field@desired-rate-lock-period", DURATION),
    DESIRED_INTEREST_RATE ("field@desired-interest-rate", NUMBER),
    DESIRED_COMMUNITY_LENDING_PRODUCT ("field@desired-community-lending-product", ENUM, "community-lending-product-type"),
    SECONDARY_FINANCING ("field@secondary-financing", ENUM, "secondary-financing"),
    TOTAL_SECONDARY_FINANCING_AMOUNT ("field@total-secondary-financing-amount", NUMBER),
    HELOC_DRAW_AMOUNT ("field@heloc-draw-amount", NUMBER),
    HELOC_MAXIMUM_BALANCE ("field@heloc-maximum-balance", NUMBER),
    SELF_EMPLOYED ("field@self-employed", ENUM, "yes-no"),
    TOTAL_MONTHLY_INCOME ("field@total-monthly-income", NUMBER),
    TOTAL_LIABILITIES_MONTHLY_PAYMENT ("field@total-liabilities-monthly-payment", NUMBER),
    MONTHS_OF_RESERVES ("field@months-of-reserves", NUMBER),
    NUMBER_MTG_LATES_IN_LAST_12_MONTHS ("field@number-mtg-lates-in-last-12-months", ENUM, "mortgage-lates-in-last-12-mos"),
    FORBEARANCE ("field@forbearance", ENUM, "forbearance"),
    BANKRUPTCY ("field@bankruptcy", ENUM, "bankruptcy"),
    FORECLOSURE ("field@foreclosure", ENUM, "foreclosure"),
    SHORT_SALES ("field@short-sales", ENUM, "short-sales"),
    DEED_IN_LIEU ("field@deed-in-lieu", ENUM, "deed-in-lieu"),
    ADVERSE_ACCOUNTS ("field@adverse-accounts", ENUM, "adverse-accounts"),
    RECENT_MORTGAGE_HISTORY ("field@recent-mortgage-history", ENUM, "recent-mortgage-history"),
    TAXES_INSURANCE_HOA ("field@taxes-insurance-hoa", NUMBER),
    GOVERNMENT_REFINANCE_TYPE ("field@government-refinance-type", ENUM, "government-refinance-type"),
    GOVERNMENT_REFINANCE_TYPE_OTHER_DESCRIPTION ("field@government-refinance-type-other-description", STRING),
    FHA_REFI_ENDORSEMENT_DATE_RANGE ("field@fha-refi-endorsement-date-range", ENUM, "fha-refi-endorsement-date-range"),
    VA_FIRST_TIME_USE ("field@va-first-time-use", ENUM, "yes-no"),
    VA_VETERAN_TYPE ("field@va-veteran-type", ENUM, "va-veteran-type"),
    FHA_ASSIGNMENT_DATE_RANGE ("field@fha-assignment-date-range", ENUM, "fha-assignment-date-range"),
    DOWN_PAYMENT_PERCENTAGE ("field@down-payment-percentage", NUMBER);

    public static final Map<String, KnownFieldId> VALUE2ENUM
            = ValueObservable.createMapValue2Obj(KnownFieldId.values());

    private final String value;

    private final FieldValueTypeOpt fieldValueType;

    private final String fieldValueRef;
    public boolean hasFieldValueRef() {return fieldValueRef != null; }

    KnownFieldId(String value
            , FieldValueTypeOpt fieldValueType) {
        this(value, fieldValueType, null);
    }

    KnownFieldId(String value
            , FieldValueTypeOpt fieldValueType
            , String fieldValueRef) {
        this.value = value;
        this.fieldValueType = fieldValueType;
        this.fieldValueRef = fieldValueRef;
    }
}
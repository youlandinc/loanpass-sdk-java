package com.youland.vendor.loanpass.generated;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.youland.lib.core.serializer.ValueObservable;
import com.youland.lib.core.serializer.ValueObservableSerializer;
import com.youland.vendor.loanpass.model.field.FieldValueOpt;

import java.util.Map;

import lombok.Getter;
import lombok.ToString;

import static com.youland.vendor.loanpass.model.field.FieldValueOpt.DURATION;
import static com.youland.vendor.loanpass.model.field.FieldValueOpt.NUMBER;

/**
 * TODO ltang: Hand-coded until LoanPass exposes calculated fields
 */
@Getter
@ToString
@JsonSerialize(using = ValueObservableSerializer.class)
public enum KnownCalcFieldId implements ValueObservable<String> {
    LTC("calc@ltc", NUMBER),
    AFTER_REPAIR_LOAN_TO_VALUE ("calc@after-repair-loan-to-value", NUMBER),
    TOTAL_LOAN_AMOUNT ("calc@total-loan-amount", NUMBER),
    LTV("calc@ltv", NUMBER),
    HCLTV("calc@hcltv", NUMBER),
    CLTV("calc@cltv", NUMBER),
    LOAN_TERM ("calc@loan-term", DURATION),

    EST_PAYMENT ("calc@est-payment", NUMBER),
    INITIAL_IO_PAYMENT ("calc@initial-io-payment", NUMBER),
    INITIAL_PI_PAYMENT ("calc@initial-pi-payment", NUMBER),

    START_PRICE ("calc@start-price", NUMBER),
    START_INTEREST_RATE ("calc@start-interest-rate", NUMBER),

    ADJUSTED_INTEREST_RATE ("calc@adjusted-interest-rate", NUMBER),
    ADJUSTED_PRICE ("calc@adjusted-price", NUMBER),
    ADJUSTED_RATE_LOCK_PERIOD ("calc@adjusted-rate-lock-period", NUMBER);

    public static final Map<String, KnownCalcFieldId> VALUE2ENUM
            = ValueObservable.createMapValue2Obj(KnownCalcFieldId.values());

    private final String value;

    private final FieldValueOpt fieldValueType;

    private final String fieldValueRef;
    public boolean hasFieldValueRef() {return fieldValueRef != null; }

    KnownCalcFieldId(String value
            , FieldValueOpt fieldValueType) {
        this(value, fieldValueType, null);
    }

    KnownCalcFieldId(String value
            , FieldValueOpt fieldValueType
            , String fieldValueRef) {
        this.value = value;
        this.fieldValueType = fieldValueType;
        this.fieldValueRef = fieldValueRef;
    }
}
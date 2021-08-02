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
 * TODO ltang: Hand-coded until LoanPass exposes these field ids
 */
@Getter
@ToString
@JsonSerialize(using = ValueObservableSerializer.class)
public enum KnownPriceScenarioFieldId implements ValueObservable<String> {
    RATE_LOCK_PERIOD ("rate-lock-period", DURATION),
    BASE_PRICE("base-price", NUMBER),
    BASE_INTEREST_RATE("base-interest-rate", NUMBER);

    public static final Map<String, KnownPriceScenarioFieldId> VALUE2ENUM
            = ValueObservable.createMapValue2Obj(KnownPriceScenarioFieldId.values());

    private final String value;

    private final FieldValueOpt fieldValueType;

    private final String fieldValueRef;
    public boolean hasFieldValueRef() {return fieldValueRef != null; }

    KnownPriceScenarioFieldId(String value
            , FieldValueOpt fieldValueType) {
        this(value, fieldValueType, null);
    }

    KnownPriceScenarioFieldId(String value
            , FieldValueOpt fieldValueType
            , String fieldValueRef) {
        this.value = value;
        this.fieldValueType = fieldValueType;
        this.fieldValueRef = fieldValueRef;
    }
}
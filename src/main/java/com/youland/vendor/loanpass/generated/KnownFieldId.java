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
    //Count: 5
    LOAN_PURPOSE ("field@loan-purpose", ENUM, "loan-purpose"),
    BASE_LOAN_AMOUNT ("field@base-loan-amount", NUMBER),
    AFTER_REPAIR_VALUE_ARV ("field@after-repair-value-arv", NUMBER),
    STATE ("field@state", STRING),
    DESIRED_LOAN_TERM ("field@desired-loan-term", DURATION);

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
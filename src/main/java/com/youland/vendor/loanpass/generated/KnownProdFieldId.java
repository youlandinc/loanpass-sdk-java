package com.youland.vendor.loanpass.generated;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.youland.lib.core.serializer.ValueObservable;
import com.youland.lib.core.serializer.ValueObservableSerializer;
import com.youland.vendor.loanpass.model.field.FieldValueOpt;

import java.util.Map;

import lombok.Getter;
import lombok.ToString;

import static com.youland.vendor.loanpass.model.field.FieldValueOpt.DURATION;
import static com.youland.vendor.loanpass.model.field.FieldValueOpt.ENUM;

/**
 * TODO ltang: Hand-coded until this ticket resolved by LoanPass
 * https://loanpass.atlassian.net/servicedesk/customer/portal/2/LPS-358?created=true
 */
@Getter
@ToString
@JsonSerialize(using = ValueObservableSerializer.class)
public enum KnownProdFieldId implements ValueObservable<String> {
    PRODUCT_CATEGORY ("field@product-category", ENUM, "product-category"),
    PAYMENT_INTERVAL ("field@payment-interval", DURATION);

    public static final Map<String, KnownProdFieldId> VALUE2ENUM
            = ValueObservable.createMapValue2Obj(KnownProdFieldId.values());

    private final String value;

    private final FieldValueOpt fieldValueType;

    private final String fieldValueRef;
    public boolean hasFieldValueRef() {return fieldValueRef != null; }

    KnownProdFieldId(String value
            , FieldValueOpt fieldValueType) {
        this(value, fieldValueType, null);
    }

    KnownProdFieldId(String value
            , FieldValueOpt fieldValueType
            , String fieldValueRef) {
        this.value = value;
        this.fieldValueType = fieldValueType;
        this.fieldValueRef = fieldValueRef;
    }
}
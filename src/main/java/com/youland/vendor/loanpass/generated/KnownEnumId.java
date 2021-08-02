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
    //Count: 1
    LOAN_PURPOSE ("loan-purpose", Set.of("purchase","refinance","assumption","mortgage-modification"));

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
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

package gencode@package;

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
@javax.annotation.Generated(value="gencode@gen-tool", date="gencode@timestamp")
/**
 * Generated code from LoanPass API for static checking
 */
public enum gencode@class implements ValueObservable<String> {
    gencode@enum

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

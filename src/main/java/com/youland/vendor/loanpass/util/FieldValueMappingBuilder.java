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

package com.youland.vendor.loanpass.util;

import com.youland.vendor.loanpass.generated.KnownEnumId;
import com.youland.vendor.loanpass.generated.KnownFieldId;
import com.youland.vendor.loanpass.model.AnyOfFieldValue;
import com.youland.vendor.loanpass.model.DurationUnit;
import com.youland.vendor.loanpass.model.FieldValueMapping;
import com.youland.vendor.loanpass.model.FieldValueMapping.IBuilder;
import com.youland.vendor.loanpass.model.field.FieldValueAsDuration;
import com.youland.vendor.loanpass.model.field.FieldValueAsEnum;
import com.youland.vendor.loanpass.model.field.FieldValueAsNumber;
import com.youland.vendor.loanpass.model.field.FieldValueAsString;
import com.youland.vendor.loanpass.model.field.FieldValueTypeOpt;

import org.apache.commons.lang3.NotImplementedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.NonNull;

import static com.youland.vendor.loanpass.util.JavaGenCommon.toJavaVarNameReverse;

/**
 * Default builder
 */
public class FieldValueMappingBuilder implements FieldValueMapping.IBuilder{

    private Map<String, FieldValueMapping> fieldsMap = new HashMap<>();

    @Override
    public List<FieldValueMapping> build() {
        return new ArrayList<>(fieldsMap.values());
    }

    private IBuilder addField(@NonNull String fieldId, @NonNull AnyOfFieldValue field) {
        FieldValueMapping fieldMap = fieldsMap.get(fieldId);
        if (fieldMap != null) {
            String stError = String.format("Cannot add duplicated field id (%s). Existing field: %s"
                    , fieldId, fieldMap);
            throw new IllegalArgumentException(stError);
        }

        fieldMap = new FieldValueMapping(fieldId, field);
        fieldsMap.put(fieldId, fieldMap);
        return this;
    }

    @Override
    public IBuilder fieldEnum(@NonNull String fieldId, @NonNull String enumTypeId, @NonNull String variantId) {
        variantId = toJavaVarNameReverse(variantId);

        FieldValueAsEnum field = new FieldValueAsEnum(enumTypeId, variantId);
        return addField(fieldId, field);
    }

    @Override
    public IBuilder fieldNumber(@NonNull String fieldId, double value) {
        FieldValueAsNumber field = new FieldValueAsNumber(value);
        return addField(fieldId, field);
    }

    @Override
    public IBuilder fieldString(@NonNull String fieldId, String value) {
        FieldValueAsString field = new FieldValueAsString(value);
        return addField(fieldId, field);
    }

    @Override
    public IBuilder fieldDuration(@NonNull String fieldId, double count, DurationUnit unit) {
        FieldValueAsDuration field = new FieldValueAsDuration(count, unit);
        return addField(fieldId, field);
    }

    @Override
    public IBuilder fieldEnum(KnownFieldId fieldId, @NonNull String variantId) {
        FieldValueTypeOpt.ENUM.assertSame(fieldId.getFieldValueType(), fieldId.getValue());

        variantId = toJavaVarNameReverse(variantId);

        String enumTypeId = fieldId.getFieldValueRef();
        if (!KnownEnumId.isValidVariantId(variantId, enumTypeId))
            throw new IllegalArgumentException
                    (String.format("%s has a mismatch between enum id (%s) and variant id (%s)"
                            , fieldId.getValue()
                            , enumTypeId
                            , variantId));

        return this.fieldEnum(fieldId.getValue(), enumTypeId, variantId);
    }

    @Override
    public IBuilder fieldNumber(KnownFieldId fieldId, double value) {
        FieldValueTypeOpt.NUMBER.assertSame(fieldId.getFieldValueType(), fieldId.getValue());
        return this.fieldNumber(fieldId.getValue(), value);
    }

    @Override
    public IBuilder fieldString(KnownFieldId fieldId, String value) {
        FieldValueTypeOpt.STRING.assertSame(fieldId.getFieldValueType(), fieldId.getValue());
        return this.fieldString(fieldId.getValue(), value);
    }

    @Override
    public IBuilder fieldDuration(KnownFieldId fieldId, double count, DurationUnit unit) {
        FieldValueTypeOpt.DURATION.assertSame(fieldId.getFieldValueType(), fieldId.getValue());
        return this.fieldDuration(fieldId.getValue(), count, unit);
    }

    @Override
    public IBuilder field(KnownFieldId fieldId, @NonNull Object value, Object...extra) {
        FieldValueTypeOpt type = fieldId.getFieldValueType();
        try{
            switch (type) {
                case ENUM:
                    String variantId;
                    if (value instanceof Enum)
                        variantId = ((Enum) value).name();
                    else if (value instanceof Boolean)
                        //ltang: Map boolean to YesNo enum
                        variantId = KnownEnumId.boolToYesNo((boolean) value);
                    else
                        variantId = (String) value;

                    fieldEnum(fieldId, variantId);
                    break;
                case NUMBER:
                    if (value instanceof Integer)
                        fieldNumber(fieldId, (int) value);
                    else
                        fieldNumber(fieldId, (double) value);

                    break;
                case STRING:
                    fieldString(fieldId, (String) value);
                    break;
                case DURATION:
                    DurationUnit unit = DurationUnit.DAYS;
                    if (extra.length > 0) {
                        if (extra[0] != null) {
                            assert extra[0] instanceof DurationUnit
                                    : String.format(
                                    "Type mismatch. Expecting type of DurationUnit "
                                            + "vs. Actual type of %s",
                                    extra[0]);
                            unit = (DurationUnit) extra[0];
                        }
                    }

                    fieldDuration(fieldId, (double) value, unit);
                    break;
                default:
                    throw new NotImplementedException(String.format("Unknown type of '%s'", type));
            }

        } catch (AssertionError | NotImplementedException e) {
            throw e;
        }
        catch (Exception e) {
            String stError = String.format("fieldId=%s, type=%s, value=%s", fieldId, type, value);
            throw new RuntimeException(stError, e);
        }

        return this;
    }
}

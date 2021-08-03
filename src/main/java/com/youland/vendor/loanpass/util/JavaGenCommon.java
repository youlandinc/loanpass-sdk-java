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

import com.youland.vendor.loanpass.model.AnyOfFieldValueType;
import com.youland.vendor.loanpass.model.EnumType;
import com.youland.vendor.loanpass.model.EnumVariant;
import com.youland.vendor.loanpass.model.FieldDefinition;
import com.youland.vendor.loanpass.model.field.FieldValueTypeAsEnum;

import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.NonNull;

public class JavaGenCommon {
    public static final String TEMPLATE_TOKEN_PACKAGE_DEFAULT = "com.youland.vendor.loanpass.generated";

    public static final String TEMPLATE_TOKEN_TAG = "gencode@";
    public static final String TEMPLATE_TOKEN_PACKAGE = TEMPLATE_TOKEN_TAG + "package";
    public static final String TEMPLATE_TOKEN_CLASS = TEMPLATE_TOKEN_TAG + "class";
    public static final String TEMPLATE_TOKEN_GEN_TOOL = TEMPLATE_TOKEN_TAG + "gen-tool";
    public static final String TEMPLATE_TOKEN_TIMESTAMP = TEMPLATE_TOKEN_TAG + "timestamp";
    public static final String TEMPLATE_TOKEN_ENUM = TEMPLATE_TOKEN_TAG + "enum";

    public static final String FIELD_ID_PREFIX = "field@";
    public static final int FIELD_ID_PREFIX_LENGTH = FIELD_ID_PREFIX.length();

    public static final String CALC_FIELD_ID_PREFIX = "calc@";
    public static final int CALC_FIELD_ID_PREFIX_LENGTH = CALC_FIELD_ID_PREFIX.length();

    public static final char NAME_SEPARATOR_NON_JAVA = '-';
    public static final char NAME_SEPARATOR_JAVA = '_';

    public static String toLPFieldId(@NonNull String input) {
        input = toJavaVarNameReverse(input);
        return String.format("%s%s", FIELD_ID_PREFIX, input);
    }

    private static boolean IsVarNameStartingWithInt(@NonNull String input, String separator) {
        String[] words = input.split(separator);
        if (words == null || words.length <= 0)
            return false;
        String firstWord = words[0];
        try {
            Integer.parseInt(firstWord);
        } catch (Exception e)
        {
            return false;
        }
        return true;
    }

    /**
     * From LoanPass var name to Java compliant var name
     * @param input
     * @return
     */
    public static String toJavaVarName(@NonNull String input) {
        String output = input
                .toUpperCase()
                .replace(NAME_SEPARATOR_NON_JAVA, NAME_SEPARATOR_JAVA);

        //ltang: Handle the special case when name starts with a number, e.g., input = 3-month-bank-statement
        if (IsVarNameStartingWithInt(output, String.format("%s", NAME_SEPARATOR_JAVA))) {
            //ltang: Prefix with NAME_SEPARATOR_JAVA to make JAVA compliant
            output = NAME_SEPARATOR_JAVA + output;
        }

        return output;
    }

    /**
     * From Java var name to LoanPass
     * @param input
     * @return
     */
    public static String toJavaVarNameReverse(@NonNull String input) {
        //ltang: Handle the special case when name starts with a NAME_SEPARATOR_JAVA, e.g., input = _3_month_bank_statement
        int index = input.indexOf(NAME_SEPARATOR_JAVA);
        if (index == 0)
            //ltang: Remove NAME_SEPARATOR_JAVA to match with LoanPass
            input = input.substring(1);


        String output = input
                .toLowerCase()
                .replace(NAME_SEPARATOR_JAVA, NAME_SEPARATOR_NON_JAVA);


        return output;
    }

    public static Path getSavePath(@NonNull String className, String folder) {
        String filename = String.format("%s.java.codegen", className);
        Path path = folder == null ? Path.of(filename) : Paths.get(folder, filename);
        return path;
    }

    public static String fieldDefsToCode(Iterable<FieldDefinition> fields) {
        StringBuilder st = new StringBuilder();
        int count = 0;
        for (FieldDefinition field : fields) {
            AnyOfFieldValueType valueType = field.getValueType();

            String id = field.getId();
            String name = toJavaVarName(id.substring(FIELD_ID_PREFIX_LENGTH));

            //E.g.,
            //CHANNEL ("field@channel", ENUM, "channel"),
            //COUNTY ("field@county", STRING),
            st.append(String.format("%s (\"%s\", %s",
                    name
                    , id
                    , valueType.getType().name()));

            if (valueType instanceof FieldValueTypeAsEnum) {
                FieldValueTypeAsEnum valueTypeAsEnum = (FieldValueTypeAsEnum) valueType;
                st.append(String.format(", \"%s\"", valueTypeAsEnum.getEnumTypeId()));
            }

            st.append("),") //ltang: End the current line
                    .append(System.lineSeparator())
                    .append("\t");

            count++;
        }

        if (st.length() > 0) {
            // ltang: Remove from the last line: 1 tab, 1 new line, 1 comma
            st.delete(st.length() - 3, st.length());
            st.append(";");
        }

        st.insert(0, String.format("//Count: %d %s\t", count, System.lineSeparator()));
        return st.toString();
    }

    public static String enumVariantsToCode(Iterable<EnumVariant> enumVariants) {
        StringBuilder st = new StringBuilder();
        for (EnumVariant enumVariant : enumVariants) {
            String id = enumVariant.getId();
            st.append(String.format("\"%s\",", id));
        }

        if (st.length() > 0) {
            st.deleteCharAt(st.length() - 1);
            st.insert(0, "Set.of(");
            st.append(")");
        }

        return st.toString();
    }

    public static String enumTypesToCode(Iterable<EnumType> enumTypes) {
        StringBuilder st = new StringBuilder();
        int count = 0;
        for (EnumType enumType : enumTypes) {
            String id = enumType.getId();
            String name = toJavaVarName(id);

            //E.g.,
            //OCCUPANCY_TYPE("occupancy-type", Set.of("abc", "xyz"));
            st.append(String.format("%s (\"%s\", %s",
                    name
                    , id
                    , enumVariantsToCode(enumType.getVariants())));

            st.append("),") //ltang: End the current line
                    .append(System.lineSeparator())
                    .append("\t");

            count++;
        }

        if (st.length() > 0) {
            // ltang: Remove from the last line: 1 tab, 1 new line, 1 comma
            st.delete(st.length() - 3, st.length());
            st.append(";");
        }

        st.insert(0, String.format("//Count: %d %s\t", count, System.lineSeparator()));
        return st.toString();
    }
}

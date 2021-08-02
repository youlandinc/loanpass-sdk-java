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

import com.youland.lib.core.FileUtil;
import com.youland.vendor.loanpass.model.EnumType;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.time.Instant;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * Generate Java code from LoanPass API for static checking
 */
public class EnumType2JavaGen {
    private static String CODE_TEMPLATE;
    private static final String TEMPLATE_TOKEN_CLASS_DEFAULT = "KnownEnumId";
    private static final String TEMPLATE_FILENAME = String.format("templates/%s.java", TEMPLATE_TOKEN_CLASS_DEFAULT) ;

    @Getter @Setter private String saveFolder;

    @Getter private String packageName = JavaGenCommon.TEMPLATE_TOKEN_PACKAGE_DEFAULT;
    public void setPackageName(@NonNull String value) { packageName = value; }

    @Getter private String className = TEMPLATE_TOKEN_CLASS_DEFAULT;
    public void setClassName(@NonNull String value) { className = value; }

    private Iterable<EnumType> enumTypes;

    public EnumType2JavaGen(@NonNull Iterable<EnumType> enumTypes) {
        this.enumTypes = enumTypes;
    }

    private static synchronized String loadTemplateFromRes(String filename) throws IOException {
        if (CODE_TEMPLATE == null) {
            CODE_TEMPLATE =
                    FileUtil.readTextFromRes(
                            MethodHandles.lookup().lookupClass()
                            , filename);
        }
        return CODE_TEMPLATE;
    }

    public String generateCode(boolean bSaveToFile) throws IOException {
        //Step 1 of 3
        String template = loadTemplateFromRes(TEMPLATE_FILENAME);

        //Step 2 of 3
        String code = JavaGenCommon.enumTypesToCode(enumTypes);

        //Step 3 of 3
        code =
                template.replace(JavaGenCommon.TEMPLATE_TOKEN_PACKAGE, this.packageName)
                        .replace(JavaGenCommon.TEMPLATE_TOKEN_GEN_TOOL, getClass().getName())
                        .replace(JavaGenCommon.TEMPLATE_TOKEN_TIMESTAMP, Instant.now().toString())
                        .replace(JavaGenCommon.TEMPLATE_TOKEN_CLASS, this.className)
                        .replace(JavaGenCommon.TEMPLATE_TOKEN_ENUM, code);

        if (bSaveToFile)
            Files.write(JavaGenCommon.getSavePath(this.className, this.saveFolder), code.getBytes());

        return code;
    }

}

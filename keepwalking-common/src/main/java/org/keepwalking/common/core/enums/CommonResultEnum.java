/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.keepwalking.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.keepwalking.common.core.Result;

/**
 * 返回码枚举类
 * <p>
 * <模块名称-六位返回码>
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public enum CommonResultEnum implements Result {
    /**
     * 成功
     */
    SUCCESS("PUB-000000", "success"),
    /**
     * 失败
     */
    ERROR("PUB-500000", "error"),
    ;
    /**
     * 返回码
     */
    private final String code;
    /**
     * 返回信息
     */
    private final String message;

    @Override
    public String code() {
        return getCode();
    }

    @Override
    public String message() {
        return getMessage();
    }
}

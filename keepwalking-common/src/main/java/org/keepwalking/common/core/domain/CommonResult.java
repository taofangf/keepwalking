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

package org.keepwalking.common.core.domain;

import lombok.*;
import org.keepwalking.common.core.Result;
import org.keepwalking.common.core.enums.CommonResultEnum;

import java.io.Serializable;

/**
 * 公用返回结果类
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> implements Serializable {
    private static final long serialVersionUID = -8136061580166740309L;
    /**
     * 返回码
     */
    private String code;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回内容
     */
    private T data;

    /**
     * 公用返回构造
     *
     * @param result {@link Result}
     * @param data   {@link #data}
     */
    public CommonResult(Result result, T data) {
        this.code = result.code();
        this.message = result.message();
        this.data = data;
    }

    /**
     * 成功响应
     *
     * @param <T> {@link #data}
     * @return 成功响应
     */
    public static <T> CommonResult<T> success() {
        return new CommonResult(CommonResultEnum.SUCCESS, null);
    }

    /**
     * 成功响应
     *
     * @param data {@link #data}
     * @param <T>  {@link #data}
     * @return 成功响应
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult(CommonResultEnum.SUCCESS, data);
    }

    /**
     * 成功响应
     *
     * @param code    {@link #code}
     * @param message {@link #message}
     * @param data    {@link #data}
     * @param <T>     {@link #data}
     * @return 成功响应
     */
    public static <T> CommonResult<T> success(String code, String message, T data) {
        return new CommonResult(code, message, data);
    }

    /**
     * 失败响应
     *
     * @param <T> {@link #data}
     * @return 失败响应
     */
    public static <T> CommonResult<T> error() {
        return new CommonResult(CommonResultEnum.ERROR, null);
    }

    /**
     * 失败响应
     *
     * @param data {@link #data}
     * @param <T>  {@link #data}
     * @return 失败响应
     */
    public static <T> CommonResult error(T data) {
        return new CommonResult(CommonResultEnum.ERROR, data);
    }

    /**
     * 失败响应
     *
     * @param code    {@link #code}
     * @param message {@link #message}
     * @param data    {@link #data}
     * @param <T>     {@link #data}
     * @return 失败响应
     */
    public static <T> CommonResult<T> error(String code, String message, T data) {
        return new CommonResult(code, message, data);
    }
}
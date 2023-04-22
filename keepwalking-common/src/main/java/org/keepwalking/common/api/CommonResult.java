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

package org.keepwalking.common.api;

import org.keepwalking.common.enums.ResultCodeEnum;

/**
 * 公用返回结果类
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
public class CommonResult<T> {
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

    public CommonResult() {
    }

    public CommonResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功响应
     *
     * @param <T> {@link #data}
     * @return 成功响应
     */
    public static <T> CommonResult<T> success() {
        return new CommonResult(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), null);
    }

    /**
     * 成功响应
     *
     * @param data {@link #data}
     * @param <T>  {@link #data}
     * @return 成功响应
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), data);
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
        return new CommonResult(ResultCodeEnum.ERROR.getCode(), ResultCodeEnum.ERROR.getMessage(), null);
    }

    /**
     * 失败响应
     *
     * @param data {@link #data}
     * @param <T>  {@link #data}
     * @return 失败响应
     */
    public static <T> CommonResult error(T data) {
        return new CommonResult(ResultCodeEnum.ERROR.getCode(), ResultCodeEnum.ERROR.getMessage(), data);
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

    public String getCode() {
        return code;
    }

    public CommonResult<T> setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommonResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public CommonResult<T> setData(T data) {
        this.data = data;
        return this;
    }
}

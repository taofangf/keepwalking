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

/**
 * Api响应结果类
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
public class ApiResult<T> implements Result<T> {
    /**
     * 返回码
     *
     * @return 返回码
     */
    private String code;
    /**
     * 返回消息
     *
     * @return 返回消息
     */
    private String message;
    /**
     * 返回内容
     *
     * @return 返回内容
     */
    private T body;

    public String getCode() {
        return code;
    }

    public ApiResult<T> setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ApiResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getBody() {
        return body;
    }

    public ApiResult<T> setBody(T body) {
        this.body = body;
        return this;
    }

    @Override
    public String code() {
        return getCode();
    }

    @Override
    public String message() {
        return getMessage();
    }

    @Override
    public T body() {
        return getBody();
    }
}

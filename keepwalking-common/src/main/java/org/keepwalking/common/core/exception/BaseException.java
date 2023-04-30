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

package org.keepwalking.common.core.exception;

import org.keepwalking.common.core.Result;

/**
 * 基础异常
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
public class BaseException extends RuntimeException {
    /**
     * 异常错误码
     */
    private final String code;

    /**
     * 异常错误信息
     */
    private final String message;

    /**
     * 基础异常构造
     *
     * @param code    {@link #code}
     * @param message {@link #message}
     */
    public BaseException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    /**
     * 基础异常构造
     *
     * @param result {@link Result}
     */
    public BaseException(Result result) {
        super(result.message());
        this.code = result.code();
        this.message = result.message();
    }
}

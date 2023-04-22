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

package org.keepwalking.util.desensitization.annotation;

import cn.hutool.core.util.DesensitizedUtil;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.keepwalking.util.desensitization.DesensitizedCustomizer;
import org.keepwalking.util.desensitization.jackson.JacksonDesensitizedValueSerializer;

import java.lang.annotation.*;

/**
 * 脱敏(模糊化)标记注解
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Inherited
@JacksonAnnotationsInside
@JsonSerialize(using = JacksonDesensitizedValueSerializer.class)
public @interface Desensitization {
    /**
     * 脱敏类型，优先级低于{@link #customize()}
     *
     * @return 脱敏类型枚举 默认类型为：DesensitizedUtil.DesensitizedType.FIRST_MASK
     * @see DesensitizedUtil.DesensitizedType
     */
    DesensitizedUtil.DesensitizedType value() default DesensitizedUtil.DesensitizedType.FIRST_MASK;

    /**
     * @return 自定义脱敏实现类
     * @see DesensitizedCustomizer
     */
    Class<? extends DesensitizedCustomizer> customize() default DesensitizedCustomizer.DefaultDesensitized.class;
}

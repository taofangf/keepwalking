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

package org.keepwalking.util.desensitization;

/**
 * 定制化脱敏接口,可拓展实现
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
public interface DesensitizedCustomizer {
    /**
     * 自定义实现脱敏
     *
     * @param object 原始数据
     * @return 脱敏后的数据
     */
    Object desensitized(Object object);

    /**
     * 默认脱敏，啥都不做原样返回
     */
    class DefaultDesensitized implements DesensitizedCustomizer {

        @Override
        public Object desensitized(Object object) {
            return object;
        }
    }
}

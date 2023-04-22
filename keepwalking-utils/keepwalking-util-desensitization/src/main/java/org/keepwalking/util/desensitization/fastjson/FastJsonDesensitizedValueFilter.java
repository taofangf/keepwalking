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

package org.keepwalking.util.desensitization.fastjson;

import cn.hutool.core.util.DesensitizedUtil;
import com.alibaba.fastjson.serializer.BeanContext;
import com.alibaba.fastjson.serializer.ContextValueFilter;
import org.keepwalking.util.desensitization.DesensitizedCustomizer;
import org.keepwalking.util.desensitization.annotation.Desensitization;

import java.util.Optional;

/**
 * FastJson Value处理过滤器
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
public class FastJsonDesensitizedValueFilter implements ContextValueFilter {
    @Override
    public Object process(BeanContext context, Object object, String name, Object value) {
        return Optional.ofNullable(context).map(v -> Optional.ofNullable(context.getAnnation(Desensitization.class)).map(t -> {
            if (t.customize() != DesensitizedCustomizer.DefaultDesensitized.class) {
                try {
                    return t.customize().newInstance().desensitized(value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return DesensitizedUtil.desensitized((CharSequence) value, t.value());
        }).orElse(value)).orElse(value);
    }
}

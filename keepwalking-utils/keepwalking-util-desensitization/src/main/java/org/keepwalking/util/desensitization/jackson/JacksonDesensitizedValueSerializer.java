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

package org.keepwalking.util.desensitization.jackson;

import cn.hutool.core.util.DesensitizedUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import org.keepwalking.util.desensitization.DesensitizedCustomizer;
import org.keepwalking.util.desensitization.annotation.Desensitization;

import java.io.IOException;
import java.util.Objects;

/**
 * Jackson实现字段值脱敏
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @see Desensitization
 * @since 1.0
 */
public class JacksonDesensitizedValueSerializer extends JsonSerializer<Object> implements ContextualSerializer {

    /**
     * 脱敏标记注解
     */
    private Desensitization desensitization;

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (desensitization.customize() != DesensitizedCustomizer.DefaultDesensitized.class) {
            try {
                gen.writeObject(desensitization.customize().newInstance().desensitized(value));
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        gen.writeString(DesensitizedUtil.desensitized((CharSequence) value, desensitization.value()));
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        if (Objects.nonNull(property)) {
            desensitization = property.getAnnotation(Desensitization.class);
            if (Objects.nonNull(desensitization)) {
                return this;
            }
            return prov.findValueSerializer(property.getType(), property);
        }
        return prov.findNullValueSerializer(null);
    }
}

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

import cn.hutool.core.util.DesensitizedUtil;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.keepwalking.util.desensitization.annotation.Desensitization;
import org.keepwalking.util.desensitization.fastjson.FastJsonDesensitizedValueFilter;

/**
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
public class DesensitizedCustomizerTest {
    @Test
    public void fastjsonDesensitizedTest() {
        UserInfo userInfo = new UserInfo().setUsername("贾维斯").setPassword("Abc123456").setAddress("武汉市东西湖区");
        String jsonString = JSONObject.toJSONString(userInfo, new FastJsonDesensitizedValueFilter());
        System.out.println("jsonString = " + jsonString);
    }

    public static class UserInfo {
        @Desensitization(DesensitizedUtil.DesensitizedType.CHINESE_NAME)
        private String username;

        @Desensitization(DesensitizedUtil.DesensitizedType.PASSWORD)
        private String password;

        @Desensitization(customize = HideDesensitized.class)
        private String address;

        public String getUsername() {
            return username;
        }

        public UserInfo setUsername(String username) {
            this.username = username;
            return this;
        }

        public String getPassword() {
            return password;
        }

        public UserInfo setPassword(String password) {
            this.password = password;
            return this;
        }

        public String getAddress() {
            return address;
        }

        public UserInfo setAddress(String address) {
            this.address = address;
            return this;
        }
    }

    public static class HideDesensitized implements DesensitizedCustomizer {

        @Override
        public Object desensitized(Object object) {
            return "哈哈哈哈哈哈";
        }
    }
}
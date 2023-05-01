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

package org.keepwalking.sysmgr.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据访问范围枚举
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public enum DataScopeEnum {
    /**
     * 所有数据权限
     */
    ALL(1),
    /**
     * 指定部门数据权限
     */
    DEPT_CUSTOM(2),
    /**
     * 当前部门数据权限
     */
    DEPT_ONLY(3),
    /**
     * 当前部门及以下部门数据权限
     */
    DEPT_AND_CHILD(4);
    /**
     * 数据访问范围
     */
    private final Integer scope;
}
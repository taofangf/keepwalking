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

package org.keepwalking.sysmgr.repository.permission;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.keepwalking.sysmgr.repository.base.BaseDO;

import java.util.Set;

/**
 * 角色DO
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
@TableName("system_role")
@Data
public class RoleDO extends BaseDO {
    private static final long serialVersionUID = 3408674840549826550L;
    /**
     * 角色ID
     */
    @TableId
    private Long id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色标识
     */
    private String code;
    /**
     * 角色排序
     */
    private Integer sort;
    /**
     * 角色状态 {@link org.keepwalking.common.core.enums.CommonStatusEnum}
     */
    private Integer status;
    /**
     * 角色类型 {@link org.keepwalking.sysmgr.enums.RoleTypeEnum}
     */
    private Integer type;
    /**
     * 备注
     */
    private String remark;
    /**
     * 数据范围 {@link org.keepwalking.sysmgr.enums.DataScopeEnum}
     */
    private Integer dataScope;
    /**
     * 数据范围(指定部门数组)
     * <p>
     * 适用于 {@link #dataScope} 的值为 {@link org.keepwalking.sysmgr.enums.DataScopeEnum#DEPT_CUSTOM} 时
     */
    private Set<Long> dataScopeDeptIds;
}
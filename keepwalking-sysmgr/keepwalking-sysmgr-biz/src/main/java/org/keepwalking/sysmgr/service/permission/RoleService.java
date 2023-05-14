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

package org.keepwalking.sysmgr.service.permission;

import org.keepwalking.common.core.domain.PageResult;
import org.keepwalking.sysmgr.controller.permission.vo.role.RoleCreateReqVO;
import org.keepwalking.sysmgr.controller.permission.vo.role.RolePageReqVO;
import org.keepwalking.sysmgr.controller.permission.vo.role.RoleUpdateReqVO;
import org.keepwalking.sysmgr.repository.permission.RoleDO;

import java.util.Collection;

/**
 * 角色Service
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
public interface RoleService {
    /**
     * 创建角色
     *
     * @param vo       {@link RoleCreateReqVO}
     * @param roleType 角色类型 {@link org.keepwalking.sysmgr.enums.RoleTypeEnum}
     * @return 角色编号
     */
    Long createRole(RoleCreateReqVO vo, Integer roleType);

    /**
     * 更新角色
     *
     * @param vo {@link RoleUpdateReqVO}
     */
    void updateRole(RoleUpdateReqVO vo);

    /**
     * 删除角色
     *
     * @param id 角色编号
     */
    void deleteRole(Long id);

    /**
     * 获得角色
     *
     * @param id 角色编号
     * @return 角色
     */
    RoleDO getRole(Long id);

    /**
     * 获得角色，从缓存中
     *
     * @param id 角色编号
     * @return 角色
     */
    RoleDO getRoleFromCache(Long id);

    /**
     * 获得角色分页
     *
     * @param vo {@link RolePageReqVO}
     * @return 角色分页结果
     */
    PageResult<RoleDO> getRolePage(RolePageReqVO vo);

    /**
     * 校验角色们是否有效。如下情况，视为无效：
     * 1. 角色编号不存在
     * 2. 角色被禁用
     *
     * @param ids 角色编号数组
     */
    void validateRoleList(Collection<Long> ids);
}
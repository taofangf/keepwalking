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

import lombok.extern.slf4j.Slf4j;
import org.keepwalking.common.core.domain.PageResult;
import org.keepwalking.sysmgr.controller.permission.vo.role.RoleCreateReqVO;
import org.keepwalking.sysmgr.controller.permission.vo.role.RolePageReqVO;
import org.keepwalking.sysmgr.controller.permission.vo.role.RoleUpdateReqVO;
import org.keepwalking.sysmgr.repository.permission.RoleDO;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * 角色Service实现类
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    @Override
    public Long createRole(RoleCreateReqVO roleCreateReqVO, Integer roleType) {
        return null;
    }

    @Override
    public void updateRole(RoleUpdateReqVO roleUpdateReqVO) {

    }

    @Override
    public void deleteRole(Long id) {

    }

    @Override
    public RoleDO getRole(Long id) {
        return null;
    }

    @Override
    public RoleDO getRoleFromCache(Long id) {
        return null;
    }

    @Override
    public PageResult<RoleDO> getRolePage(RolePageReqVO rolePageReqVO) {
        return null;
    }

    @Override
    public void validateRoleList(Collection<Long> ids) {

    }
}
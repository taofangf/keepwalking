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
import org.keepwalking.sysmgr.repository.permission.MenuDO;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 权限Service接口实现类
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
@Service
@Slf4j
public class PermissionServiceImpl implements PermissionService {
    @Override
    public Set<Long> getUserRoleIdListByUserId(Long userId) {
        return null;
    }

    @Override
    public Set<Long> getUserRoleIdListFromCache(Long userId, Collection<Integer> roleStatuses) {
        return null;
    }

    @Override
    public Set<Long> getRoleMenuIds(Long roleId) {
        return null;
    }

    @Override
    public Set<Long> getUserRoleIdListByRoleIds(Collection<Long> roleIds) {
        return null;
    }

    @Override
    public List<MenuDO> getRoleMenuListFromCache(Collection<Long> roleIds, Collection<Integer> menuTypes, Collection<Integer> menuStatuses) {
        return null;
    }

    @Override
    public void assignUserRole(Long userId, Set<Long> roleIds) {

    }

    @Override
    public void assignRoleMenu(Long roleId, Set<Long> menuIds) {

    }

    @Override
    public void assignRoleDataScope(Long roleId, Integer dataScope, Set<Long> dataScopeDeptIds) {

    }

    @Override
    public void processRoleDeleted(Long roleId) {

    }

    @Override
    public void processMenuDeleted(Long menuId) {

    }

    @Override
    public void processUserDeleted(Long userId) {

    }

    @Override
    public boolean hasAnyPermissions(Long userId, String... permissions) {
        return false;
    }

    @Override
    public boolean hasAnyRoles(Long userId, String... roles) {
        return false;
    }
}
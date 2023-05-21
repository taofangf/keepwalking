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

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.keepwalking.sysmgr.repository.permission.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 权限Service接口实现类
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
@Service
@Slf4j
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Override
    public Set<Long> getUserRoleIdListByUserId(Long userId) {
        return userRoleMapper.selectListByUserId(userId)
                .stream()
                .map(UserRoleDO::getRoleId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Long> getUserRoleIdListFromCache(Long userId, Collection<Integer> roleStatuses) {
        return null;
    }

    @Override
    public Set<Long> getRoleMenuIds(Long roleId) {
        return roleMenuMapper.selectListByRoleId(roleId).stream()
                .map(RoleMenuDO::getRoleId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Long> getUserRoleIdListByRoleIds(Collection<Long> roleIds) {
        return userRoleMapper.selectListByRoleIds(roleIds).stream()
                .map(UserRoleDO::getRoleId)
                .collect(Collectors.toSet());
    }

    @Override
    public List<MenuDO> getRoleMenuListFromCache(Collection<Long> roleIds, Collection<Integer> menuTypes, Collection<Integer> menuStatuses) {
        // TODO: 2023/5/14 缓存后面实现
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignUserRole(Long userId, Set<Long> roleIds) {
        // 查询已存在的用户角色编号集合
        Set<Long> userRoleIds = userRoleMapper.selectListByUserId(userId).stream()
                .map(UserRoleDO::getRoleId).filter(Objects::nonNull).collect(Collectors.toSet());
        // 将传入的角色编号集合与数据库存在的用户角色编号集合比较，如果是数据库中不存在则新增用户角色关联
        Collection<Long> createRoleIds = CollUtil.subtract(roleIds, userRoleIds);
        // 反之，集合取差集将数据库中存在但是传入的用户角色不存在即为需要删除的用户角色
        Collection<Long> deleteRoleIds = CollUtil.subtract(userRoleIds, roleIds);

        if (CollectionUtil.isNotEmpty(createRoleIds)) {
            userRoleMapper.insertBatch(createRoleIds.stream()
                    .map(v -> {
                        UserRoleDO userRoleDO = new UserRoleDO();
                        userRoleDO.setUserId(userId);
                        userRoleDO.setRoleId(v);
                        return userRoleDO;
                    }).collect(Collectors.toList()));
        }
        if (CollectionUtil.isNotEmpty(deleteRoleIds)) {
            userRoleMapper.deleteListByUserIdAndRoleIdIds(userId, deleteRoleIds);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignRoleMenu(Long roleId, Set<Long> menuIds) {
        Set<Long> roleMenuIds = roleMenuMapper.selectListByRoleId(roleId).stream()
                .map(RoleMenuDO::getRoleId).filter(Objects::nonNull).collect(Collectors.toSet());
        // 将传入的角色菜单集合与数据库存在的角色菜单编号集合对比，如果数据库中不存在则为新增的角色菜单
        Collection<Long> createRoleMenuIds = CollUtil.subtract(menuIds, roleMenuIds);
        // 反之即为需要删除的角色菜单
        Collection<Long> deleterRoleMenuIds = CollUtil.subtract(roleMenuIds, menuIds);
        if (CollectionUtil.isNotEmpty(createRoleMenuIds)) {
            roleMenuMapper.insertBatch(createRoleMenuIds.stream()
                    .map(v -> {
                        RoleMenuDO roleMenuDO = new RoleMenuDO();
                        roleMenuDO.setRoleId(roleId);
                        roleMenuDO.setMenuId(v);
                        return roleMenuDO;
                    }).collect(Collectors.toList()));
        }
        if (CollectionUtil.isNotEmpty(deleterRoleMenuIds)) {
            userRoleMapper.deleteListByUserIdAndRoleIdIds(roleId, deleterRoleMenuIds);
        }
    }

    @Override
    public void assignRoleDataScope(Long roleId, Integer dataScope, Set<Long> dataScopeDeptIds) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void processRoleDeleted(Long roleId) {
        userRoleMapper.deleteListByRoleId(roleId);
        roleMenuMapper.deleteListByRoleId(roleId);
    }

    @Override
    public void processMenuDeleted(Long menuId) {
        roleMenuMapper.deleteListByMenuId(menuId);
    }

    @Override
    public void processUserDeleted(Long userId) {
        userRoleMapper.deleteListByUserId(userId);
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
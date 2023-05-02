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

import org.keepwalking.sysmgr.repository.permission.MenuDO;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 权限Service接口
 * <p>
 * 用户-角色 1->N
 * 角色-菜单 1—>N
 * 角色-部门
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
public interface PermissionService {
    /**
     * 通过用户编号查询拥有的角色编号集合
     * <p>
     * 一个用户同时拥有多个角色
     *
     * @param userId 用户编号
     * @return 角色编号集合
     */
    Set<Long> getUserRoleIdListByUserId(Long userId);

    /**
     * 通过用户编号查询对应的角色编号集合
     * <p>
     * 一个用户同时拥有多个角色
     *
     * @param userId       用户编号
     * @param roleStatuses 角色状态 {@link org.keepwalking.common.core.enums.CommonStatusEnum}
     * @return 角色编号集合
     * @see #getUserRoleIdListByUserId(Long)
     */
    Set<Long> getUserRoleIdListFromCache(Long userId, Collection<Integer> roleStatuses);

    /**
     * 角色编号拥有的菜单编号集合
     * <p>
     * 一个角色所拥有的所有菜单列表
     *
     * @param roleId 角色编号
     * @return 菜单编号集合
     */
    Set<Long> getRoleMenuIds(Long roleId);

    /**
     * 获得拥有多个角色的用户编号集合
     *
     * @param roleIds 角色编号集合
     * @return 用户编号集合
     */
    Set<Long> getUserRoleIdListByRoleIds(Collection<Long> roleIds);

    /**
     * 获取角色对应的菜单列表（任意参数为空则返回空）
     *
     * @param roleIds      角色编号
     * @param menuTypes    菜单类型 {@link org.keepwalking.sysmgr.enums.MenuTypeEnum}
     * @param menuStatuses 菜单状态 {@link org.keepwalking.common.core.enums.CommonStatusEnum}
     * @return 菜单列表
     */
    List<MenuDO> getRoleMenuListFromCache(Collection<Long> roleIds, Collection<Integer> menuTypes,
                                          Collection<Integer> menuStatuses);

    /**
     * 设置用户角色
     * <p>
     * 用户绑定角色
     *
     * @param userId  角色编号
     * @param roleIds 角色编号集合
     */
    void assignUserRole(Long userId, Set<Long> roleIds);

    /**
     * 设置角色菜单
     * <p>
     * 角色添加菜单权限
     *
     * @param roleId  角色编号
     * @param menuIds 菜单编号集合
     */
    void assignRoleMenu(Long roleId, Set<Long> menuIds);

    /**
     * 设置角色的数据权限
     * <p>
     * 角色添加数据访问权限
     *
     * @param roleId           角色编号
     * @param dataScope        数据范围
     * @param dataScopeDeptIds 部门编号数组
     */
    void assignRoleDataScope(Long roleId, Integer dataScope, Set<Long> dataScopeDeptIds);

    /**
     * 处理角色删除时，删除关联授权数据
     *
     * @param roleId 角色编号
     */
    void processRoleDeleted(Long roleId);

    /**
     * 处理菜单删除时，删除关联授权数据
     *
     * @param menuId 菜单编号
     */
    void processMenuDeleted(Long menuId);

    /**
     * 处理用户删除是，删除关联授权数据
     *
     * @param userId 用户编号
     */
    void processUserDeleted(Long userId);

    /**
     * 判断是否有权限，任一一个即可
     *
     * @param userId      用户编号
     * @param permissions 权限
     * @return 是否
     */
    boolean hasAnyPermissions(Long userId, String... permissions);

    /**
     * 判断用户是否拥有角色编号，任一一个即可
     *
     * @param userId 用户编号
     * @param roles  角色数组
     * @return true or false
     */
    boolean hasAnyRoles(Long userId, String... roles);
}
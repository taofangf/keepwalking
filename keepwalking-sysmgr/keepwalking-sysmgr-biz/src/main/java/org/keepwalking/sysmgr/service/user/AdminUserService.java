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

package org.keepwalking.sysmgr.service.user;

import org.keepwalking.common.core.domain.PageResult;
import org.keepwalking.sysmgr.controller.user.vo.UserCreateReqVO;
import org.keepwalking.sysmgr.controller.user.vo.UserPageReqVO;
import org.keepwalking.sysmgr.controller.user.vo.UserUpdateReqVO;
import org.keepwalking.sysmgr.repository.user.AdminUserDO;

import java.util.Collection;
import java.util.List;

/**
 * 系统管理 用户Service
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
public interface AdminUserService {
    /**
     * 创建用户
     *
     * @param userCreateReqVO {@link UserCreateReqVO}
     * @return 用户编号
     */
    Long createUser(UserCreateReqVO userCreateReqVO);

    /**
     * 修改用户信息
     *
     * @param userUpdateReqVO {@link UserUpdateReqVO}
     */
    void updateUser(UserUpdateReqVO userUpdateReqVO);

    /**
     * 更新用户最后登录信息
     *
     * @param id      用户编号
     * @param loginIp 登录IP地址
     */
    void updateUserLogin(Long id, String loginIp);

    /**
     * 删除用户
     *
     * @param id 用户ID
     */
    void deleteUser(Long id);

    /**
     * 修改密码
     *
     * @param id       用户编号
     * @param password 密码
     */
    void updateUserPassword(Long id, String password);

    /**
     * 修改状态
     *
     * @param id     用户编号
     * @param status 状态
     */
    void updateUserStatus(Long id, Integer status);

    /**
     * 通过用户 ID 查询用户
     *
     * @param id 用户ID
     * @return 用户对象信息
     */
    AdminUserDO getUser(Long id);

    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return 用户对象信息
     */
    AdminUserDO getUserByUsername(String username);

    /**
     * 通过手机号获取用户
     *
     * @param mobile 手机号
     * @return 用户对象信息
     */
    AdminUserDO getUserByMobile(String mobile);

    /**
     * 获取用户分页列表
     *
     * @param userPageReqVO {@link UserPageReqVO}
     * @return 用户分页信息
     */
    PageResult<AdminUserDO> getUserPage(UserPageReqVO userPageReqVO);

    /**
     * 获得指定部门的用户数组
     *
     * @param deptIds 部门数组
     * @return 用户数组
     */
    List<AdminUserDO> getUserListByDeptIds(Collection<Long> deptIds);

    /**
     * 获得指定岗位的用户数组
     *
     * @param postIds 岗位数组
     * @return 用户数组
     */
    List<AdminUserDO> getUserListByPostIds(Collection<Long> postIds);

    /**
     * 获得用户列表
     *
     * @param ids 用户编号数组
     * @return 用户列表
     */
    List<AdminUserDO> getUserList(Collection<Long> ids);

    /**
     * 获得用户列表，基于昵称模糊匹配
     *
     * @param nickname 昵称
     * @return 用户列表
     */
    List<AdminUserDO> getUserListByNickname(String nickname);

    /**
     * 获得指定状态的用户列表
     *
     * @param status 状态 {@link org.keepwalking.common.core.enums.CommonStatusEnum}
     * @return 用户列表
     */
    List<AdminUserDO> getUserListByStatus(Integer status);

    /**
     * 校验用户们是否有效。如下情况，视为无效：
     * 1. 用户编号不存在
     * 2. 用户被禁用
     *
     * @param ids 用户编号数组
     */
    void validateUserList(Collection<Long> ids);
}
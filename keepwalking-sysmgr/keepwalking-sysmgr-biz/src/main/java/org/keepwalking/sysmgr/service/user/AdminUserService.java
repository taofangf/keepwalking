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

import org.keepwalking.sysmgr.controller.user.vo.UserCreateReqVO;
import org.keepwalking.sysmgr.controller.user.vo.UserUpdateReqVO;

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
}
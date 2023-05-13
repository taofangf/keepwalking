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

import lombok.extern.slf4j.Slf4j;
import org.keepwalking.common.core.domain.PageResult;
import org.keepwalking.sysmgr.controller.user.vo.UserCreateReqVO;
import org.keepwalking.sysmgr.controller.user.vo.UserPageReqVO;
import org.keepwalking.sysmgr.controller.user.vo.UserUpdateReqVO;
import org.keepwalking.sysmgr.repository.user.AdminUserDO;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * 系统管理 用户Service实现类
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
@Service
@Slf4j
public class AdminUserServiceImpl implements AdminUserService {

    @Override
    public Long createUser(UserCreateReqVO vo) {
        return null;
    }

    @Override
    public void updateUser(UserUpdateReqVO vo) {

    }

    @Override
    public void updateUserLogin(Long id, String loginIp) {

    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public void updateUserPassword(Long id, String password) {

    }

    @Override
    public void updateUserStatus(Long id, Integer status) {

    }

    @Override
    public AdminUserDO getUser(Long id) {
        return null;
    }

    @Override
    public AdminUserDO getUserByUsername(String username) {
        return null;
    }

    @Override
    public AdminUserDO getUserByMobile(String mobile) {
        return null;
    }

    @Override
    public PageResult<AdminUserDO> getUserPage(UserPageReqVO vo) {
        return null;
    }

    @Override
    public List<AdminUserDO> getUserListByDeptIds(Collection<Long> deptIds) {
        return null;
    }

    @Override
    public List<AdminUserDO> getUserListByPostIds(Collection<Long> postIds) {
        return null;
    }

    @Override
    public List<AdminUserDO> getUserList(Collection<Long> ids) {
        return null;
    }

    @Override
    public List<AdminUserDO> getUserListByNickname(String nickname) {
        return null;
    }

    @Override
    public List<AdminUserDO> getUserListByStatus(Integer status) {
        return null;
    }

    @Override
    public void validateUserList(Collection<Long> ids) {

    }
}

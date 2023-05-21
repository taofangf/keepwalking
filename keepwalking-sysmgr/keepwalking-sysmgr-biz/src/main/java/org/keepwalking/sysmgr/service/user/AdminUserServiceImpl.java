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

import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.keepwalking.common.core.domain.PageResult;
import org.keepwalking.common.core.enums.CommonStatusEnum;
import org.keepwalking.common.core.exception.ServiceException;
import org.keepwalking.sysmgr.constant.SysMgrErrorCode;
import org.keepwalking.sysmgr.controller.user.vo.UserCreateReqVO;
import org.keepwalking.sysmgr.controller.user.vo.UserPageReqVO;
import org.keepwalking.sysmgr.controller.user.vo.UserUpdateReqVO;
import org.keepwalking.sysmgr.convert.user.UserConvert;
import org.keepwalking.sysmgr.repository.dept.UserPostDO;
import org.keepwalking.sysmgr.repository.dept.UserPostMapper;
import org.keepwalking.sysmgr.repository.user.AdminUserDO;
import org.keepwalking.sysmgr.repository.user.AdminUserMapper;
import org.keepwalking.sysmgr.service.dept.DeptService;
import org.keepwalking.sysmgr.service.dept.PostService;
import org.keepwalking.sysmgr.service.permission.PermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 系统管理 用户Service实现类
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
@Service
@Slf4j
public class AdminUserServiceImpl implements AdminUserService {
    @Resource
    private AdminUserMapper userMapper;
    @Resource
    private PostService postService;
    @Resource
    private DeptService deptService;
    @Resource
    private UserPostMapper userPostMapper;
    @Resource
    private PermissionService permissionService;

    @Override
    public Long createUser(UserCreateReqVO vo) {
        validateUserDataForCreateOrUpdate(vo.getUsername(), vo.getEmail(), vo.getMobile(), vo.getDeptId(), vo.getPostIds());
        AdminUserDO user = UserConvert.INSTANCE.convert(vo);
        user.setStatus(CommonStatusEnum.ENABLE.getStatus());
        userMapper.insert(user);
        if (CollectionUtil.isNotEmpty(user.getPostIds())) {
            // 用户岗位关联信息
            userPostMapper.insertBatch(user.getPostIds().stream()
                    .map(v -> {
                        UserPostDO userPostDO = new UserPostDO();
                        userPostDO.setUserId(user.getId());
                        userPostDO.setPostId(v);
                        return userPostDO;
                    }).collect(Collectors.toList()));
        }
        return user.getId();
    }

    @Override
    public void updateUser(UserUpdateReqVO vo) {
        validateUserExist(vo.getId());
        validateUserDataForCreateOrUpdate(vo.getUsername(), vo.getEmail(), vo.getMobile(), vo.getDeptId(), vo.getPostIds());
        AdminUserDO userDO = UserConvert.INSTANCE.convert(vo);
        userMapper.updateById(userDO);
        // 更新用户岗位信息
        Set<Long> postIds = userPostMapper.selectListByUserId(vo.getId()).stream()
                .map(UserPostDO::getPostId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        // 将数据库查出的用户岗位ID和修改的用户岗位ID比较 如果数据库中不存在则为新增岗位
        Collection<Long> createPostIds = CollectionUtil.subtract(postIds, vo.getPostIds());
        // 反之则删除岗位
        Collection<Long> deletePostIds = CollectionUtil.subtract(postIds, vo.getPostIds());
        if (CollectionUtil.isNotEmpty(createPostIds)) {
            userPostMapper.insertBatch(createPostIds.stream().map(v -> {
                UserPostDO userPostDO = new UserPostDO();
                userPostDO.setUserId(vo.getId());
                userPostDO.setPostId(v);
                return userPostDO;
            }).collect(Collectors.toList()));
        }
        if (CollectionUtil.isNotEmpty(deletePostIds)) {
            userPostMapper.deleteByUserIdAndPostId(vo.getId(), deletePostIds);
        }
    }

    /**
     * 校验用户是否存在
     *
     * @param id 用户ID
     */
    private void validateUserExist(Long id) {
        Optional.ofNullable(userMapper.selectById(id))
                .orElseThrow(() -> new ServiceException(SysMgrErrorCode.USER_NOT_EXIST));
    }

    /**
     * 创建、修改用户数据校验
     *
     * @param username 用户名称
     * @param email    邮箱
     * @param mobile   电话号码
     * @param deptId   部门ID
     * @param postIds  岗位ID集合
     */
    private void validateUserDataForCreateOrUpdate(String username, String email, String mobile, Long deptId, Set<Long> postIds) {
        // 校验用户名
        Optional.ofNullable(userMapper.selectByUsername(username))
                .orElseThrow(() -> new ServiceException(SysMgrErrorCode.USER_USERNAME_EXIST));
        // 校验邮箱
        Optional.ofNullable(userMapper.selectByEmail(email))
                .orElseThrow(() -> new ServiceException(SysMgrErrorCode.USER_EMAIL_EXIST));
        // 校验手机号码
        Optional.ofNullable(userMapper.selectByMobile(mobile))
                .orElseThrow(() -> new ServiceException(SysMgrErrorCode.USER_MOBILE_EXIST));
        // 校验部门信息
        deptService.validateDeptList(Optional.ofNullable(deptId).map(Collections::singleton).orElse(Collections.emptySet()));
        // 校验岗位信息
        postService.validatePostList(postIds);
    }

    @Override
    public void updateUserLogin(Long id, String loginIp) {
        validateUserExist(id);
        userMapper.updateById(new AdminUserDO().setId(id).setLoginIp(loginIp).setLoginDate(LocalDateTime.now()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(Long id) {
        validateUserExist(id);
        userMapper.deleteById(id);
        // 用户岗位关联信息删除
        userPostMapper.deleteByUserId(id);
        // 用户关联角色信息删除
        permissionService.processUserDeleted(id);
        // 用户岗位信息关联信息删除
        userPostMapper.deleteByUserId(id);
    }

    @Override
    public void updateUserPassword(Long id, String password) {

    }

    @Override
    public void updateUserStatus(Long id, Integer status) {
        validateUserExist(id);
        AdminUserDO adminUserDO = new AdminUserDO().setId(id).setStatus(status);
        userMapper.updateById(adminUserDO);
    }

    @Override
    public AdminUserDO getUser(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public AdminUserDO getUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public AdminUserDO getUserByMobile(String mobile) {
        return userMapper.selectByMobile(mobile);
    }

    @Override
    public PageResult<AdminUserDO> getUserPage(UserPageReqVO vo) {
        return null;
    }

    @Override
    public List<AdminUserDO> getUserListByDeptIds(Collection<Long> deptIds) {
        if (CollectionUtil.isEmpty(deptIds)) {
            return Collections.emptyList();
        }
        return userMapper.selectListByDeptIds(deptIds);
    }

    @Override
    public List<AdminUserDO> getUserListByPostIds(Collection<Long> postIds) {
        if (CollectionUtil.isEmpty(postIds)) {
            return Collections.emptyList();
        }
        Set<Long> userIds = userPostMapper.selectListByPostIds(postIds).stream()
                .map(UserPostDO::getUserId)
                .filter(Objects::nonNull).collect(Collectors.toSet());
        return userMapper.selectBatchIds(userIds);
    }

    @Override
    public List<AdminUserDO> getUserList(Collection<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return userMapper.selectBatchIds(ids);
    }

    @Override
    public List<AdminUserDO> getUserListByNickname(String nickname) {
        return userMapper.selectListByNickname(nickname);
    }

    @Override
    public List<AdminUserDO> getUserListByStatus(Integer status) {
        return userMapper.selectListByStatus(status);
    }

    @Override
    public void validateUserList(Collection<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return;
        }
        Map<Long, AdminUserDO> userMap = userMapper.selectBatchIds(ids).stream()
                .collect(Collectors.toMap(AdminUserDO::getId, Function.identity(), (v1, v2) -> v1));
        ids.forEach(s -> {
            AdminUserDO user = Optional.ofNullable(userMap.get(s))
                    .orElseThrow(() -> new ServiceException(SysMgrErrorCode.USER_NOT_EXIST));
            if (!CommonStatusEnum.ENABLE.getStatus().equals(user.getStatus())) {
                throw new ServiceException(SysMgrErrorCode.DEPT_IS_DISABLE);
            }
        });
    }
}
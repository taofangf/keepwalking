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

package org.keepwalking.sysmgr.repository.user;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * 用户CRUD
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
@Mapper
public interface AdminUserMapper extends BaseMapper<AdminUserDO> {
    /**
     * 通过用户账号查询系统用户信息
     *
     * @param username 用户账号
     * @return {@link AdminUserDO}
     */
    default AdminUserDO selectByUsername(String username) {
        return selectOne(new LambdaQueryWrapper<AdminUserDO>().eq(AdminUserDO::getUsername, username));
    }

    /**
     * 通过邮箱查询系统用户信息
     *
     * @param email 邮箱
     * @return {@link AdminUserDO}
     */
    default AdminUserDO selectByEmail(String email) {
        return selectOne(new LambdaQueryWrapper<AdminUserDO>().eq(AdminUserDO::getEmail, email));
    }
    // TODO: 2023/5/3 用户分页和列表查询待处理

    /**
     * 通过用户昵称模糊查询用户列表
     *
     * @param nickname 用户昵称
     * @return 用户信息列表
     */
    default List<AdminUserDO> selectListByNickname(String nickname) {
        return selectList(new LambdaQueryWrapper<AdminUserDO>()
                .like(StrUtil.isNotEmpty(nickname), AdminUserDO::getNickname, nickname));
    }

    /**
     * 通过用户状态查询用户信息列表
     *
     * @param status 用户状态 {@link org.keepwalking.common.core.enums.CommonStatusEnum}
     * @return 用户信息列表
     */
    default List<AdminUserDO> selectListByStatus(Integer status) {
        return selectList(new LambdaQueryWrapper<AdminUserDO>().eq(AdminUserDO::getStatus, status));
    }

    /**
     * 通过部门ID查询用户信息列表
     *
     * @param deptIds 部门ID集合
     * @return 用户信息列表
     */
    default List<AdminUserDO> selectListByDeptIds(Collection<Long> deptIds) {
        return selectList(new LambdaQueryWrapper<AdminUserDO>().in(AdminUserDO::getDeptId, deptIds));
    }
}
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

package org.keepwalking.sysmgr.repository.permission;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.keepwalking.common.core.domain.PageResult;
import org.keepwalking.sysmgr.controller.permission.vo.role.RolePageReqVO;

import java.util.Collection;
import java.util.List;

/**
 * 角色表CRUD
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
@Mapper
public interface RoleMapper extends BaseMapper<RoleDO> {
    /**
     * 分页查询角色列表
     *
     * @param vo {@link RolePageReqVO}
     * @return 分页角色信息列表
     */
    default PageResult<RoleDO> selectPage(RolePageReqVO vo) {
        // TODO: 2023/5/3 分页后续完成
        return null;
    }

    /**
     * 通过角色名称查询角色信息
     *
     * @param name 角色名称
     * @return {@link RoleDO}
     */
    default RoleDO selectByName(String name) {
        return selectOne(new LambdaQueryWrapper<RoleDO>().eq(RoleDO::getName, name));
    }

    /**
     * 通过角色标识查询角色信息
     *
     * @param code 角色标识
     * @return {@link RoleDO}
     */
    default RoleDO selectByCode(String code) {
        return selectOne(new LambdaQueryWrapper<RoleDO>().eq(RoleDO::getCode, code));
    }

    /**
     * 通过角色状态查询角色列表
     *
     * @param statuses 角色状态列表
     * @return 角色列表
     */
    default List<RoleDO> selectListByStatus(Collection<Integer> statuses) {
        return selectList(new LambdaQueryWrapper<RoleDO>().in(RoleDO::getStatus, statuses));
    }
}
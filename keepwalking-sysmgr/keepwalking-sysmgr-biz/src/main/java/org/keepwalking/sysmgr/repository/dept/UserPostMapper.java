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

package org.keepwalking.sysmgr.repository.dept;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * 用户岗位关联表CRUD
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
@Mapper
public interface UserPostMapper extends BaseMapper<UserPostDO> {
    /**
     * 通过用户ID查询用户岗位关联列表
     *
     * @param userId 用户ID
     * @return 用户岗位关联列表
     */
    default List<UserPostDO> selectListByUserId(Long userId) {
        return selectList(new LambdaQueryWrapper<UserPostDO>().eq(UserPostDO::getUserId, userId));
    }

    /**
     * 通过用户ID和岗位编号删除用户岗位关联信息
     *
     * @param userId  用户ID
     * @param postIds 岗位编号列表
     */
    default void deleteByUserIdAndPostId(Long userId, Collection<Long> postIds) {
        delete(new LambdaQueryWrapper<UserPostDO>()
                .eq(UserPostDO::getUserId, userId)
                .in(UserPostDO::getPostId, postIds));
    }

    /**
     * 通过岗位编号集合查询用户岗位关联列表
     *
     * @param postIds 岗位编号集合
     * @return 用户岗位关联列表
     */
    default List<UserPostDO> selectListByPostIds(Collection<Long> postIds) {
        return selectList(new LambdaQueryWrapper<UserPostDO>().in(UserPostDO::getPostId, postIds));
    }

    /**
     * 通过用户ID删除用户岗位关联信息
     *
     * @param userId 用户ID
     */
    default void deleteByUserId(Long userId) {
        delete(Wrappers.lambdaUpdate(UserPostDO.class).eq(UserPostDO::getUserId, userId));
    }
}
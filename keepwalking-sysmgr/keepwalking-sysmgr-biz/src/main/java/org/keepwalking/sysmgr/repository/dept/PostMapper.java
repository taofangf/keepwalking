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

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.keepwalking.sysmgr.controller.dept.vo.PostListReqVO;

import java.util.Collection;
import java.util.List;

/**
 * 岗位表CRUD
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
@Mapper
public interface PostMapper extends BaseMapper<PostDO> {
    /**
     * 通过岗位序号和状态查询岗位信息列表
     *
     * @param ids      岗位集合
     * @param statuses 状态集合
     * @return 岗位列表
     */
    default List<PostDO> selectList(Collection<Long> ids, Collection<Integer> statuses) {
        return selectList(new LambdaQueryWrapper<PostDO>()
                .in(CollectionUtil.isNotEmpty(ids), PostDO::getId, ids)
                .in(CollectionUtil.isNotEmpty(statuses), PostDO::getStatus, statuses));
    }

    // TODO: 2023/5/3 分页待实现

    /**
     * 通过岗位名称查询岗位信息
     *
     * @param name 岗位名称
     * @return 岗位信息
     */
    default PostDO selectByName(String name) {
        return selectOne(new LambdaQueryWrapper<PostDO>().eq(PostDO::getName, name));
    }

    /**
     * 通过岗位编码查询岗位信息
     *
     * @param code 岗位编码
     * @return 岗位信息
     *
     */
    default PostDO selectByCode(String code) {
        return selectOne(new LambdaQueryWrapper<PostDO>().eq(PostDO::getCode, code));
    }

    /**
     * 查询岗位列表
     *
     * @param vo
     * @return 岗位列表
     */
    default List<PostDO> selectList(PostListReqVO vo) {
        return selectList(new LambdaQueryWrapper<PostDO>()
                .like(StrUtil.isNotEmpty(vo.getCode()), PostDO::getCode, vo.getCode())
                .like(StrUtil.isNotEmpty(vo.getName()), PostDO::getName, vo.getName())
                .eq(ObjectUtil.isNotEmpty(vo.getStatus()), PostDO::getCode, vo.getCode()));
    }
}
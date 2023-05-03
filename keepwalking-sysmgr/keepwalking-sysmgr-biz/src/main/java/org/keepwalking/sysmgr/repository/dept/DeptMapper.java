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

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.keepwalking.sysmgr.controller.dept.vo.DeptListReqVO;

import java.util.List;

/**
 * 部门表CRUD
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1, 0
 */
@Mapper
public interface DeptMapper extends BaseMapper<DeptDO> {
    /**
     * 部门列表查询（部门名称模糊匹配、部门状态）
     *
     * @param vo {@link DeptListReqVO}
     * @return 部门列表
     */
    default List<DeptDO> selectList(DeptListReqVO vo) {
        return selectList(new LambdaQueryWrapper<DeptDO>()
                .like(StrUtil.isNotEmpty(vo.getName()), DeptDO::getName, vo.getName())
                .eq(ObjectUtil.isNotEmpty(vo.getStatus()), DeptDO::getStatus, vo.getStatus()));
    }

    /**
     * 通过父部门ID和部门名称查询部门信息
     *
     * @param parentId 父部门ID
     * @param name     部门名称
     * @return 部门信息
     */
    default DeptDO selectByParentIdAndName(Long parentId, String name) {
        return selectOne(new LambdaQueryWrapper<DeptDO>().eq(DeptDO::getParentId, parentId).eq(DeptDO::getName, name));
    }

    /**
     * 通过父部门ID查询数量
     *
     * @param parentId 父部门ID
     * @return 部门数量
     */
    default Long selectCountByParentId(Long parentId) {
        return selectCount(new LambdaQueryWrapper<DeptDO>().eq(DeptDO::getParentId, parentId));
    }
}
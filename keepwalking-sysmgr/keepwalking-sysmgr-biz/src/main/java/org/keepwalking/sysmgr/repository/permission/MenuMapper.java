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

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.keepwalking.sysmgr.controller.permission.vo.menu.MenuListReqVO;

import java.util.List;

/**
 * 菜单CRUD实现类
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
@Mapper
public interface MenuMapper extends BaseMapper<MenuDO> {
    /**
     * 通过父菜单ID和菜单名称查询菜单信息
     *
     * @param parentId 父菜单ID
     * @param name     菜单名称
     * @return 菜单信息
     */
    default MenuDO selectByParentIdAndName(Long parentId, String name) {
        return selectOne(new LambdaQueryWrapper<MenuDO>().eq(MenuDO::getParentId, parentId).eq(MenuDO::getName, name));
    }

    /**
     * 通过父菜单ID查询数量
     *
     * @param parentId 父菜单ID
     * @return 菜单数量
     */
    default Long selectCountByParentId(Long parentId) {
        return selectCount(new LambdaQueryWrapper<MenuDO>().eq(MenuDO::getParentId, parentId));
    }

    /**
     * 根据条件查询菜单列表
     *
     * @param vo {@link MenuListReqVO}
     * @return 菜单列表
     */
    default List<MenuDO> selectList(MenuListReqVO vo) {
        return selectList(new LambdaQueryWrapper<MenuDO>()
                .like(StrUtil.isNotEmpty(vo.getName()), MenuDO::getName, vo.getName())
                .eq(ObjectUtil.isNotEmpty(vo.getStatus()), MenuDO::getStatus, vo.getStatus()));
    }
}
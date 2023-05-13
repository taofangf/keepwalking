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

package org.keepwalking.sysmgr.service.permission;

import org.keepwalking.sysmgr.controller.permission.vo.menu.MenuCreateReqVO;
import org.keepwalking.sysmgr.controller.permission.vo.menu.MenuListReqVO;
import org.keepwalking.sysmgr.controller.permission.vo.menu.MenuUpdateReqVO;
import org.keepwalking.sysmgr.repository.permission.MenuDO;

import java.util.List;

/**
 * 菜单Service
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
public interface MenuService {
    /**
     * 创建菜单
     *
     * @param vo {@link MenuCreateReqVO}
     * @return 菜单编号
     */
    Long createMenu(MenuCreateReqVO vo);

    /**
     * 更新菜单
     *
     * @param vo {@link MenuUpdateReqVO}
     */
    void updateMenu(MenuUpdateReqVO vo);

    /**
     * 删除菜单
     *
     * @param id 菜单编号
     */
    void deleteMenu(Long id);

    /**
     * 按条件筛选菜单列表
     *
     * @param vo {@link MenuListReqVO}
     * @return {@link MenuDO}
     */
    List<MenuDO> getMenuList(MenuListReqVO vo);

    /**
     * 获得所有菜单列表
     *
     * @return 菜单列表
     */
    List<MenuDO> getMenuList();

    /**
     * 获得菜单
     *
     * @param id 菜单编号
     * @return 菜单
     */
    MenuDO getMenu(Long id);

    /**
     * 获得权限对应的菜单列表
     *
     * @param permission 权限标识
     * @return 菜单列表
     */
    List<MenuDO> getMenuListByPermissionFromCache(String permission);
}
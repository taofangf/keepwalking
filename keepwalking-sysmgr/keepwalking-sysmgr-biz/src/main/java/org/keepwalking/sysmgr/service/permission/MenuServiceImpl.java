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

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.keepwalking.common.core.exception.ServiceException;
import org.keepwalking.sysmgr.constant.SysMgrErrorCode;
import org.keepwalking.sysmgr.controller.permission.vo.menu.MenuBaseVO;
import org.keepwalking.sysmgr.controller.permission.vo.menu.MenuCreateReqVO;
import org.keepwalking.sysmgr.controller.permission.vo.menu.MenuListReqVO;
import org.keepwalking.sysmgr.controller.permission.vo.menu.MenuUpdateReqVO;
import org.keepwalking.sysmgr.convert.permission.MenuConvert;
import org.keepwalking.sysmgr.enums.MenuTypeEnum;
import org.keepwalking.sysmgr.repository.permission.MenuDO;
import org.keepwalking.sysmgr.repository.permission.MenuMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * 菜单Services实现类
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
@Service
@Slf4j
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuMapper menuMapper;

    @Override
    public Long createMenu(MenuCreateReqVO vo) {
        if (ObjectUtil.isNotNull(vo.getParentId()) && !MenuBaseVO.MENU_ID_ROOT.equals(vo.getParentId())) {
            // 创建菜单校验父菜单
            validateParentMenu(vo.getParentId());
        }
        // 校验菜单名称是否重复
        Optional.ofNullable(menuMapper.selectByParentIdAndName(vo.getParentId(), vo.getName())).ifPresent(v -> {
            throw new ServiceException(SysMgrErrorCode.MENU_NAME_DUPLICATE);
        });
        MenuDO menu = MenuConvert.INSTANCE.convert(vo);
        menuMapper.insert(menu);
        return menu.getId();
    }

    @Override
    public void updateMenu(MenuUpdateReqVO vo) {
        Optional.ofNullable(menuMapper.selectById(vo.getId()))
                .orElseThrow(() -> new ServiceException(SysMgrErrorCode.MENU_NOT_EXIST));
        // 校验父菜单
        if (!ObjectUtil.isNull(vo.getParentId()) && !MenuBaseVO.MENU_ID_ROOT.equals(vo.getParentId())) {
            if (vo.getParentId().equals(vo.getId())) {
                throw new ServiceException(SysMgrErrorCode.MENU_PARENT_ERROR);
            }
            validateParentMenu(vo.getParentId());
        }
        // 校验菜单名称是否重复
        Optional.ofNullable(menuMapper.selectByParentIdAndName(vo.getParentId(), vo.getName())).ifPresent(v -> {
            if (!v.getId().equals(vo.getId())) {
                throw new ServiceException(SysMgrErrorCode.MENU_NAME_DUPLICATE);
            }
        });
        MenuDO menu = MenuConvert.INSTANCE.convert(vo);
        menuMapper.updateById(menu);
    }

    /**
     * 检验父菜单
     *
     * @param parentId 父菜单ID
     */
    private void validateParentMenu(Long parentId) {
        MenuDO parentMenu = Optional.ofNullable(menuMapper.selectById(parentId))
                .orElseThrow(() -> new ServiceException(SysMgrErrorCode.MENU_PARENT_NOT_EXIST));
        if (!MenuTypeEnum.DIR.getType().equals(parentMenu.getType()) ||
                !MenuTypeEnum.MENU.getType().equals(parentMenu.getType())) {
            throw new ServiceException(SysMgrErrorCode.MENU_PARENT_NOT_DIR_OR_MENU);
        }
    }

    @Override
    public void deleteMenu(Long id) {
        Optional.ofNullable(menuMapper.selectById(id))
                .orElseThrow(() -> new ServiceException(SysMgrErrorCode.MENU_NOT_EXIST));
        if (menuMapper.selectCountByParentId(id) > 0) {
            throw new ServiceException(SysMgrErrorCode.MENU_EXIST_CHILDREN);
        }
        menuMapper.deleteById(id);
        // TODO: 2023/5/13 删除菜单需同步删除对应的角色权限、事务处理
    }

    @Override
    public List<MenuDO> getMenuList(MenuListReqVO vo) {
        return menuMapper.selectList(vo);
    }

    @Override
    public List<MenuDO> getMenuList() {
        return menuMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public MenuDO getMenu(Long id) {
        return menuMapper.selectById(id);
    }

    @Override
    public List<MenuDO> getMenuListByPermissionFromCache(String permission) {
        return null;
    }
}
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

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.keepwalking.common.core.domain.PageResult;
import org.keepwalking.common.core.enums.CommonStatusEnum;
import org.keepwalking.common.core.exception.ServiceException;
import org.keepwalking.sysmgr.constant.SysMgrErrorCode;
import org.keepwalking.sysmgr.controller.permission.vo.role.RoleCreateReqVO;
import org.keepwalking.sysmgr.controller.permission.vo.role.RolePageReqVO;
import org.keepwalking.sysmgr.controller.permission.vo.role.RoleUpdateReqVO;
import org.keepwalking.sysmgr.convert.permission.RoleConvert;
import org.keepwalking.sysmgr.enums.DataScopeEnum;
import org.keepwalking.sysmgr.enums.RoleTypeEnum;
import org.keepwalking.sysmgr.repository.permission.RoleDO;
import org.keepwalking.sysmgr.repository.permission.RoleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 角色Service实现类
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private PermissionService permissionService;

    @Override
    public Long createRole(RoleCreateReqVO vo, Integer roleType) {
        // TODO: 2023/5/14 需要考虑是否可以创建管理员
        validateRoleNameAndCode(vo.getName(), vo.getCode());
        RoleDO role = RoleConvert.INSTANCE.convert(vo);
        role.setStatus(CommonStatusEnum.ENABLE.getStatus());
        role.setType(ObjectUtil.defaultIfNull(roleType, RoleTypeEnum.CUSTOM.getType()));
        role.setDataScope(DataScopeEnum.ALL.getScope());
        roleMapper.insert(role);
        return role.getId();
    }

    @Override
    public void updateRole(RoleUpdateReqVO vo) {
        validateRole(vo.getId());
        validateRoleNameAndCode(vo.getName(), vo.getCode());
        RoleDO role = RoleConvert.INSTANCE.convert(vo);
        roleMapper.updateById(role);
    }

    /**
     * 校验角色（角色不存在或者为系统角色均抛出异常）
     *
     * @param id 角色ID
     */
    private void validateRole(Long id) {
        RoleDO role = Optional.ofNullable(roleMapper.selectById(id))
                .orElseThrow(() -> new ServiceException(SysMgrErrorCode.ROLE_NOT_EXIST));
        if (RoleTypeEnum.SYSTEM.getType().equals(role.getType())) {
            throw new ServiceException(SysMgrErrorCode.ROLE_CAN_NOT_OPERATE);
        }
    }


    /**
     * 检验角色名称和角色编码实际否存在
     *
     * @param name 角色名称
     * @param code 角色编码
     */
    private void validateRoleNameAndCode(String name, String code) {
        Optional.ofNullable(roleMapper.selectByName(name))
                .orElseThrow(() -> new ServiceException(SysMgrErrorCode.ROLE_NAME_EXIST));
        if (StrUtil.isNotEmpty(code)) {
            Optional.ofNullable(roleMapper.selectByCode(code))
                    .orElseThrow(() -> new ServiceException(SysMgrErrorCode.ROLE_CODE_EXIST));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRole(Long id) {
        validateRole(id);
        roleMapper.deleteById(id);
        // 角色删除时对应删除角色权限关联
        permissionService.processRoleDeleted(id);
    }

    @Override
    public RoleDO getRole(Long id) {
        return roleMapper.selectById(id);
    }

    @Override
    public RoleDO getRoleFromCache(Long id) {
        return null;
    }

    @Override
    public PageResult<RoleDO> getRolePage(RolePageReqVO vo) {
        return null;
    }

    @Override
    public void validateRoleList(Collection<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return;
        }
        Map<Long, RoleDO> roleMap = roleMapper.selectBatchIds(ids).stream()
                .collect(Collectors.toMap(RoleDO::getId, Function.identity(), (v1, v2) -> v1));
        ids.forEach(v -> {
            RoleDO role = Optional.ofNullable(roleMap.get(v))
                    .orElseThrow(() -> new ServiceException(SysMgrErrorCode.ROLE_NOT_EXIST));
            if (!CommonStatusEnum.ENABLE.getStatus().equals(role.getStatus())) {
                throw new ServiceException(SysMgrErrorCode.ROLE_IS_DISABLE);
            }
        });
    }
}
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

package org.keepwalking.sysmgr.service.dept;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.keepwalking.common.core.enums.CommonStatusEnum;
import org.keepwalking.common.core.exception.ServiceException;
import org.keepwalking.sysmgr.constant.SysMgrErrorCode;
import org.keepwalking.sysmgr.controller.dept.vo.DeptBaseVO;
import org.keepwalking.sysmgr.controller.dept.vo.DeptCreateReqVO;
import org.keepwalking.sysmgr.controller.dept.vo.DeptListReqVO;
import org.keepwalking.sysmgr.controller.dept.vo.DeptUpdateReqVO;
import org.keepwalking.sysmgr.convert.dept.DeptConvert;
import org.keepwalking.sysmgr.repository.dept.DeptDO;
import org.keepwalking.sysmgr.repository.dept.DeptMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * 部门Service实现类
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
@Service
@Slf4j
public class DeptServiceImpl implements DeptService {
    @Resource
    private DeptMapper deptMapper;

    @Override
    public Long createDept(DeptCreateReqVO vo) {
        if (ObjectUtil.isNull(vo.getParentId())) {
            vo.setParentId(DeptBaseVO.DEPT_ID_ROOT);
        }
        validateDeptDataForCreateOrUpdate(null, vo.getParentId(), vo.getName());
        DeptDO deptDO = DeptConvert.INSTANCE.convert(vo);
        deptMapper.insert(deptDO);
        return deptDO.getId();
    }

    /**
     * 创建或修改部门数据校验
     *
     * @param id       部门ID
     * @param parentId 父部门ID
     * @param name     部门名称
     */
    private void validateDeptDataForCreateOrUpdate(Long id, Long parentId, String name) {
        validateDeptExists(id);
        if (parentId.equals(id)) {
            throw new ServiceException(SysMgrErrorCode.DEPT_PARENT_ERROR);
        }

        // 父部门校验
        DeptDO dept = Optional.ofNullable(deptMapper.selectById(parentId))
                .orElseThrow(() -> new ServiceException(SysMgrErrorCode.DEPT_PARENT_NOT_EXIST));
        if (!CommonStatusEnum.ENABLE.getStatus().equals(dept.getStatus())) {
            throw new ServiceException(SysMgrErrorCode.DEPT_NOT_ENABLE);
        }
        // TODO: 2023/5/13 父部门不能为子部门、需要递归查询
        /*
         * 1.创建新部门时、parentId为空时默认为0，否则为页面选择的父部门id，通过parentId和部门名称查出部门信息
         *  - 不存在校验通过（新创建的部门名称此时查不到正常）
         *  - 存在说明部门名称重复 (创建时id传的null)
         * 2.修改部门时、部门ID已存在数据库中，同一个父部门下子部门名称如果存在多个部门id则视为重复
         */
        Optional.ofNullable(deptMapper.selectByParentIdAndName(parentId, name)).ifPresent(v -> {
            if (!v.getId().equals(id) || ObjectUtil.isNull(id)) {
                throw new ServiceException(SysMgrErrorCode.DEPT_NAME_DUPLICATE);
            }
        });
    }

    /**
     * 校验部门是否存在
     *
     * @param id 部门ID
     */
    private void validateDeptExists(Long id) {
        if (ObjectUtil.isNotEmpty(id)) {
            // 不为空时为修改部门，根据ID无法查到部门信息则抛出异常
            Optional.ofNullable(deptMapper.selectById(id))
                    .orElseThrow(() -> new ServiceException(SysMgrErrorCode.DEPT_NOT_EXIST));
        }
    }

    @Override
    public void updateDept(DeptUpdateReqVO vo) {
        if (ObjectUtil.isNull(vo.getParentId())) {
            vo.setParentId(DeptBaseVO.DEPT_ID_ROOT);
        }
        validateDeptDataForCreateOrUpdate(vo.getId(), vo.getParentId(), vo.getName());
        DeptDO deptDO = DeptConvert.INSTANCE.convert(vo);
        deptMapper.updateById(deptDO);
    }

    @Override
    public void deleteDept(Long id) {
        validateDeptExists(id);
        if (deptMapper.selectCountByParentId(id) > 0) {
            throw new ServiceException(SysMgrErrorCode.DEPT_EXITS_CHILDREN);
        }
        deptMapper.deleteById(id);
    }

    @Override
    public List<DeptDO> getDeptList(DeptListReqVO vo) {
        return deptMapper.selectList(vo);
    }

    @Override
    public List<DeptDO> getDeptListByParentIdFromCache(Long parentId, boolean recursive) {
        // TODO: 2023/5/4 缓存后续处理，先实现基础功能
        return null;
    }

    @Override
    public DeptDO getDept(Long id) {
        return deptMapper.selectById(id);
    }
}
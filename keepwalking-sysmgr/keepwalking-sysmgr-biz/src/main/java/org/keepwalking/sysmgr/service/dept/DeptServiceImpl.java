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
import org.keepwalking.sysmgr.controller.dept.vo.DeptCreateReqVO;
import org.keepwalking.sysmgr.controller.dept.vo.DeptListReqVO;
import org.keepwalking.sysmgr.controller.dept.vo.DeptUpdateReqVO;
import org.keepwalking.sysmgr.convert.dept.DeptConvert;
import org.keepwalking.sysmgr.enums.DeptIdEnum;
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
    public Long createDept(DeptCreateReqVO deptCreateReqVO) {
        if (ObjectUtil.isNull(deptCreateReqVO.getParentId())) {
            deptCreateReqVO.setParentId(DeptIdEnum.ROOT.getId());
        }
        // TODO: 2023/5/4 数据校验逻辑
        DeptDO deptDO = DeptConvert.INSTANCE.convert(deptCreateReqVO);
        deptMapper.insert(deptDO);
        return deptDO.getId();
    }

    @Override
    public void updateDept(DeptUpdateReqVO deptUpdateReqVO) {
        if (ObjectUtil.isNull(deptUpdateReqVO.getParentId())) {
            deptUpdateReqVO.setParentId(DeptIdEnum.ROOT.getId());
        }
        // TODO: 2023/5/4 数据校验
        DeptDO deptDO = DeptConvert.INSTANCE.convert(deptUpdateReqVO);
        deptMapper.updateById(deptDO);
    }

    @Override
    public void deleteDept(Long id) {
        // TODO: 2023/5/4 数据校验
        if (deptMapper.selectCountByParentId(id) > 0) {
            // TODO: 2023/5/4 业务异常
        }
        deptMapper.deleteById(id);
    }

    @Override
    public List<DeptDO> getDeptList(DeptListReqVO deptListReqVO) {
        return deptMapper.selectList(deptListReqVO);
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
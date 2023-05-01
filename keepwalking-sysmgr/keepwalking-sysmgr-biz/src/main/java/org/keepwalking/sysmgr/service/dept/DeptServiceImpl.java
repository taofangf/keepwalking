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

import lombok.extern.slf4j.Slf4j;
import org.keepwalking.sysmgr.controller.dept.vo.DeptCreateReqVO;
import org.keepwalking.sysmgr.controller.dept.vo.DeptListReqVO;
import org.keepwalking.sysmgr.controller.dept.vo.DeptUpdateReqVO;
import org.keepwalking.sysmgr.repository.dept.DeptDO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门Service实现类
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
@Service
@Slf4j
public class DeptServiceImpl implements DeptService {
    @Override
    public Long createDept(DeptCreateReqVO deptCreateReqVO) {
        return null;
    }

    @Override
    public void updateDept(DeptUpdateReqVO deptUpdateReqVO) {

    }

    @Override
    public void deleteDept(Long id) {

    }

    @Override
    public List<DeptDO> getDeptList(DeptListReqVO deptListReqVO) {
        return null;
    }

    @Override
    public List<DeptDO> getDeptListByParentIdFromCache(Long parentId, boolean recursive) {
        return null;
    }

    @Override
    public DeptDO getDept(Long id) {
        return null;
    }
}
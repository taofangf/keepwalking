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

import org.junit.jupiter.api.Test;
import org.keepwalking.common.core.enums.CommonStatusEnum;
import org.keepwalking.sysmgr.controller.dept.vo.DeptListReqVO;
import org.keepwalking.sysmgr.controller.dept.vo.DeptUpdateReqVO;
import org.keepwalking.sysmgr.repository.dept.DeptDO;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 部门Service单元测试
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
@SpringBootTest
public class DeptServiceImplTest {

    @Resource
    private DeptService deptService;

    @Test
    public void createDept() {
    }

    @Test
    public void updateDept() {
        DeptUpdateReqVO updateReqVO = new DeptUpdateReqVO().setId(100L);
        updateReqVO.setName("新部门名称");
        deptService.updateDept(updateReqVO);
    }

    @Test
    public void deleteDept() {
        deptService.deleteDept(111L);
    }

    @Test
    public void getDeptList() {
        DeptListReqVO reqVO = new DeptListReqVO().setStatus(CommonStatusEnum.ENABLE.getStatus());
        List<DeptDO> deptList = deptService.getDeptList(reqVO);
        System.out.println("deptList = " + deptList);
    }

    @Test
    public void getDeptListByParentIdFromCache() {
    }

    @Test
    public void getDept() {
    }

    @Test
    public void validateDeptList() {
    }
}
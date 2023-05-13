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

import org.keepwalking.sysmgr.controller.dept.vo.DeptCreateReqVO;
import org.keepwalking.sysmgr.controller.dept.vo.DeptListReqVO;
import org.keepwalking.sysmgr.controller.dept.vo.DeptUpdateReqVO;
import org.keepwalking.sysmgr.repository.dept.DeptDO;

import java.util.List;

/**
 * 部门Service接口
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
public interface DeptService {
    /**
     * 创建部门
     *
     * @param vo {@link DeptCreateReqVO}
     * @return 部门编号
     */
    Long createDept(DeptCreateReqVO vo);

    /**
     * 更新部门
     *
     * @param vo {@link DeptUpdateReqVO}
     */
    void updateDept(DeptUpdateReqVO vo);

    /**
     * 删除部门
     *
     * @param id 部门ID
     */
    void deleteDept(Long id);

    /**
     * 获取部门列表
     *
     * @param vo {@link DeptListReqVO}
     * @return {@link DeptDO}
     */
    List<DeptDO> getDeptList(DeptListReqVO vo);

    /**
     * 获取子部门列表
     *
     * @param parentId  部门编号
     * @param recursive 是否递归
     * @return 子部门列表
     */
    List<DeptDO> getDeptListByParentIdFromCache(Long parentId, boolean recursive);

    /**
     * 获取部门信息
     *
     * @param id 部门编号
     * @return 部门信息
     */
    DeptDO getDept(Long id);
}
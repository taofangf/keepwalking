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

import org.keepwalking.sysmgr.controller.dept.vo.PostCreateReqVO;
import org.keepwalking.sysmgr.controller.dept.vo.PostExportReqVO;
import org.keepwalking.sysmgr.controller.dept.vo.PostListReqVO;
import org.keepwalking.sysmgr.controller.dept.vo.PostUpdateReqVO;
import org.keepwalking.sysmgr.repository.dept.PostDO;

import java.util.Collection;
import java.util.List;

/**
 * 岗位Service
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
public interface PostService {
    /**
     * 创建岗位
     *
     * @param vo {@link PostCreateReqVO}
     * @return 岗位编号
     */
    Long createPost(PostCreateReqVO vo);

    /**
     * 更新岗位
     *
     * @param vo {@link PostUpdateReqVO}
     */
    void updatePost(PostUpdateReqVO vo);

    /**
     * 删除岗位
     *
     * @param id 岗位编号
     */
    void deletePost(Long id);

    /**
     * 获取岗位列表
     *
     * @param vo {@link PostExportReqVO}
     * @return {@link PostDO}
     */
    List<PostDO> getPostList(PostExportReqVO vo);

    /**
     * 获取岗位信息
     *
     * @param id 岗位编号
     * @return {@link PostDO}
     */
    PostDO getPost(Long id);

    /**
     * 校验岗位信息（岗位不存在或者被禁用则抛出异常）
     *
     * @param ids 岗位编号集合
     */
    void validatePostList(Collection<Long> ids);
}
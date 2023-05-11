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
import org.keepwalking.sysmgr.controller.dept.vo.PostCreateReqVO;
import org.keepwalking.sysmgr.controller.dept.vo.PostListReqVO;
import org.keepwalking.sysmgr.controller.dept.vo.PostUpdateReqVO;
import org.keepwalking.sysmgr.convert.dept.PostConvert;
import org.keepwalking.sysmgr.repository.dept.PostDO;
import org.keepwalking.sysmgr.repository.dept.PostMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 岗位Service实现类
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
@Service
@Slf4j
public class PostServiceImpl implements PostService {
    @Resource
    private PostMapper postMapper;

    @Override
    public Long createPost(PostCreateReqVO postCreateReqVO) {
        // TODO: 2023/5/6 校验请求数据
        PostDO post = PostConvert.INSTANCE.convert(postCreateReqVO);
        postMapper.insert(post);
        return post.getId();
    }

    @Override
    public void updatePost(PostUpdateReqVO postUpdateReqVO) {
        // TODO: 2023/5/6 校验请求数据
        PostDO post = PostConvert.INSTANCE.convert(postUpdateReqVO);
        postMapper.updateById(post);
    }

    @Override
    public void deletePost(Long id) {
        // TODO: 2023/5/6 校验请求数据
        postMapper.deleteById(id);
    }

    @Override
    public List<PostDO> getPostList(PostListReqVO postListReqVO) {
        return postMapper.selectList(postListReqVO);
    }

    @Override
    public PostDO getPost(Long id) {
        return null;
    }

    @Override
    public void validatePostList(Collection<Long> ids) {

    }
}
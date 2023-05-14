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

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.keepwalking.common.core.enums.CommonStatusEnum;
import org.keepwalking.common.core.exception.ServiceException;
import org.keepwalking.sysmgr.constant.SysMgrErrorCode;
import org.keepwalking.sysmgr.controller.dept.vo.PostCreateReqVO;
import org.keepwalking.sysmgr.controller.dept.vo.PostExportReqVO;
import org.keepwalking.sysmgr.controller.dept.vo.PostUpdateReqVO;
import org.keepwalking.sysmgr.convert.dept.PostConvert;
import org.keepwalking.sysmgr.repository.dept.PostDO;
import org.keepwalking.sysmgr.repository.dept.PostMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    public Long createPost(PostCreateReqVO vo) {
        validatePostDataForCreateOrUpdate(null, vo.getName(), vo.getCode());
        PostDO post = PostConvert.INSTANCE.convert(vo);
        postMapper.insert(post);
        return post.getId();
    }

    /**
     * 创建或修改岗位数据校验
     *
     * @param id   岗位ID
     * @param name 岗位名称
     * @param code 岗位编码
     */
    private void validatePostDataForCreateOrUpdate(Long id, String name, String code) {
        validatePostExists(id);
        Optional.ofNullable(postMapper.selectByName(name)).ifPresent(v -> {
            if (!v.getId().equals(id) || ObjectUtil.isNull(id)) {
                throw new ServiceException(SysMgrErrorCode.POST_NAME_EXIST);
            }
        });
        Optional.ofNullable(postMapper.selectByCode(code)).ifPresent(v -> {
            if (!v.getId().equals(id) || ObjectUtil.isNull(id)) {
                throw new ServiceException(SysMgrErrorCode.POST_CODE_EXIST);
            }
        });
    }

    /**
     * 通过岗位ID检验岗位是否存在
     *
     * @param id 岗位ID
     */
    private void validatePostExists(Long id) {
        Optional.ofNullable(postMapper.selectById(id))
                .orElseThrow(() -> new ServiceException(SysMgrErrorCode.POST_NOT_EXIST));
    }

    @Override
    public void updatePost(PostUpdateReqVO vo) {
        validatePostDataForCreateOrUpdate(vo.getId(), vo.getName(), vo.getCode());
        PostDO post = PostConvert.INSTANCE.convert(vo);
        postMapper.updateById(post);
    }

    @Override
    public void deletePost(Long id) {
        validatePostExists(id);
        postMapper.deleteById(id);
    }

    @Override
    public List<PostDO> getPostList(PostExportReqVO vo) {
        return postMapper.selectList(vo);
    }

    @Override
    public PostDO getPost(Long id) {
        return postMapper.selectById(id);
    }

    @Override
    public void validatePostList(Collection<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return;
        }
        Map<Long, PostDO> postMap = postMapper.selectBatchIds(ids).stream()
                .collect(Collectors.toMap(PostDO::getId, Function.identity(), (v1, v2) -> v1));
        ids.forEach(v -> {
            PostDO post = Optional.ofNullable(postMap.get(v))
                    .orElseThrow(() -> new ServiceException(SysMgrErrorCode.POST_NOT_EXIST));
            if (!CommonStatusEnum.ENABLE.getStatus().equals(post.getStatus())) {
                throw new ServiceException(SysMgrErrorCode.POST_IS_DISABLE);
            }
        });
    }
}
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

package org.keepwalking.sysmgr.service.dict;

import org.keepwalking.common.core.domain.PageResult;
import org.keepwalking.sysmgr.controller.dict.vo.DictDataCreateReqVO;
import org.keepwalking.sysmgr.controller.dict.vo.DictDataPageReqVO;
import org.keepwalking.sysmgr.controller.dict.vo.DictDataUpdateReqVO;
import org.keepwalking.sysmgr.repository.dict.DictDataDO;

import java.util.Collection;
import java.util.List;

/**
 * 字典数据表 Service
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
public interface DictDataService {
    /**
     * 创建字典数据
     *
     * @param createReqVO {@link DictDataCreateReqVO}
     * @return 字典数据编号
     */
    Long createDictData(DictDataCreateReqVO createReqVO);

    /**
     * 更新字典数据
     *
     * @param updateReqVO {@link DictDataUpdateReqVO}
     */
    void updateDictData(DictDataUpdateReqVO updateReqVO);

    /**
     * 删除字典数据
     *
     * @param id 字典数据编号
     */
    void deleteDictData(Long id);

    /**
     * 获得字典数据列表
     *
     * @return 字典数据全列表
     */
    List<DictDataDO> getDictDataList();

    /**
     * 获得字典数据分页列表
     *
     * @param dataPageReqVO {@link DictDataPageReqVO}
     * @return 分页字典数据
     */
    PageResult<DictDataDO> getDictDataPage(DictDataPageReqVO dataPageReqVO);

    /**
     * 获得字典数据详情
     *
     * @param id 字典数据编号
     * @return 字典数据
     */
    DictDataDO getDictData(Long id);

    /**
     * 获得指定的字典数据
     *
     * @param dictType 字典类型
     * @param value    字典数据值
     * @return 字典数据
     */
    DictDataDO getDictData(String dictType, String value);

    /**
     * 校验字典数据们是否有效。如下情况，视为无效：
     * 1. 字典数据不存在
     * 2. 字典数据被禁用
     *
     * @param dictType 字典类型
     * @param values   字典数据值的数组
     */
    void validateDictDataList(String dictType, Collection<String> values);
}
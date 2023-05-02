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

import lombok.extern.slf4j.Slf4j;
import org.keepwalking.common.core.domain.PageResult;
import org.keepwalking.sysmgr.controller.dict.vo.DictDataCreateReqVO;
import org.keepwalking.sysmgr.controller.dict.vo.DictDataPageReqVO;
import org.keepwalking.sysmgr.controller.dict.vo.DictDataUpdateReqVO;
import org.keepwalking.sysmgr.convert.dict.DictDataConvert;
import org.keepwalking.sysmgr.repository.dict.DictDataDO;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * 字典数据表 Service 实现类
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
@Service
@Slf4j
public class DictDataServiceImpl implements DictDataService {

    @Override
    public Long createDictData(DictDataCreateReqVO reqVO) {
        // TODO: 2023/4/30 数据校验
        DictDataDO dictDataDO = DictDataConvert.INSTANCE.convert(reqVO);
        // TODO: 2023/4/29 VO转DO插入数据库
        return null;
    }

    @Override
    public void updateDictData(DictDataUpdateReqVO updateReqVO) {
        
    }

    @Override
    public void deleteDictData(Long id) {

    }

    @Override
    public List<DictDataDO> getDictDataList() {
        return null;
    }

    @Override
    public PageResult<DictDataDO> getDictDataPage(DictDataPageReqVO dataPageReqVO) {
        return null;
    }

    @Override
    public DictDataDO getDictData(Long id) {
        return null;
    }

    @Override
    public DictDataDO getDictData(String dictType, String value) {
        return null;
    }

    @Override
    public void validateDictDataList(String dictType, Collection<String> values) {

    }
}
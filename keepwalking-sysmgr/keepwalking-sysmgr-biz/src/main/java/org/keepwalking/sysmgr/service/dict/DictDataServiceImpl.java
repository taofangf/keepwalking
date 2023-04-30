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

import org.keepwalking.sysmgr.controller.dict.vo.DictDataCreateReqVO;
import org.keepwalking.sysmgr.convert.dict.DictDataConvert;
import org.keepwalking.sysmgr.repository.dict.DictDataDO;
import org.springframework.stereotype.Service;

/**
 * 字典数据表 Service 实现类
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
@Service
public class DictDataServiceImpl implements DictDataService {

    @Override
    public Long createDictData(DictDataCreateReqVO reqVO) {
        // TODO: 2023/4/30 数据校验
        DictDataDO dictDataDO = DictDataConvert.INSTANCE.convert(reqVO);
        // TODO: 2023/4/29 VO转DO插入数据库
        return null;
    }
}
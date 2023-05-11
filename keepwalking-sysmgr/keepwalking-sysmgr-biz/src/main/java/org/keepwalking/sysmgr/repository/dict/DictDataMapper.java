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

package org.keepwalking.sysmgr.repository.dict;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.keepwalking.sysmgr.controller.dict.vo.DictDataExportReqVO;

import java.util.Collection;
import java.util.List;

/**
 * 字典数据表CRUD
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
@Mapper
public interface DictDataMapper extends BaseMapper<DictDataDO> {
    /**
     * 通过字典类型和字典值查询字典数据
     *
     * @param dictType 字典类型
     * @param value    字典值
     * @return 字典数据
     */
    default DictDataDO selectByDictTypeAndValue(String dictType, String value) {
        return selectOne(new LambdaQueryWrapper<DictDataDO>().eq(DictDataDO::getDictType, dictType)
                .eq(DictDataDO::getValue, value));
    }

    /**
     * 通过字典类型和字典标签查询字典数据
     *
     * @param dictType 字典类型
     * @param label    字典标签
     * @return 字典数据
     */
    default DictDataDO selectByDictTypeAndLabel(String dictType, String label) {
        return selectOne(new LambdaQueryWrapper<DictDataDO>().eq(DictDataDO::getDictType, dictType)
                .eq(DictDataDO::getLabel, label));
    }

    /**
     * 通过字典类型和字典值集合查询字典数据列表
     *
     * @param dictType 字典类型
     * @param values   字典值集合
     * @return 字典数据列表
     */
    default List<DictDataDO> selectByDictTypeAndValues(String dictType, Collection<String> values) {
        return selectList(new LambdaQueryWrapper<DictDataDO>().eq(DictDataDO::getDictType, dictType)
                .in(DictDataDO::getValue, values));
    }

    /**
     * 通过字典类型统计字典数据数量
     *
     * @param dictType 字典类型
     * @return 数量
     */
    default long selectCountByDictType(String dictType) {
        return selectCount(new LambdaQueryWrapper<DictDataDO>().eq(DictDataDO::getDictType, dictType));
    }

    /**
     * 通过字典标签（模糊匹配）、字典类型、字典状态查询字典数据列表
     *
     * @param vo {@link DictDataExportReqVO}
     * @return 字典数据集合
     */
    default List<DictDataDO> selectList(DictDataExportReqVO vo) {
        return selectList(new LambdaQueryWrapper<DictDataDO>()
                .like(StrUtil.isNotEmpty(vo.getLabel()), DictDataDO::getLabel, vo.getLabel())
                .eq(StrUtil.isNotEmpty(vo.getDictType()), DictDataDO::getDictType, vo.getDictType())
                .eq(ObjectUtil.isNotEmpty(vo.getStatus()), DictDataDO::getStatus, vo.getStatus()));
    }
    // TODO: 2023/5/3 分页查询待统一处理
}
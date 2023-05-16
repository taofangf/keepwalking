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

package org.keepwalking.sysmgr.controller.dict.vo;

import lombok.Data;
import org.keepwalking.sysmgr.domain.PageParam;

import javax.validation.constraints.Size;

/**
 * 字典分页列表
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
@Data
public class DictDataPageReqVO extends PageParam {
    private static final long serialVersionUID = -5101443284664308269L;
    /**
     * 字典标签
     */
    @Size(max = 100, message = "字典标签长度不能超过100个字符")
    private String label;

    /**
     * 字典类型（模糊匹配）
     */
    @Size(max = 100, message = "字典类型类型长度不能超过100个字符")
    private String dictType;

    /**
     * 字典状态 {@link org.keepwalking.common.core.enums.CommonStatusEnum}
     */
    private Integer status;
}
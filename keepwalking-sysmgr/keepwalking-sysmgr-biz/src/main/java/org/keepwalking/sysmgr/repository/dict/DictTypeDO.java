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

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import org.keepwalking.sysmgr.repository.base.BaseDO;

import java.time.LocalDateTime;

/**
 * 字典类型表
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
@TableName("system_dict_type")
@KeySequence("system_dict_type_seq")
@Data
@Builder
public class DictTypeDO extends BaseDO {
    private static final long serialVersionUID = -5015435105419742387L;
    /**
     * 字典主键
     */
    @TableId
    private Long id;
    /**
     * 字典名称
     */
    private String name;
    /**
     * 字典类型
     */
    private String type;
    /**
     * 状态
     * <p>
     * 枚举 {@link org.keepwalking.common.core.enums.CommonStatusEnum}
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 删除时间
     */
    private LocalDateTime deletedTime;
}
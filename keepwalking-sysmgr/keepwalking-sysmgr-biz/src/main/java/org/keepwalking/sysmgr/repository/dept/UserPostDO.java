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

package org.keepwalking.sysmgr.repository.dept;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.keepwalking.sysmgr.repository.base.BaseDO;
import org.keepwalking.sysmgr.repository.user.AdminUserDO;

/**
 * 用户岗位关联表
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
@TableName("system_user_post")
@KeySequence("system_user_post_seq")
@Data
public class UserPostDO extends BaseDO {
    private static final long serialVersionUID = -742868325442415056L;
    /**
     * 自增主键
     */
    @TableId
    private Long id;
    /**
     * 用户 ID
     * <p>
     * 关联 {@link AdminUserDO#getId()}
     */
    private Long userId;
    /**
     * 角色 ID
     * <p>
     * 关联 {@link PostDO#getId()}
     */
    private Long postId;
}
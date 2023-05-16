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

package org.keepwalking.sysmgr.repository.permission;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.keepwalking.sysmgr.repository.base.BaseDO;

/**
 * 角色菜单关联表
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
@TableName("system_role_menu")
@Data
public class RoleMenuDO extends BaseDO {
    private static final long serialVersionUID = 5098946325475870337L;
    /**
     * 自增主键
     */
    @TableId
    private Long id;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 菜单ID
     */
    private Long menuId;
}
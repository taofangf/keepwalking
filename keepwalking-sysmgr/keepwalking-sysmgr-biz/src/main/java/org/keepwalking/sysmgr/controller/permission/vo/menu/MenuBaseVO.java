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

package org.keepwalking.sysmgr.controller.permission.vo.menu;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 菜单基础VO
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
@Data
public class MenuBaseVO {
    /**
     * 菜单编号 根节点
     */
    public static final Long MENU_ID_ROOT = 0L;
    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空")
    @Size(max = 50, message = "菜单名称长度不能超过50个字符")
    private String name;
    /**
     * 权限标识
     */
    @Size(max = 100)
    private String permission;
    /**
     * 菜单类型 {@link org.keepwalking.sysmgr.enums.MenuTypeEnum}
     */
    @NotNull(message = "菜单类型不能为空")
    private Integer type;
    /**
     * 菜单显示顺序
     */
    @NotNull(message = "显示顺序不能为空")
    private Integer sort;
    /**
     * 父菜单ID
     */
    @NotNull(message = "父菜单 ID 不能为空")
    private Long parentId;
    /**
     * 菜单路由地址 仅菜单类型为菜单或者目录时，才需要传
     */
    @Size(max = 200, message = "路由地址不能超过200个字符")
    private String path;
    /**
     * 菜单图标 仅菜单类型为菜单或者目录时，才需要传
     */
    private String icon;
    /**
     * 组件路径,仅菜单类型为菜单时，才需要传
     */
    @Size(max = 200, message = "组件路径不能超过255个字符")
    private String component;
    /**
     * 组件名
     */
    private String componentName;
    /**
     * 菜单状态 {@link org.keepwalking.common.core.enums.CommonStatusEnum}
     */
    @NotNull(message = "状态不能为空")
    private Integer status;
    /**
     * 菜单是否可见
     */
    private Boolean visible;
    /**
     * 是否缓存
     */
    private Boolean keepAlive;
    /**
     * 是否总是显示
     */
    private Boolean alwaysShow;
}
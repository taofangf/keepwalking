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
 * 菜单DO
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
@TableName("system_menu")
@Data
public class MenuDO extends BaseDO {
    private static final long serialVersionUID = 1778565683935813010L;
    /**
     * 菜单编号
     */
    @TableId
    private Long id;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 权限标识
     * <p>
     * 一般格式为：${系统}:${模块}:${操作}
     * 例如说：system:admin:add，即 system 服务的添加管理员。
     * <p>
     * 当我们把该 MenuDO 赋予给角色后，意味着该角色有该资源：
     * - 对于后端，配合注解，配置 API 接口需要该权限，从而对API接口进行权限控制。
     * - 对于前端，配合前端标签，配置按钮是否展示，避免用户没有该权限时，结果可以看到该操作。
     */
    private String permission;
    /**
     * 菜单类型
     * <p>
     * {@link org.keepwalking.sysmgr.enums.MenuTypeEnum}
     */
    private Integer type;
    /**
     * 显示顺序
     */
    private Integer sort;
    /**
     * 父菜单ID
     */
    private Long parentId;
    /**
     * 路由地址
     * <p>
     * 如果 path 为 http(s) 时，则它是外链
     */
    private String path;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 组件路径
     */
    private String component;
    /**
     * 组件名称
     */
    private String componentName;
    /**
     * 状态
     * <p>
     * 枚举 {@link org.keepwalking.common.core.enums.CommonStatusEnum}
     */
    private Integer status;
    /**
     * 是否可见
     * <p>
     * 只有菜单、目录使用
     * 当设置为 true 时，该菜单不会展示在侧边栏，但是路由还是存在。例如说，一些独立的编辑页面 /edit/1024 等等
     */
    private Boolean visible;
    /**
     * 是否缓存
     * <p>
     * 只有菜单、目录使用，否则使用 Vue 路由的 keep-alive 特性
     * 注意：如果开启缓存，则必须填写 {@link #componentName} 属性，否则无法缓存
     */
    private Boolean keepAlive;
    /**
     * 是否总是显示
     * <p>
     * 如果为 false 时，当该菜单只有一个子菜单时，不展示自己，直接展示子菜单
     */
    private Boolean alwaysShow;
}
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

package org.keepwalking.sysmgr.constant;

import org.keepwalking.common.core.ErrorCode;

/**
 * 系统管理错误码
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
public final class SysMgrErrorCode {
    /**
     * 父部门不存在
     */
    public static final ErrorCode DEPT_PARENT_NOT_EXIST = new ErrorCode("SYSMGR-100000", "父部门不存在");
    /**
     * 当前部门不存在
     */
    public static final ErrorCode DEPT_NOT_EXIST = new ErrorCode("SYSMGR-100001", "当前部门不存在");
    /**
     * 部门状态被禁用
     */
    public static final ErrorCode DEPT_IS_DISABLE = new ErrorCode("SYSMGR-100002", "部门状态被禁用");
    /**
     * 不能设置自己为父部门
     */
    public static final ErrorCode DEPT_PARENT_ERROR = new ErrorCode("SYSMGR-100003", "不能设置自己为父部门");
    /**
     * 部门名称重复
     */
    public static final ErrorCode DEPT_NAME_EXIST = new ErrorCode("SYSMGR-100004", "部门名称重复");
    /**
     * 该部门存在子部门，无法删除
     */
    public static final ErrorCode DEPT_EXITS_CHILDREN = new ErrorCode("SYSMGR-100005", "该部门存在子部门，无法删除");
    /**
     * 当前岗位不存在
     */
    public static final ErrorCode POST_NOT_EXIST = new ErrorCode("SYSMGR-100006", "当前岗位不存在");
    /**
     * 岗位名称重复
     */
    public static final ErrorCode POST_NAME_EXIST = new ErrorCode("SYSMGR-100007", "岗位名称重复");
    /**
     * 岗位编码重复
     */
    public static final ErrorCode POST_CODE_EXIST = new ErrorCode("SYSMGR-100008", "岗位编码重复");
    /**
     * 岗位状态被禁用
     */
    public static final ErrorCode POST_IS_DISABLE = new ErrorCode("SYSMGR-100009", "岗位状态被禁用");
    /**
     * 父菜单不存在
     */
    public static final ErrorCode MENU_PARENT_NOT_EXIST = new ErrorCode("SYSMGR-100010", "父菜单不存在");
    /**
     * 父菜单必须是目录或者菜单
     */
    public static final ErrorCode MENU_PARENT_NOT_DIR_OR_MENU = new ErrorCode("SYSMGR-100011", "父菜单必须是目录或者菜单");
    /**
     * 菜单名称已存在
     */
    public static final ErrorCode MENU_NAME_EXIST = new ErrorCode("SYSMGR-100012", "菜单名称已存在");
    /**
     * 菜单不存在
     */
    public static final ErrorCode MENU_NOT_EXIST = new ErrorCode("SYSMGR-100013", "菜单不存在");
    /**
     * 不能设置自己为父菜单
     */
    public static final ErrorCode MENU_PARENT_ERROR = new ErrorCode("SYSMGR-100014", "不能设置自己为父菜单");
    /**
     * 当前菜单存在子菜单，无法删除
     */
    public static final ErrorCode MENU_EXIST_CHILDREN = new ErrorCode("SYSMGR-100015", "当前菜单存在子菜单，无法删除");
    /**
     * 角色名称已存在
     */
    public static final ErrorCode ROLE_NAME_EXIST = new ErrorCode("SYSMGR-100016", "角色名称已存在");
    /**
     * 角色编码已存在
     */
    public static final ErrorCode ROLE_CODE_EXIST = new ErrorCode("SYSMGR-100017", "角色编码已存在");
    /**
     * 角色不存在
     */
    public static final ErrorCode ROLE_NOT_EXIST = new ErrorCode("SYSMGR-100018", "角色不存在");
    /**
     * 内置角色不允许操作
     */
    public static final ErrorCode ROLE_CAN_NOT_OPERATE = new ErrorCode("SYSMGR-100019", "内置角色不允许操作");
    /**
     * 角色被禁用
     */
    public static final ErrorCode ROLE_IS_DISABLE = new ErrorCode("SYSMGR-100020", "角色被禁用");
    /**
     * 用户账号已经存在
     */
    public static final ErrorCode USER_USERNAME_EXIST = new ErrorCode("SYSMGR-100021", "用户账号已经存在");
    /**
     * 用户邮箱已经存在
     */
    public static final ErrorCode USER_EMAIL_EXIST = new ErrorCode("SYSMGR-100022", "用户邮箱已经存在");
    /**
     * 用户手机号码已存在
     */
    public static final ErrorCode USER_MOBILE_EXIST = new ErrorCode("SYSMGR-100023", "用户手机号码已经存在");
    /**
     * 用户不存在
     */
    public static final ErrorCode USER_NOT_EXIST = new ErrorCode("SYSMGR-100024", "用户不存在");
    /**
     * 用户被禁用
     */
    public static final ErrorCode USER_IS_DISABLE = new ErrorCode("SYSMGR-100025", "用户被禁用");
}
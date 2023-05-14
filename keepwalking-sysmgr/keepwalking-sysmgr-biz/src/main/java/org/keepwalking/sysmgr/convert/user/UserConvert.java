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

package org.keepwalking.sysmgr.convert.user;

import org.keepwalking.sysmgr.controller.user.vo.UserCreateReqVO;
import org.keepwalking.sysmgr.controller.user.vo.UserUpdateReqVO;
import org.keepwalking.sysmgr.repository.user.AdminUserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 用户对象转换
 *
 * @author <a href="mailto:taofangf@gmail.com">fangtao</a>
 * @since 1.0
 */
@Mapper
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    /**
     * UserCreateReqVO 转 AdminUserDO
     *
     * @param vo {@link UserCreateReqVO}
     * @return {@link AdminUserDO}
     */
    AdminUserDO convert(UserCreateReqVO vo);

    /**
     * UserUpdateReqVO 转 AdminUserDO
     *
     * @param vo {@link UserUpdateReqVO}
     * @return {@link AdminUserDO}
     */
    AdminUserDO convert(UserUpdateReqVO vo);
}

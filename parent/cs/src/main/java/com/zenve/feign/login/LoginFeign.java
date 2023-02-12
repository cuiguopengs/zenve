package com.zenve.feign.login;

import com.zenve.common.pojo.CommonResult;
import com.zenve.feign.FeignAPI;
import com.zenve.pojo.vo.AuthLoginReqVO;
import com.zenve.pojo.vo.AuthLoginRespVO;
import com.zenve.pojo.vo.AuthPermissionInfoRespVO;
import feign.RequestLine;

public interface LoginFeign extends FeignAPI {

    @RequestLine(value = "POST /admin-api/system/auth/login")
    CommonResult<AuthLoginRespVO> login(AuthLoginReqVO request);

    @RequestLine(value = "GET /admin-api/system/auth/get-permission-info")
    CommonResult<AuthPermissionInfoRespVO> getPermissionInfo();

}

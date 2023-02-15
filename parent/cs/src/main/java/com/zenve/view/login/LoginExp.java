package com.zenve.view.login;

import com.zenve.feign.Request;
import com.zenve.feign.login.LoginFeign;
import com.zenve.pojo.vo.AuthLoginReqVO;

public class LoginExp implements ILoginExp{
    @Override
    public void doLoginCheck(String userId, String userPassword) {
        AuthLoginReqVO authLoginReqVO = new AuthLoginReqVO();
        authLoginReqVO.setUsername("admin");
        authLoginReqVO.setPassword("admin123");
        authLoginReqVO.setCaptchaVerification("");

        Object login1 = Request.connector(LoginFeign.class).login(authLoginReqVO);


        System.out.println(login1);

    }

    public static void main(String[] args) {
        LoginExp loginExp = new LoginExp();
        loginExp.doLoginCheck("", "");
    }
}

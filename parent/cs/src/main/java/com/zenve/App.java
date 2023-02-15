package com.zenve;

import com.zenve.common.pojo.CommonResult;
import com.zenve.feign.Request;
import com.zenve.feign.login.LoginFeign;
import com.zenve.pojo.vo.AuthLoginReqVO;
import com.zenve.pojo.vo.AuthLoginRespVO;
import com.zenve.store.ApplicatonStore;
import feign.Feign;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Date;

import static com.zenve.feign.Request.*;

/**
 * Hello world!
 *
 */
public class App extends Application
{
    @Override
    public void start(Stage stage) throws Exception {
        Button button = new Button();
        button.setOnAction(e->{
            AuthLoginReqVO authLoginReqVO = new AuthLoginReqVO();
            authLoginReqVO.setUsername("admin");
            authLoginReqVO.setPassword("admin123");
            authLoginReqVO.setCaptchaVerification("");

//            CommonResult<AuthLoginRespVO> login1 = Request.connector(LoginFeign.class).login(authLoginReqVO);
//            ApplicatonStore.setToken("Bearer c3c9c9bc848841408ac0afb4e6a22b82");
//            AuthLoginRespVO data = login1.getData();
//            System.out.println(login1);
        });
        stage.setScene(new Scene(button));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

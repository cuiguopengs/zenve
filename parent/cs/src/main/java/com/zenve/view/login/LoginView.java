package com.zenve.view.login;

public class LoginView extends LoginInit implements ILoginCtrl {

    @Override
    public void doShow() {
        super.show();
    }

    @Override
    public void doSuccess() {

    }

    @Override
    public void doError() {
        super.close();

    }
}

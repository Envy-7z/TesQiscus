package com.wa.tesqiscus.activity.login;

import com.wa.tesqiscus.model.repo.UserRepo;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;
    private UserRepo userRepo;

    public LoginPresenter(LoginContract.View view, UserRepo userRepo) {
        this.view = view;
        this.userRepo = userRepo;
    }

    @Override
    public void login(String userId, String password, String displayName) {
        view.showProgressBar();
        userRepo.login(userId, password, displayName,
                user -> {
                    view.showHomePage();
                },
                throwable -> {
                    view.showErrorMessage(throwable.getMessage());
                });
        view.hideProgressBar();
    }


}

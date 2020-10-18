package com.wa.tesqiscus;

import android.content.Context;

import com.wa.tesqiscus.model.method.UserRepoMethod;
import com.wa.tesqiscus.model.repo.UserRepo;


public class AppComponent {

    private final UserRepo userRepository;

    public AppComponent(Context context) {
        userRepository = new UserRepoMethod(context);
    }

    public UserRepo getUserRepository() {
        return userRepository;
    }

}

package com.wa.tesqiscus.model.repo;

import android.content.Intent;

import com.wa.tesqiscus.model.User;
import com.wa.tesqiscus.utils.Action;

import java.util.List;

public interface UserRepo {
    void login(String userId, String password, String displayName, Action<User> onSuccess, Action<Throwable> onError);
    void getUser(Action<List<User>> onSuccess, Action<Throwable> onError);
    void openChat(User user, Action<Intent> onSuccess, Action<Throwable> onError);
    void updateContacts(List<User> contacts);
    void logout();
}

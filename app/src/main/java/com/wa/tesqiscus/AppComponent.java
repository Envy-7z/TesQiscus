package com.wa.tesqiscus;

import android.content.Context;

import com.qiscus.sdk.chat.core.data.model.QiscusChatRoom;
import com.wa.tesqiscus.model.User;
import com.wa.tesqiscus.model.method.ChatRepoMethod;
import com.wa.tesqiscus.model.method.UserRepoMethod;
import com.wa.tesqiscus.model.repo.ChatRepo;
import com.wa.tesqiscus.model.repo.UserRepo;
import com.wa.tesqiscus.utils.Action;

import java.util.List;


public class AppComponent {

    private final UserRepo userRepository;
    private final ChatRepo chatRoomRepository;

    public AppComponent(Context context) {
        userRepository = new UserRepoMethod(context);
        chatRoomRepository = new ChatRepoMethod();

    }

    public UserRepo getUserRepository() {
        return userRepository;
    }
    public ChatRepo getChatRoomRepository() {
        return chatRoomRepository;
    }

}

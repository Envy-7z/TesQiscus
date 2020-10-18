package com.wa.tesqiscus.model.repo;

import com.qiscus.sdk.chat.core.data.model.QiscusChatRoom;
import com.wa.tesqiscus.model.User;
import com.wa.tesqiscus.utils.Action;

import java.util.List;

public interface ChatRepo {
    void getChatRooms(Action<List<QiscusChatRoom>> onSuccess, Action<Throwable> onError);

    void createChatRoom(User user, Action<QiscusChatRoom> onSuccess, Action<Throwable> onError);

    void createGroupChatRoom(String name, List<User> members, Action<QiscusChatRoom> onSuccess, Action<Throwable> onError);

}


package com.wa.tesqiscus.activity.main;

import com.qiscus.sdk.chat.core.data.model.QiscusChatRoom;
import com.wa.tesqiscus.model.repo.ChatRepo;
import com.wa.tesqiscus.model.repo.UserRepo;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private UserRepo userRepository;
    private ChatRepo chatRoomRepository;

    public MainPresenter(MainContract.View view, UserRepo userRepository, ChatRepo chatRoomRepository) {
        this.view = view;
        this.userRepository = userRepository;
        this.chatRoomRepository = chatRoomRepository;
    }

    @Override
    public void openChat(QiscusChatRoom chatRoom) {
        view.showChatRoomPage(chatRoom);
    }

    @Override
    public void logout() {
        userRepository.logout();
    }

    @Override
    public void loadChatRooms() {
        chatRoomRepository.getChatRooms(chatRooms -> view.showChatRooms(chatRooms),
                throwable -> view.showErrorMessage(throwable.getMessage()));
    }


}


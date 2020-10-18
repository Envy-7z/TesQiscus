package com.wa.tesqiscus.activity.contact;

import com.wa.tesqiscus.model.User;
import com.wa.tesqiscus.model.repo.ChatRepo;
import com.wa.tesqiscus.model.repo.UserRepo;

public class ContactPresenter implements ContactContract.Presenter {
    private ContactContract.View view;
    private UserRepo userRepository;
    private ChatRepo chatRoomRepository;

    public ContactPresenter(ContactContract.View view, UserRepo userRepository, ChatRepo chatRoomRepository) {
        this.view = view;
        this.userRepository = userRepository;
        this.chatRoomRepository = chatRoomRepository;
    }

    @Override
    public void loadContacts() {
        userRepository.getUser(users -> view.showContacts(users),
                throwable -> view.showErrorMessage(throwable.getMessage()));

    }

    @Override
    public void createRoom(User contact) {
        chatRoomRepository.createChatRoom(contact,
                qiscusChatRoom -> view.showChatRoomPage(qiscusChatRoom),
                throwable -> view.showErrorMessage(throwable.getMessage()));
        userRepository.openChat(contact, intent -> view.showRoomPage(intent),
                throwable -> view.showErrorMessage(throwable.getMessage()));

    }
}

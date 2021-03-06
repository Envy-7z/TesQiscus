package com.wa.tesqiscus.activity.main;

import com.qiscus.sdk.chat.core.data.model.QiscusChatRoom;

import java.util.List;

public class MainContract {

        public interface View {
            void showChatRooms(List<QiscusChatRoom> chatRooms);

            void showChatRoomPage(QiscusChatRoom qiscusChatRoom);
            void showChatRoomPageGroup(QiscusChatRoom qiscusChatRoom);
            void showErrorMessage(String errorMessage);
        }

        interface Presenter {
            void openChat(QiscusChatRoom chatRoom);
            void logout();
            void loadChatRooms();
        }
    }



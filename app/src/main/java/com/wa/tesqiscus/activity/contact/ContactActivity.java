package com.wa.tesqiscus.activity.contact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.qiscus.sdk.chat.core.data.model.QiscusChatRoom;
import com.wa.tesqiscus.R;
import com.wa.tesqiscus.TesQiscusApp;
import com.wa.tesqiscus.model.User;
import com.wa.tesqiscus.utils.OnItemClickListener;

import java.util.List;

public class ContactActivity extends AppCompatActivity implements OnItemClickListener, ContactContract.View{
    private RecyclerView recyclerView;
    private ContactAdapter contactAdapter;

    private ContactContract.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findViewById(R.id.back).setOnClickListener(v -> onBackPressed());
        recyclerView = findViewById(R.id.contact_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        contactAdapter = new ContactAdapter(this);
        contactAdapter.setOnItemClickListener(this);

        recyclerView.setAdapter(contactAdapter);
        presenter = new ContactPresenter(this,
                TesQiscusApp.getInstance().getComponent().getUserRepository(),
                TesQiscusApp.getInstance().getComponent().getChatRoomRepository());

        presenter.loadContacts();

    }

    @Override
    public void showContacts(List<User> contacts) {
        contactAdapter.addOrUpdate(contacts);

    }

    @Override
    public void showChatRoomPage(QiscusChatRoom qiscusChatRoom) {

    }

    @Override
    public void showRoomPage(Intent intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(this,errorMessage,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onItemClick(int position) {
        presenter.createRoom(contactAdapter.getData().get(position));

    }
}
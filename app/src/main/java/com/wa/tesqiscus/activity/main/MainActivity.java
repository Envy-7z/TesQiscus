package com.wa.tesqiscus.activity.main;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.qiscus.sdk.chat.core.data.model.QiscusChatRoom;
import com.qiscus.sdk.chat.core.event.QiscusCommentReceivedEvent;
import com.qiscus.sdk.ui.QiscusChannelActivity;
import com.qiscus.sdk.ui.QiscusChatActivity;
import com.wa.tesqiscus.R;
import com.wa.tesqiscus.TesQiscusApp;
import com.wa.tesqiscus.activity.login.LoginActivity;
import com.wa.tesqiscus.utils.OnItemClickListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View, OnItemClickListener {
    private RecyclerView recyclerView;
    private MainContract.Presenter presenter;
    private MainAdapter chatRoomAdapter;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.rvChat);
        presenter = new MainPresenter(this, TesQiscusApp.getInstance().getComponent().getUserRepository()
                , TesQiscusApp.getInstance().getComponent().getChatRoomRepository());
        chatRoomAdapter = new MainAdapter(this);
        chatRoomAdapter.setOnItemClickListener(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(chatRoomAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadChatRooms();
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void onCommentReceivedEvent(QiscusCommentReceivedEvent event) {
        presenter.loadChatRooms();
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.rooms, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            new AlertDialog.Builder(this)
                    .setTitle("Logout")
                    .setMessage("Are you sure wants to logout?")
                    .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            presenter.logout();
                            startActivity(new Intent(MainActivity.this, LoginActivity.class));
                            finish();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showChatRooms(List<QiscusChatRoom> chatRooms) {
        chatRoomAdapter.addOrUpdate(chatRooms);

    }

    @Override
    public void showChatRoomPage(QiscusChatRoom qiscusChatRoom) {
        startActivity(QiscusChatActivity.generateIntent(this, qiscusChatRoom));

    }

    @Override
    public void showChatRoomPageGroup(QiscusChatRoom qiscusChatRoom) {
        startActivity(QiscusChannelActivity.generateIntent(this, qiscusChatRoom));

    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onItemClick(int position) {
        presenter.openChat(chatRoomAdapter.getData().get(position));

    }

}
package com.wa.tesqiscus;

import android.app.Application;
import android.os.Build;

import androidx.multidex.MultiDexApplication;

import com.qiscus.jupuk.Jupuk;
import com.qiscus.nirmana.Nirmana;
import com.qiscus.sdk.Qiscus;
import com.qiscus.sdk.chat.core.QiscusCore;
import com.vanniktech.emoji.EmojiManager;
import com.vanniktech.emoji.one.EmojiOneProvider;
import com.wa.tesqiscus.utils.Constant;

public class TesQiscusApp extends Application {
    private static TesQiscusApp instance;

    private AppComponent component;

    public static TesQiscusApp getInstance() {
        return instance;
    }

    private static void initEmoji() {
        EmojiManager.install(new EmojiOneProvider());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        component = new AppComponent(this);

        Nirmana.init(this);
        Qiscus.init(this, Constant.APP_ID);


        Qiscus.getChatConfig()
                .setSwipeRefreshColorScheme(R.color.colorPrimary, R.color.colorAccent)
                .setLeftBubbleColor(R.color.leftBubble)
                .setLeftBubbleTextColor(R.color.qiscus_primary_text)
                .setLeftBubbleTimeColor(R.color.qiscus_secondary_text)
                .setLeftLinkTextColor(R.color.qiscus_primary_text)
                .setLeftProgressFinishedColor(R.color.colorPrimary)
                .setRightBubbleColor(R.color.colorPrimaryLight)
                .setRightProgressFinishedColor(R.color.colorPrimary)
                .setSelectedBubbleBackgroundColor(R.color.colorPrimary)
                .setReadIconColor(R.color.colorPrimary)
                .setAppBarColor(R.color.colorPrimary)
                .setStatusBarColor(R.color.colorPrimaryDark)
                .setAccentColor(R.color.colorAccent)
                .setAccountLinkingTextColor(R.color.colorPrimary)
                .setAccountLinkingBackground(R.color.accountLinkingBackground)
                .setButtonBubbleTextColor(R.color.colorPrimary)
                .setButtonBubbleBackBackground(R.color.accountLinkingBackground)
                .setReplyBarColor(R.color.colorPrimary)
                .setReplySenderColor(R.color.colorPrimaryLight)
                .setSendButtonIcon(R.drawable.ic_default_send)
                .setStopRecordIcon(R.drawable.ic_send_record)
                .setCancelRecordIcon(R.drawable.ic_cancel_record)
                .setInlineReplyColor(R.color.colorPrimary);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Qiscus.getChatConfig().setEnableReplyNotification(true);
        }

      //  initEmoji();


      }

    public AppComponent getComponent() {
        return component;
    }
}



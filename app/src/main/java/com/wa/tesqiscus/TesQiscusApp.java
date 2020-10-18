package com.wa.tesqiscus;

import android.os.Build;
import androidx.multidex.MultiDexApplication;

import com.qiscus.jupuk.Jupuk;
import com.qiscus.nirmana.Nirmana;
import com.qiscus.sdk.Qiscus;
import com.qiscus.sdk.chat.core.QiscusCore;
import com.vanniktech.emoji.EmojiManager;
import com.vanniktech.emoji.one.EmojiOneProvider;

public class TesQiscusApp extends MultiDexApplication {
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
        QiscusCore.init(this, BuildConfig.QISCUS_SDK_APP_ID);

        Jupuk.init(this);
    }

    public AppComponent getComponent() {
        return component;
    }
}



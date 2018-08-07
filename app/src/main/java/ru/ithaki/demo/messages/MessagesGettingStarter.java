package ru.ithaki.demo.messages;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MessagesGettingStarter extends BroadcastReceiver {
    @Override
    public void onReceive(@NonNull Context context, @Nullable Intent intent) {
        MessagesServiceLauncher.launchMessagesService(context);
    }

    public static PendingIntent getLaunchAction(@NonNull Context context) {
        Intent intent = new Intent(context, MessagesGettingStarter.class);
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
    }
}

package ru.ithaki.demo.messages;

import android.annotation.TargetApi;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

@TargetApi(Build.VERSION_CODES.O)
public class MessagesServiceApi26 extends BaseMessagesService {
    private static final int FOREGROUND_NOTIFICATION_ID = 2468642;

    public static final MessagesServiceLauncher<MessagesServiceApi26> SERVICE_LAUNCHER = new MessagesServiceLauncher<MessagesServiceApi26>() {
        @Override
        protected Class<MessagesServiceApi26> getServiceClass() {
            return MessagesServiceApi26.class;
        }

        @Override
        protected void launchService(@NonNull Context context, @NonNull Intent intent) {
            context.startForegroundService(intent);
        }
    };

    protected MessagesServiceApi26() {
        super("MessagesServiceApi26");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Notification foregroundNotification = getForegroundNotification(this);
        startForeground(FOREGROUND_NOTIFICATION_ID, foregroundNotification);

        super.onHandleIntent(intent);

        stopForeground(true);
    }

    private static Notification getForegroundNotification(@NonNull Context context) {
        return new NotificationCompat.Builder(context)
            .setSmallIcon(R.drawable.ic_messages_service_notification)
            .setContentTitle(context.getString(R.string.messages_service_title))
            .setContentText(context.getString(R.string.messages_service_text))
            .build();
    }
}

package ru.ithaki.demo.messages;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.ithaki.demo.messages.data.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MessageNotification {
    private static final int MESSAGE_NOTIFCATION_ID = -1;
    private static final Map<String, Integer> MESSAGE_NOTIFCATION_IDS = new HashMap<>();
    private static int LAST_MESSAGE_NOTIFCATION_ID = MESSAGE_NOTIFCATION_ID;

    public static boolean renderMessage(@NonNull Context context, @NonNull Message message) {
        long now = System.currentTimeMillis();

        if (message.StartDateTime <= now && now < message.EndDateTime) {
            int notificationId = getMessageNotificationId(message);
            Notification notification = createMessageNotification(context, message);

            NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(notificationId, notification);

            return true;
        } else {
            return false;
        }
    }

    public static void renderMessages(@NonNull Context context, @Nullable List<Message> messages) {
        if (messages != null) {
            for (Message message : messages) {
                MessageNotification.renderMessage(context, message);
            }
        }
    }

    private static int getMessageNotificationId(@NonNull Message message) {
        Integer messageNotifcationId;

        synchronized (MESSAGE_NOTIFCATION_IDS) {
            messageNotifcationId = MESSAGE_NOTIFCATION_IDS.get(message.Id);

            if (messageNotifcationId == null) {
                messageNotifcationId = LAST_MESSAGE_NOTIFCATION_ID;

                MESSAGE_NOTIFCATION_IDS.put(message.Id, LAST_MESSAGE_NOTIFCATION_ID);

                LAST_MESSAGE_NOTIFCATION_ID--;
            }
        }

        return messageNotifcationId;
    }

    private static Notification createMessageNotification(@NonNull Context context, @NonNull Message message) {
        return new NotificationCompat.Builder(context)
            .setSmallIcon(R.drawable.ic_message_notification)
            .setContentTitle(message.Subject)
            .setContentText(message.Text)
            .setContentIntent(MessageActivity.getLaunchAction(context, message))
            .setAutoCancel(true)
            .build();
    }
}


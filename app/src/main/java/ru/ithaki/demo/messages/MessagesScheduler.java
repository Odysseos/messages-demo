package ru.ithaki.demo.messages;

import android.app.AlarmManager;
import android.content.Context;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;

public class MessagesScheduler {
    private static final long INTERVAL = TimeUnit.MINUTES.toMillis(2);

    public static void startMessagesScheduling(@NonNull Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            0,
            INTERVAL,
            MessagesGettingStarter.getLaunchAction(context));
    }

    public static void stopMessagesScheduling(@NonNull Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(MessagesGettingStarter.getLaunchAction(context));
    }
}

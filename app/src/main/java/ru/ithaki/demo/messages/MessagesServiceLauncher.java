package ru.ithaki.demo.messages;

import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;

public abstract class MessagesServiceLauncher<S extends BaseMessagesService> {
    private static final MessagesServiceLauncher<? extends BaseMessagesService> SERVICE_LAUNCHER = Build.VERSION.SDK_INT < Build.VERSION_CODES.O
        ? MessagesServiceApi14.SERVICE_LAUNCHER
        : MessagesServiceApi26.SERVICE_LAUNCHER;

    protected MessagesServiceLauncher() {
    }

    protected abstract Class<S> getServiceClass();

    protected abstract void launchService(@NonNull Context context, @NonNull Intent intent);

    private Intent createLaunchIntent(@NonNull Context context) {
        return new Intent(context, getServiceClass());
    }

    public static void launchMessagesService(@NonNull Context context) {
        SERVICE_LAUNCHER.launchService(context, SERVICE_LAUNCHER.createLaunchIntent(context));
    }
}

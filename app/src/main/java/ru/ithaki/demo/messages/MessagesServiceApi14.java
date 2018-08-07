package ru.ithaki.demo.messages;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

public class MessagesServiceApi14 extends BaseMessagesService {
    public static final MessagesServiceLauncher<MessagesServiceApi14> SERVICE_LAUNCHER = new MessagesServiceLauncher<MessagesServiceApi14>() {
        @Override
        protected Class<MessagesServiceApi14> getServiceClass() {
            return MessagesServiceApi14.class;
        }

        @Override
        protected void launchService(@NonNull Context context, @NonNull Intent intent) {
            context.startService(intent);
        }
    };

    public MessagesServiceApi14() {
        super("MessagesServiceApi14");
    }
}

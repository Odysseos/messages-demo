package ru.ithaki.demo.messages;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import ru.ithaki.demo.messages.data.Message;
import ru.ithaki.demo.messages.network.UrlRequester;
import ru.ithaki.demo.messages.parser.ArrayJsonParser;
import ru.ithaki.demo.messages.parser.MessageParser;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class BaseMessagesService extends IntentService {
    private static final String TAG = "MessagesGetter";

    @NonNull
    private final UrlRequester<List<Message>> mRequester =
        new UrlRequester<>(new ArrayJsonParser<>(new MessageParser()));

    protected BaseMessagesService(@NonNull String name) {
        super(name);
    }

    @Override
    @CallSuper
    protected void onHandleIntent(@Nullable Intent intent) {
        List<Message> messages = getMessages();
        MessageNotification.renderMessages(this, messages);
    }

    private List<Message> getMessages() {
        try {
            return mRequester.request(BuildConfig.MESSAGES_URL);
        } catch (IOException e) {
            Log.e(TAG, "", e);
        }

        return null;
    }
}

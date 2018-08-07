package ru.ithaki.demo.messages;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import ru.ithaki.demo.messages.data.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MessageActivity extends AppCompatActivity {
    private static final String PARAM_SUBJECT = "subject";
    private static final String PARAM_TEXT = "text";

    private TextView mMessageSubject;
    private TextView mMessageText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_message);

        mMessageSubject = findViewById(R.id.message_subject);
        mMessageText = findViewById(R.id.message_text);

        if (!bindIntent(getIntent())) {
            finish();
        }
    }

    @Override
    protected void onNewIntent(@Nullable Intent intent) {
        bindIntent(intent);
    }

    private boolean bindIntent(@Nullable Intent intent) {
        if (intent == null) {
            return false;
        }

        Uri messageUri = intent.getData();
        if (messageUri == null) {
            return false;
        }

        mMessageSubject.setText(messageUri.getQueryParameter(PARAM_SUBJECT));
        mMessageText.setText(messageUri.getQueryParameter(PARAM_TEXT));

        return true;
    }

    public static PendingIntent getLaunchAction(@NonNull Context context, @NonNull Message message) {
        Uri uri = new Uri.Builder()
            .appendQueryParameter(PARAM_SUBJECT, message.Subject)
            .appendQueryParameter(PARAM_TEXT, message.Text)
            .build();
        Intent intent = new Intent(context, MessageActivity.class)
            .setData(uri);
        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
    }
}

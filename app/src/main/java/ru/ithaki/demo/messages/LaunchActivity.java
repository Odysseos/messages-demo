package ru.ithaki.demo.messages;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class LaunchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_launch);

        findViewById(R.id.start_getting_messages_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessagesScheduler.startMessagesScheduling(LaunchActivity.this);
            }
        });

        findViewById(R.id.stop_getting_messages_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessagesScheduler.stopMessagesScheduling(LaunchActivity.this);
            }
        });
    }
}

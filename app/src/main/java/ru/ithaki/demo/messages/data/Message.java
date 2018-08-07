package ru.ithaki.demo.messages.data;

import androidx.annotation.NonNull;

public final class Message {
    @NonNull
    public final String Id;
    @NonNull
    public final String Subject;
    @NonNull
    public final String Text;
    public final long StartDateTime;
    public final long EndDateTime;

    public Message(
        @NonNull String id,
        @NonNull String subject,
        @NonNull String text,
        long startDateTime,
        long endDateTime)
    {
        Id = id;
        Subject = subject;
        Text = text;
        StartDateTime = startDateTime;
        EndDateTime = endDateTime;
    }
}

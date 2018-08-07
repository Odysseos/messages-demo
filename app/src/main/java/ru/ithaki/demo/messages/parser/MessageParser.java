package ru.ithaki.demo.messages.parser;

import android.util.JsonReader;
import android.util.JsonToken;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import ru.ithaki.demo.messages.data.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MessageParser extends BaseJsonParser<Message> {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    private static final String FIELD_ID = "id";
    private static final String FIELD_SUBJECT = "subject";
    private static final String FIELD_TEXT = "text";
    private static final String FIELD_START_DATE_TIME = "startDateTime";
    private static final String FIELD_END_DATE_TIME = "endDateTime";

    @Nullable
    @Override
    protected Message parse(@NonNull JsonReader reader) throws IOException, ParseException {
        reader.beginObject();

        String id = null;
        String subject = null;
        String text = null;
        String startDateTimeStr = null;
        String endDateTimeStr = null;

        while (reader.peek() != JsonToken.END_OBJECT) {
            String field = reader.nextName();
            if (field != null) {
                switch (field) {
                    case FIELD_ID:
                        id = reader.nextString();
                        break;
                    case FIELD_SUBJECT:
                        subject = reader.nextString();
                        break;
                    case FIELD_TEXT:
                        text = reader.nextString();
                        break;
                    case FIELD_START_DATE_TIME:
                        startDateTimeStr = reader.nextString();
                        break;
                    case FIELD_END_DATE_TIME:
                        endDateTimeStr = reader.nextString();
                        break;
                }
            }
        }

        reader.endObject();

        if (id == null || subject == null || text == null || startDateTimeStr == null || endDateTimeStr == null) {
            throw new ParseException("Message object is not complete!");
        }

        long startDateTime = parseDateTime(startDateTimeStr);
        long endDateTime = parseDateTime(endDateTimeStr);

        return new Message(id, subject, text, startDateTime, endDateTime);
    }

    private static long parseDateTime(@NonNull String dateTimeStr) throws ParseException {
        // because of SimpleDateFormat is not know what is "Z" postfix in ISO 8601
        String normalizedDateTimeStr = dateTimeStr.endsWith("Z")
            ? dateTimeStr.substring(0, dateTimeStr.length() - "Z".length()) + "+0000"
            : dateTimeStr;

        try {
            return DATE_FORMAT.parse(normalizedDateTimeStr).getTime();
        } catch (java.text.ParseException e) {
            throw new ParseException("Message object date format is invalid!");
        }
    }
}

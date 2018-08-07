package ru.ithaki.demo.messages.parser;

import android.util.JsonReader;
import android.util.JsonToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class ArrayJsonParser<T> extends BaseJsonParser<List<T>> {
    @NonNull
    private final BaseJsonParser<T> mItemParser;

    public ArrayJsonParser(@NonNull BaseJsonParser<T> itemParser) {
        mItemParser = itemParser;
    }

    @NonNull
    @Override
    protected List<T> parse(@NonNull JsonReader reader) throws IOException {
        reader.beginArray();

        List<T> result = new ArrayList<>();

        while (reader.peek() != JsonToken.END_ARRAY) {
            T item;

            try {
                item = mItemParser.parse(reader);
            } catch (ParseException ignore) {
                continue;
            }

            if (item != null) {
                result.add(item);
            }
        }

        reader.endArray();

        return result;
    }
}

package ru.ithaki.demo.messages.parser;

import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class BaseJsonParser<T> implements Parser<T> {
    @Nullable
    @Override
    public T parse(@NonNull InputStream input) throws IOException, ParseException {
        return parse(new JsonReader(new InputStreamReader(input)));
    }

    @Nullable
    protected abstract T parse(@NonNull JsonReader reader) throws IOException, ParseException;
}

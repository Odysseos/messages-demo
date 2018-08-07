package ru.ithaki.demo.messages.parser;

import java.io.IOException;
import java.io.InputStream;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface Parser<T> {
    @Nullable
    T parse(@NonNull InputStream input) throws IOException, ParseException;

    class ParseException extends Exception {
        public ParseException(@NonNull String message) {
            super(message);
        }
    }
}

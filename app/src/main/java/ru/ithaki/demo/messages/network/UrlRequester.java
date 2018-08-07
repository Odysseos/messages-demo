package ru.ithaki.demo.messages.network;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import ru.ithaki.demo.messages.parser.Parser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class UrlRequester<T> implements Requester<String, T> {
    private static final String TAG = "UrlRequester";

    @NonNull
    private final Parser<T> mParser;

    public UrlRequester(@NonNull Parser<T> parser) {
        mParser = parser;
    }

    @Override
    @Nullable
    public T request(@NonNull String url) throws IOException {
        URLConnection connection = new URL(url).openConnection();

        InputStream input = new BufferedInputStream(connection.getInputStream());

        try {
            return mParser.parse(input);
        } catch (Parser.ParseException e) {
            Log.e(TAG, String.format("Request for url \"%s\" was failed!", url), e);

            return null;
        } finally {
            try {
                input.close();
            } catch (IOException ignore) {
            }
        }
    }
}

package ru.ithaki.demo.messages.network;

import java.io.IOException;

import ru.ithaki.demo.messages.parser.Parser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface Requester<S, T> {
    @Nullable
    T request(@NonNull S source) throws IOException, Parser.ParseException;
}

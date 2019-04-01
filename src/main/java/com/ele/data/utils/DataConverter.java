package com.ele.data.utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DataConverter {

    public static <T extends GeneratedMessageV3> List<T> parseProtoArray(InputStream stream, T.Builder builder) throws IOException {

        JsonReader reader = new JsonReader(new InputStreamReader(stream, "UTF-8"));
        List<T> data = new ArrayList<>();

        reader.beginObject();
        while (reader.hasNext()) {
            data.add(parseProto(reader.nextString(), builder));
        }
        reader.endObject();
        reader.close();

        return data;
    }

    public static <T extends GeneratedMessageV3> T parseProto(String json, T.Builder builder) throws InvalidProtocolBufferException {
        JsonFormat.parser().merge(json, builder);
        return (T) builder.build();
    }
}

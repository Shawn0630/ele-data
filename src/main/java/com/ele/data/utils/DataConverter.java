package com.ele.data.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DataConverter {

    public static <T extends GeneratedMessageV3> List<T> parseProtoArray(InputStream in, T.Builder builder) throws IOException {

        JsonParser parser = new JsonParser();
        Object obj = parser.parse(new InputStreamReader(in, "UTF-8"));
        JsonArray jsonArray = (JsonArray) obj;
        List<T> data = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonElement jsonobject = jsonArray.get(i);
            data.add(parseProto(jsonobject.toString(), builder));
            builder.clear();
        }

        return data;
    }

    public static <T extends GeneratedMessageV3> T parseProto(String json, T.Builder builder) throws InvalidProtocolBufferException {
        JsonFormat.parser().merge(json, builder);
        return (T) builder.build();
    }

}

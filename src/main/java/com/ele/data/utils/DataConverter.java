package com.ele.data.utils;

import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataConverter {

    public static <T extends GeneratedMessageV3> List<T> parseProtoArray(String string, T.Builder builder) throws IOException {

        JSONArray jsonarray = new JSONArray(string);
        List<T> data = new ArrayList<>();
        for (int i = 0; i < jsonarray.length(); i++) {
            JSONObject jsonobject = jsonarray.getJSONObject(i);
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

package com.ele.data.repositories;

import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MySQLCRUDRepository {
    private static final Logger LOG = LoggerFactory.getLogger(MySQLCRUDRepository.class);
    protected String getColumnNames(List<String> columns) {
        return String.join("," , columns);
    }
    protected String getQuestionMarks(List<String> columns) {
        return String.join(",", columns.stream().map(c -> "?").collect(Collectors.toList()));
    }
    protected <T extends GeneratedMessageV3> String protoToJson(T obj) {
        final JsonFormat.Printer printer = JsonFormat.printer().omittingInsignificantWhitespace();
        try {
            return printer.print(obj);
        } catch (Throwable e) {
            LOG.error("Error converting protobuf to Json");
            return "\"Error converting to Json. See server log.\"";
        }
    }
    protected <T extends GeneratedMessageV3> String protoArrayToJson(List<T> objs) {
        return "[" + String.join("," + objs.stream().map(t -> protoToJson(t)).collect(Collectors.toList())) + "]";
    }
}

package com.ele.data.repositories.file;

import akka.NotUsed;
import akka.stream.javadsl.Source;
import com.ele.data.utils.DataConverter;
import com.example.dto.ScanDenovoCandidate;

import java.io.IOException;
import java.io.InputStream;

public class FileResultRepository implements ResultRepository {
    @Override
    public Source<ScanDenovoCandidate, NotUsed> denovo() {
        InputStream denovo = FileResultRepository.class.getClassLoader().getResourceAsStream("result/Denovo.json");
        try {
            return Source.from(DataConverter.parseProtoArray(denovo, ScanDenovoCandidate.newBuilder()));
        } catch (IOException e) {
            return Source.failed(e);
        }
    }
}

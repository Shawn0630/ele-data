package com.ele.data.repositories.file;

import akka.NotUsed;
import akka.stream.javadsl.Source;
import com.ele.data.utils.DataConverter;
import com.example.dto.ScanDenovoCandidate;

import java.io.IOException;
import java.io.InputStream;

public class FileResultRepository implements ResultRepository {
    @Override
    public Source<ScanDenovoCandidate, NotUsed> denovo() throws IOException {
        InputStream denovo = FileResultRepository.class.getClassLoader().getResourceAsStream("result/Denovo.json");
        return Source.from(DataConverter.parseProtoArray(denovo, ScanDenovoCandidate.newBuilder()));
    }
}

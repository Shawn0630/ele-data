package com.ele.data.repositories.file;

import akka.NotUsed;
import akka.stream.javadsl.Source;
import com.example.dto.ScanDenovoCandidate;

import java.io.IOException;

public interface ResultRepository {
    Source<ScanDenovoCandidate, NotUsed> denovo() throws IOException;
}

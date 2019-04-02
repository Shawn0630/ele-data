package com.ele.data.repositories.file;

import akka.NotUsed;
import akka.stream.javadsl.Source;
import com.example.dto.ScanDenovoCandidate;

public interface ResultRepository {
    Source<ScanDenovoCandidate, NotUsed> denovo();
}

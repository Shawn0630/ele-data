package com.ele.data.repositories.cassandra;

import akka.Done;
import akka.NotUsed;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import com.ele.model.dto.ele.ShopDetail;

import java.util.concurrent.CompletionStage;

public interface ShopRepository {
    Sink<ShopDetail, CompletionStage<Done>> sink();
    Source<ShopDetail, NotUsed> source();
}

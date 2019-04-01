package com.ele.data.repositories.cassandra;

import akka.Done;
import akka.NotUsed;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import com.ele.model.dto.ele.ShopDetail;

import java.util.concurrent.CompletionStage;

public class CassandraShopRepository implements ShopRepository {
    public static final String TABLE = "shop";

    public static final String SHOP_ID = "shop_id";
    public static final String GEOHASH = "geohash";
    public static final String PROTO = "proto";

    public static final String FIELDS = ""
            + SHOP_ID + " uuid,"
            + GEOHASH + " text,"
            + PROTO + " blob,";

    private static final String COLUMNS = CassandraRepository.convertFieldsToNames(FIELDS);


    @Override
    public Sink<ShopDetail, CompletionStage<Done>> sink() {
        return null;
    }

    @Override
    public Source<ShopDetail, NotUsed> source() {
        return null;
    }
}

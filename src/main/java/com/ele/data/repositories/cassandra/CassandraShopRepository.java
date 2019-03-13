package com.ele.data.repositories.cassandra;

public class CassandraShopRepository {
    public static final String TABLE = "shop";

    public static final String SHOP_ID = "shop_id";
    public static final String LAT = "lat";
    public static final String LNG = "lng";
    public static final String PROTO = "proto";

    public static final String FIELDS = ""
            + SHOP_ID + " uuid,"
            + LAT + " text,"
            + LNG + " text,"
            + PROTO + " blob,";

    private static final String COLUMNS = CassandraRepository.convertFieldsToNames(FIELDS);

    //  + "PRIMARY KEY ((" + LAT + "," + LNG + ")," + SHOP_ID ")"

}

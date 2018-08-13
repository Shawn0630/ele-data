package com.ele.data.repositories;

import com.ele.data.utils.EleDateFormat;
import com.ele.model.dto.ele.Promotion;
import com.ele.model.dto.ele.ShopProfile;
import com.google.protobuf.util.JsonFormat;
import org.apache.commons.pool.ObjectPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class ShopRepository extends MySQLCRUDRepository{

    private static final String TABLE_NAME = "shop";
    private static final String SHOP_ID = "shop_id";
    private static final String SHOP_NAME = "shop_name";
    private static final String DATE_OF_REGISTRATION = "date_of_registration";
    private static final String LAT = "lat";
    private static final String LNG = "lng";
    private static final String IS_BIRD = "is_bird";
    private static final String IS_INSURANCE = "is_insurance";
    private static final String IS_BRAND = "is_brand";
    private static final String NEED_TIP = "need_tip";
    private static final String PROMOTION = "promotion";

    private final ObjectPool connPool;

    ShopRepository(ObjectPool connPool) {
        this.connPool = connPool;
    }

    public CompletionStage<String> create(ShopProfile shop) {
        List<String> columns = Arrays.asList(
                SHOP_ID, SHOP_NAME, DATE_OF_REGISTRATION, LAT, LNG, IS_BIRD, IS_INSURANCE, IS_BRAND, NEED_TIP, PROMOTION);
        String sql = "INSERT INTO " + TABLE_NAME + " (" + getColumnNames(columns) + ") "
                + "VALUES(" + getQuestionMarks(columns) + ")";

        return CompletableFuture.supplyAsync(() -> {
            try (Connection conn = (Connection) connPool.borrowObject()) {
                try (PreparedStatement stmt = conn.prepareStatement(sql)){
                    List<Promotion> promotions = shop.getShopActivityList();
                    stmt.setObject(1, shop.getId());
                    stmt.setObject(2, shop.getShopName());
                    stmt.setObject(3, EleDateFormat.getCurrentDate());
                    stmt.setObject(4, 13.0412658);
                    stmt.setObject(5, 80.2338514);
                    stmt.setObject(6, shop.getIsBird());
                    stmt.setObject(7, shop.getIsInsurance());
                    stmt.setObject(8, shop.getIsBrand());
                    stmt.setObject(9, shop.getNeedtip());
                    stmt.setObject(10, protoArrayToJson(shop.getShopActivityList()));
                    stmt.executeUpdate();
                    return shop.getId();
                }
            } catch (Exception e) {
                throw new RepositoryException("Error creating shop", e);
            }
        });
    }
}

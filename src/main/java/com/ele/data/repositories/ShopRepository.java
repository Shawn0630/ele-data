package com.ele.data.repositories;

import com.ele.data.utils.EleDateFormat;
import com.ele.data.utils.ShopUtils;
import com.ele.model.dto.ele.Promotion;
import com.ele.model.dto.ele.ShopProfile;
import com.google.protobuf.util.JsonFormat;
import org.json.JSONArray;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class ShopRepository extends MySQLCRUDRepository {

    private static final String TABLE_NAME = "shop";
    private static final String SHOP_ID = "shop_id";
    private static final String SHOP_NAME = "shop_name";
    private static final String SHOP_URL = "shop_url";
    private static final String DATE_OF_REGISTRATION = "date_of_registration";
    private static final String LAT = "lat";
    private static final String LNG = "lng";
    private static final String IS_BIRD = "is_bird";
    private static final String IS_INSURANCE = "is_insurance";
    private static final String IS_BRAND = "is_brand";
    private static final String NEED_TIP = "need_tip";
    private static final String PROMOTION = "promotion";

    private final DataSource source;

    ShopRepository(DataSource source) {
        super(source);
        this.source = source;
    }

    @Override
    protected ShopProfile parse(ResultSet rs) throws Exception {
        ShopProfile.Builder builder = ShopProfile.newBuilder()
                .setId(rs.getString(SHOP_ID))
                .setImgUrl(rs.getString(SHOP_URL))
                .setIsNewShop(ShopUtils.isNewShop(rs.getDate(DATE_OF_REGISTRATION)))
                .setShopName(rs.getString(SHOP_NAME))
                .setIsNewShop(ShopUtils.isNewShop(rs.getDate(DATE_OF_REGISTRATION)))
                .setIsBird(rs.getBoolean(IS_BIRD))
                .setIsInsurance(rs.getBoolean(IS_INSURANCE))
                .setIsBrand(rs.getBoolean(IS_BRAND))
                .setNeedtip(rs.getBoolean(NEED_TIP));

        String promotionsJson = rs.getString(PROMOTION);
        JSONArray array = new JSONArray(promotionsJson);
        for (int i = 0; i < array.length(); i++) {
            final Promotion.Builder promotionBuilder = Promotion.newBuilder();
            JsonFormat.parser().ignoringUnknownFields().merge(array.getJSONObject(i).toString(), promotionBuilder);
            builder.addShopActivity(promotionBuilder.build());
        }

        return builder.build();
    }

    public CompletionStage<String> create(ShopProfile shop) {
        List<String> columns = Arrays.asList(
                SHOP_ID, SHOP_NAME, SHOP_URL, DATE_OF_REGISTRATION, LAT, LNG, IS_BIRD, IS_INSURANCE, IS_BRAND, NEED_TIP, PROMOTION);
        String sql = "INSERT INTO " + TABLE_NAME + " (" + getColumnNames(columns) + ") "
                + "VALUES(" + getQuestionMarks(columns) + ")";

        return CompletableFuture.supplyAsync(() -> {
            try (Connection conn = source.getConnection()) {
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setObject(1, shop.getId());
                    stmt.setObject(2, shop.getShopName());
                    stmt.setObject(3, shop.getImgUrl());
                    stmt.setObject(4, EleDateFormat.getCurrentDate());
                    stmt.setObject(5, 13.0412658);
                    stmt.setObject(6, 80.2338514);
                    stmt.setObject(7, shop.getIsBird());
                    stmt.setObject(8, shop.getIsInsurance());
                    stmt.setObject(9, shop.getIsBrand());
                    stmt.setObject(10, shop.getNeedtip());
                    stmt.setObject(11, protoArrayToJson(shop.getShopActivityList()));
                    stmt.executeUpdate();

                    return shop.getId();
                }
            } catch (Exception e) {
                throw new RepositoryException("Error creating shop", e);
            }
        });
    }

    public CompletionStage<List<ShopProfile>> getAll() {
        String sql = "SELECT * FROM " + TABLE_NAME;
        return CompletableFuture.supplyAsync(() -> {
             try (Connection conn = source.getConnection()) {
                 List<ShopProfile> list = new ArrayList<>();
                 try (Statement stmt = conn.createStatement()) {
                     try (ResultSet rs = stmt.executeQuery(sql)) {
                         while (rs.next()) {
                            list.add(parse(rs));
                         }
                     }

                 }
                 return list;
             } catch (Exception e) {
                 throw new RepositoryException("Error getting all shops", e);
             }
        });
    }
}

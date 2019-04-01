package com.ele.data.repositories.cassandra.migrations;

import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SimpleStatement;
import com.hhandoko.cassandra.migration.api.migration.java.JavaMigration;

import static com.ele.data.repositories.cassandra.CassandraShopRepository.*;
import static com.ele.data.repositories.cassandra.CassandraStorage.CREATE_TABLE;

public class V1__create_shop_table implements JavaMigration {
    @Override
    public void migrate(Session session) throws Exception {
        String cql = CREATE_TABLE + TABLE + " ("
                + FIELDS
                + "PRIMARY KEY (" + SHOP_ID + ")"
                + ")";

        SimpleStatement stmt = new SimpleStatement(cql);
        stmt.setConsistencyLevel(ConsistencyLevel.ALL);
        session.execute(stmt);
    }
}

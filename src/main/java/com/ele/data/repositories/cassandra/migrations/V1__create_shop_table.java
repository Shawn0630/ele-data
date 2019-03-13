package com.ele.data.repositories.cassandra.migrations;

import com.datastax.driver.core.Session;
import com.hhandoko.cassandra.migration.api.migration.java.JavaMigration;

public class V1__create_shop_table implements JavaMigration {
    @Override
    public void migrate(Session session) throws Exception {

    }
}

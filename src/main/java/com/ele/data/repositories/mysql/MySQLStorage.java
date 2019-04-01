package com.ele.data.repositories.mysql;

import com.ele.data.repositories.SystemStorage;
import com.github.racc.tscg.TypesafeConfig;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.commons.dbcp2.BasicDataSource;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class MySQLStorage implements SystemStorage {

    private static final Logger LOG = LoggerFactory.getLogger(MySQLStorage.class);

    private final String driverClass;
    private final String url;
    private final String username;
    private final String password;
    private final String migrationLocation;

    private final BasicDataSource dataSource;

    private final ShopRepository shopRepo;

    @Inject
    public MySQLStorage (
       @TypesafeConfig("ele.data.mysql.drive") final String driverClass,
       @TypesafeConfig("ele.data.mysql.url") final String url,
       @TypesafeConfig("ele.data.mysql.username") final String username,
       @TypesafeConfig("ele.data.mysql.password") final String password,
       @TypesafeConfig("ele.data.mysql.migration-location") final String migrationLocation
    ) {
        this.driverClass = driverClass;
        this.url = url;
        this.username = username;
        this.password = password;
        this.migrationLocation = migrationLocation;

        migrate();
        this.dataSource = createDataSource();

        shopRepo = new ShopRepository(this.dataSource);

    }

    private void migrate() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(this.url, this.username, this.password);
        flyway.setLocations(this.migrationLocation);
        flyway.migrate();
    }

    private BasicDataSource createDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(this.url);
        ds.setUsername(this.username);
        ds.setPassword(this.password);

        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);

        return ds;
    }

    @Override
    public ShopRepository getShopRepository() {
        return this.shopRepo;
    }
}

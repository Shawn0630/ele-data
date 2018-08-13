package com.ele.data.repositories;

import com.github.racc.tscg.TypesafeConfig;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.commons.pool.impl.GenericObjectPoolFactory;
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

    private ObjectPool pool;

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
        createPool();

        shopRepo = new ShopRepository(this.pool);

    }

    private void migrate() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(this.url, this.username, this.password);
        flyway.setLocations(this.migrationLocation);
        flyway.migrate();
    }

    private void createPool() {
        MySQLPoolFactory mySQLPoolFactory = new MySQLPoolFactory(driverClass, url, username, password);

        GenericObjectPool.Config config = new GenericObjectPool.Config();
        config.maxActive = 10;
        config.testOnBorrow = true;
        config.testWhileIdle = true;
        config.timeBetweenEvictionRunsMillis = 10000;
        config.minEvictableIdleTimeMillis = 60000;


        GenericObjectPoolFactory genericObjectPoolFactory = new GenericObjectPoolFactory(mySQLPoolFactory, config);
        this.pool = genericObjectPoolFactory.createPool();
    }

    @Override
    public ShopRepository getShopRepository() {
        return this.shopRepo;
    }
}

package com.ele.data.repositories;

import com.github.racc.tscg.TypesafeConfig;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.commons.pool.impl.GenericObjectPoolFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class MySQLStorage {

    private static final Logger LOG = LoggerFactory.getLogger(MySQLStorage.class);

    private final String driverClass;
    private final String url;
    private final String username;
    private final String password;

    private final ShopRepository shopRopo;

    @Inject
    public MySQLStorage (
       @TypesafeConfig("ele.data.mysql.drive") String driverClass,
       @TypesafeConfig("ele.data.mysql.url") String url,
       @TypesafeConfig("ele.data.mysql.username") String username,
       @TypesafeConfig("ele.data.mysql.password") String password
    ) {
        this.driverClass = driverClass;
        this.url = url;
        this.username = username;
        this.password = password;

        MySQLPoolFactory mySQLPoolFactory = new MySQLPoolFactory(driverClass, url, username, password);

        GenericObjectPool.Config config = new GenericObjectPool.Config();
        config.maxActive = 10;
        config.testOnBorrow = true;
        config.testWhileIdle = true;
        config.timeBetweenEvictionRunsMillis = 10000;
        config.minEvictableIdleTimeMillis = 60000;


        GenericObjectPoolFactory genericObjectPoolFactory = new GenericObjectPoolFactory(mySQLPoolFactory, config);
        ObjectPool pool = genericObjectPoolFactory.createPool();

        shopRopo = new ShopRepository(pool);

    }

}

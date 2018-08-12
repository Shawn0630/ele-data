package com.ele.data.repositories;

import com.github.racc.tscg.TypesafeConfig;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;

@Singleton
public class MySQLStorage {

    private static final Logger LOG = LoggerFactory.getLogger(MySQLStorage.class);

    private final String driverClass;
    private final String url;
    private final String username;
    private final String password;

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

        try {
            Class.forName(driverClass);
            Connection con = DriverManager.getConnection(url, username, password);
            LOG.info("Connected to mysql");
        } catch (Exception e) {
            LOG.info("Unable to connect to mysql");
            System.out.println(e);
        }

    }
}

package com.ele.data.repositories;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLPoolFactory extends BasePoolableObjectFactory {

    private static final Logger LOG = LoggerFactory.getLogger(MySQLPoolFactory.class);

    private final String driverClass;
    private final String url;
    private final String username;
    private final String password;

    MySQLPoolFactory(String driverClass, String url, String username, String password) {
        this.driverClass = driverClass;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Object makeObject() throws Exception {
        try {
            Class.forName(driverClass);
            Connection con = DriverManager.getConnection(this.url, this.username, this.password);
            return con;
        } catch (Exception e) {
            LOG.error("Unable to create connection");
            return null;
        }
    }
}

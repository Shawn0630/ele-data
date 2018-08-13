package com.ele.data.repositories;

import org.apache.commons.pool.ObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MySQLRepository {
    private static final Logger LOG = LoggerFactory.getLogger(MySQLRepository.class);

    private final ObjectPool connPool;

    MySQLRepository(ObjectPool connPool) {
        this.connPool = connPool;
    }


    protected void close(Connection conn) {
        if (conn != null) {
            try {
                connPool.returnObject(conn);
            }
            catch (Exception e) {
                LOG.error("Failed to return the connection to the pool", e);
            }
        }
    }

    protected void close(ResultSet res) {
        if (res != null) {
            try {
                res.close();
            } catch (SQLException e) {
                LOG.error("Failed to close databse resultset", e);
            }
        }
    }

    protected void close(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                LOG.error("Failed to close databse statment", e);
            }
        }
    }

}

package com.ele.data.repositories;

import org.apache.commons.pool.ObjectPool;

public class ShopRepository extends MySQLRepository{
    ShopRepository(ObjectPool connPool) {
        super(connPool);
    }
}

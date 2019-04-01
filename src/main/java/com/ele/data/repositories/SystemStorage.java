package com.ele.data.repositories;

import com.ele.data.repositories.mysql.ShopRepository;

public interface SystemStorage {
    ShopRepository getShopRepository();
}

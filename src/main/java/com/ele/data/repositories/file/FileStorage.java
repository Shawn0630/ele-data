package com.ele.data.repositories.file;

import com.ele.data.repositories.SystemStorage;
import com.ele.data.repositories.mysql.ShopRepository;

public class FileStorage implements SystemStorage {
    @Override
    public ShopRepository getShopRepository() {
        return null;
    }
}

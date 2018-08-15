package com.ele.data.utils;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public enum Dataconfig {;
    private static Config config = ConfigFactory.defaultApplication().withFallback(ConfigFactory.defaultReference());

    public static int getNewShopPromotionPeriod() {
        return config.getInt("ele.data.core.default.newShopPromotionPeriod");
    }

}

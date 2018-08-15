package com.ele.data.utils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ShopUtils {

    public static final boolean isNewShop(Date date_of_registration) {
       Date current = new Date();
       return TimeUnit.DAYS.convert(current.getTime() - date_of_registration.getTime(), TimeUnit.MILLISECONDS) > Dataconfig.getNewShopPromotionPeriod();
    }
}

package com.alten.shop.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Util {

    private static int SCALE = 2;

    public static BigDecimal scale(BigDecimal value) {
        return value.setScale(SCALE, RoundingMode.HALF_UP);
    }

    public static BigDecimal scale(BigDecimal value, int scale) {
        return value.setScale(scale, RoundingMode.HALF_UP);
    }

}

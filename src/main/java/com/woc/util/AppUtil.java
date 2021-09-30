package com.woc.util;

import java.util.Random;

public class AppUtil {

    public static long getRandomMobileNumber() {
        long mobileNumber = 9000000000l + new Random().nextLong(999999999);
        return mobileNumber;
    }

    public static int getRandomNumber() {
        return new Random().nextInt(1000);
    }

}

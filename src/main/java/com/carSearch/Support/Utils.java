package com.carSearch.Support;

import java.util.Random;

public class Utils {
        public static int generateRandomMileage() {
            Random random = new Random();
            return random.nextInt(10000);

        }



}

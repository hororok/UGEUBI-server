package duksung.backend.hororok.ugeubi.common.util;

import java.util.Random;

public class RandomNumber {
    private final static  Integer MIN = 100000;
    private final static  Integer MAX_RANGE = 900000;

    public static Integer generateRandomNumber(){
        Random random = new Random();
        return random.nextInt(MAX_RANGE)+MIN;
    }
}

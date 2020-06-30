package duksung.backend.hororok.ugeubi.common.util;

import java.util.Random;

public class RandomString {

    private final static Integer RANDOM_STRING_LENGTH = 6;

    public static String generateRandomString(){
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();

        for(int count=0; count<RANDOM_STRING_LENGTH; count++){
            int randomType = random.nextInt(3);
            switch (randomType){
                case 0: //a-z
                    stringBuffer.append((char)(random.nextInt(26)+97));
                    break;
                case 1: //A-Z
                    stringBuffer.append((char)(random.nextInt(26)+65));
                    break;
                case 2: //0-9
                    stringBuffer.append(random.nextInt(10));
                    break;
            }
        }
        return stringBuffer.toString();
    }
}

package duksung.backend.hororok.ugeubi.common.util;

public class ReplaceString {

    public static String changeAsterisk(String id){
        StringBuilder stringBuilder = new StringBuilder();
        String frontId;
        int size;

        if(id.length() <= 4){
            frontId = id.substring(0,2);
            size = id.substring(2).length();
        }else{
            frontId = id.substring(0,4);
            size = id.substring(4).length();
        }

        stringBuilder.append(frontId);
        for(int i=0; i<size; i++){
            stringBuilder.append("*");
        }

        return stringBuilder.toString();
    }
}

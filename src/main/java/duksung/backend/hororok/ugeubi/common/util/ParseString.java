package duksung.backend.hororok.ugeubi.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParseString {
    public static Date toDate(String stringOfDate){
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(stringOfDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package bookface.testutil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TypicalDates {


    public static final Date TYPICAL_DATE;

    static {
        try {
            TYPICAL_DATE = new SimpleDateFormat("yyyy-MM-dd").parse("2018-08-08");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

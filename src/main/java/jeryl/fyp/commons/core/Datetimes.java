package jeryl.fyp.commons.core;

/**
 * Container for accepted datetime formats.
 */
public class Datetimes {

    public static final String[] ACCEPTABLE_DATETIME_FORMATS = {
        "MMM dd uuuu HHmm", "MMM dd uuuu HH:mm",
        "dd/MM/uuuu HHmm", "dd/MM/uuuu HH:mm",
        "dd-MM-uuuu HHmm", "dd-MM-uuuu HH:mm",
        "uuuu/MM/dd HHmm", "uuuu/MM/dd HH:mm",
        "uuuu/MM/dd'T'HHmm", "uuuu/MM/dd'T'HH:mm",
        "uuuu-MM-dd HHmm", "uuuu-MM-dd HH:mm",
        "dd MMM uuuu HHmm", "dd MMM uuuu HH:mm",
        "MMM dd, uuuu HHmm", "MMM dd, uuuu HH:mm"
    };

}

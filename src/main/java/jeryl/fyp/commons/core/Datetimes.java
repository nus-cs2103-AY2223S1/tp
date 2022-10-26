package jeryl.fyp.commons.core;

/**
 * Container for accepted datetime formats.
 */
public class Datetimes {

    public static final String[] ACCEPTABLE_DATETIME_FORMATS = {
        "MMM dd yyyy HHmm", "MMM dd yyyy HH:mm",
        "dd/MM/yyyy HHmm", "dd/MM/yyyy HH:mm",
        "dd-MM-yyyy HHmm", "dd-MM-yyyy HH:mm",
        "yyyy/MM/dd HHmm", "yyyy/MM/dd HH:mm",
        "yyyy/MM/dd'T'HHmm", "yyyy/MM/dd'T'HH:mm",
        "yyyy-MM-dd HHmm", "yyyy-MM-dd HH:mm",
        "dd MMM yyyy HHmm", "dd MMM yyyy HH:mm",
        "MMM dd, yyyy HHmm", "MMM dd, yyyy HH:mm"
    };

}

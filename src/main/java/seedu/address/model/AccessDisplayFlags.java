package seedu.address.model;

/**
 * Enum to denote the binary bit flag of access and print permission of
 * attributes and DisplayItems.
 */
public final class AccessDisplayFlags {
    public static int DISPLAY_OK = 1;
    public static int MENU_OK = 1 << 1;
    public static int GROUP = 1 << 2;
    public static int TASK = 1 << 3;
    public static int PERSON = 1 << 4;
    public static int ACCESS_OK = 0b11100;
    public static int HIDE_TYPE = 1 << 5;
    public static int DEFAULT = 0b111111;
}

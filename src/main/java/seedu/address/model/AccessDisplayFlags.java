package seedu.address.model;

/**
 * Enum to denote the binary bit flag of access and print permission of
 * attributes and DisplayItems.
 */
public final class AccessDisplayFlags {
    public static final int DISPLAY_OK = 1;
    public static final int MENU_OK = 1 << 1;
    public static final int GROUP = 1 << 2;
    public static final int TASK = 1 << 3;
    public static final int PERSON = 1 << 4;
    public static final int ACCESS_OK = 0b11100;
    public static final int HIDE_TYPE = 1 << 5;
    public static final int DEFAULT = 0b111111;
}

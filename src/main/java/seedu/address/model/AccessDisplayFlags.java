package seedu.address.model;

/**
 * Enum to denote the binary bit flag of access and print permission of attributes and DisplayItems.
 */
public final class AccessDisplayFlags {
    // flags for storage and display permissions
    public static final int DISPLAY_OK = 1;
    public static final int MENU_OK = 1 << 1;
    public static final int GROUP = 1 << 2;
    public static final int TASK = 1 << 3;
    public static final int PERSON = 1 << 4;
    public static final int HIDE_TYPE = 1 << 5;
    public static final int ACCESS_OK = 0b11100;
    public static final int DEFAULT = 0b011111;

    // flags for style and formatting of labels
    public static final int BOLD = 1;
    public static final int ITALIC = 1 << 1;
    public static final int UNDERLINE = 1 << 2;
    public static final int STRIKETHROUGH = 1 << 3;
    public static final int DROPSHADOW = 1 << 4;

    // where there is a conflict, left > center > right
    public static final int LEFT_JUSTIFY = 1 << 5;
    public static final int CENTER_JUSTIFY = 1 << 6;
    public static final int RIGHT_JUSTIFY = 1 << 7;

    // where there is a conflict, normal > big > small
    // big font will show as normal when in menu view !
    public static final int FONT_SIZE_NORMAL = 1 << 8;
    public static final int FONT_SIZE_BIG = 1 << 9;
    public static final int FONT_SIZE_SMALL = 1 << 10;

    // public static final int DEFAULT_STYLE = 0b00100100000;
    public static final int DEFAULT_STYLE = 0b00100100000;
    public static final int HEADER_STYLE = 0b01001010101;
}

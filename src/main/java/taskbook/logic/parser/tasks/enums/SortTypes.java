package taskbook.logic.parser.tasks.enums;

/**
 * Represents the different types of sorts that TaskBook supports for its task list
 */
public enum SortTypes {
    DESC_ALPHA("a"),
    DESC_ADDED_CHRON("ac"),
    INVALID("INVALID_SORT_TYPE");

    public final String flag;
    private SortTypes(String flag) {
        this.flag = flag;
    }
    @Override
    public String toString() {
        return flag;
    }

    /**
     * Returns a SortType that matches the input String.
     * @param str Input string.
     * @return the matching SortType.
     */
    public static SortTypes parse(String str) {
        switch (str) {
        case "a":
            return DESC_ALPHA;
        case "ac":
            return DESC_ADDED_CHRON;
        default:
            return INVALID;
        }
    }
}

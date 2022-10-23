package taskbook.logic.parser.contacts.enums;

/**
 * Represents the different types of sorts that TaskBook supports for its task list
 */
public enum SortTypes {
    DESC_ALPHABETICAL("a"),
    DESC_CHRONOLOGICAL_ADDED("ca"),
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
            return DESC_ALPHABETICAL;
        case "ca":
            return DESC_CHRONOLOGICAL_ADDED;
        default:
            return INVALID;
        }
    }
}

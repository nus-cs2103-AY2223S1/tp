package taskbook.logic.parser.contacts.enums;

/**
 * Represents the different types of sorts that TaskBook supports for its task list
 */
public enum SortTypes {
    NAME_ALPHABETICAL("a"),
    NAME_REVERSE_ALPHABETICAL("ra"),
    CHRONOLOGICAL_ADDED("ca"),
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
            return NAME_ALPHABETICAL;
        case "ra":
            return NAME_REVERSE_ALPHABETICAL;
        case "ca":
            return CHRONOLOGICAL_ADDED;
        default:
            return INVALID;
        }
    }
}

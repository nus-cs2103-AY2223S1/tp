package taskbook.logic.parser.tasks.enums;

/**
 * Represents the different types of sorts that TaskBook supports for its task list
 */
public enum SortTypes {
    DESC_ALPHABETICAL("a"),
    DESC_REVERSE_ALPHABETICAL("ra"),
    CHRONOLOGICAL_ADDED("ca"),
    CHRONOLOGICAL_DATE("cd"),
    REVERSE_CHRONOLOGICAL_DATE("rcd"),
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
        case "ra":
            return DESC_REVERSE_ALPHABETICAL;
        case "ca":
            return CHRONOLOGICAL_ADDED;
        case "cd":
            return CHRONOLOGICAL_DATE;
        case "rcd":
            return REVERSE_CHRONOLOGICAL_DATE;
        default:
            return INVALID;
        }
    }
}

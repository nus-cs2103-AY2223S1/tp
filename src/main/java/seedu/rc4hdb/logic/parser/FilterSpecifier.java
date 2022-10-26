package seedu.rc4hdb.logic.parser;

/**
 *  Wrapper for the specifier entered for the filter command
 */
public class FilterSpecifier {
    private final String specifier;

    /**
     * Constructor to create a new specifier object
     * @param specifier string parsed from the filter command entered by the user
     */
    public FilterSpecifier(String specifier) {
        this.specifier = specifier;
    }

    public String getSpecifier() {
        return specifier;
    }

}

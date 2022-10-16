package seedu.rc4hdb.logic.parser;

import seedu.rc4hdb.commons.core.Messages;
import seedu.rc4hdb.logic.parser.exceptions.ParseException;

public class FilterSpecifier {
    private final String specifier;

    public FilterSpecifier(String specifier) {
            this.specifier = specifier;
    }

    public String getSpecifier() {
        return specifier;
    }

    public boolean isAll(){
        return specifier == "all";
    }

    public boolean isAny() {
        return specifier == "any";
    }
}

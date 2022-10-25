package seedu.rc4hdb.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.rc4hdb.logic.parser.commandparsers.ColumnManipulatorCommandParser;

public class TypicalColumnManipulatorInputs {

    // The letters here correspond to the list counterparts of the same name in TypicalFieldLists

    public static final String ALL_VALID_LETTERS = "i n p e m r g t h"; // order does not matter

    public static final String VALID_LETTERS = "n p e t";

    public static final String VALID_LETTERS_COMPLEMENT = "i r m g h";

    public static final String DUPLICATE_LETTERS = "n p e t p";

    public static final String MIXED_CASE_LETTERS = "n P e T";

    public static final String INVALID_LETTERS = "w x y z";

    public static final String EMPTY_STRING = "";
}

package seedu.rc4hdb.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.rc4hdb.logic.commands.residentcommands.ColumnManipulatorCommand;

/**
 * Utility class for testing column manipulator command parsers.
 */
public class TypicalFieldLists {
    public static final List<String> ALL_VALID_FIELDS = new ArrayList<>(ColumnManipulatorCommand.ALL_FIELDS);

    public static final List<String> VALID_FIELDS = new ArrayList<>(List.of("name", "phone", "email", "tags"));

    public static final List<String> DUPLICATE_FIELDS = new ArrayList<>(List.of("name", "phone", "email", "tags",
            "phone"));
    public static final List<String> MIXED_CASE_FIELDS = new ArrayList<>(List.of("nAmE", "pHoNe", "eMaIl", "tAgS"));

    public static final List<String> INVALID_FIELDS = new ArrayList<>(List.of("height", "weight"));

    public static final List<String> VALID_SUBSEQUENT_FIELDS_FOR_HIDE = new ArrayList<>(List.of("gender", "house"));

    public static final List<String> INVALID_SUBSEQUENT_FIELDS_FOR_HIDE = new ArrayList<>(List.of("name"));

    public static final List<String> VALID_SUBSEQUENT_FIELDS_FOR_SHOW = INVALID_SUBSEQUENT_FIELDS_FOR_HIDE;

    public static final List<String> INVALID_SUBSEQUENT_FIELDS_FOR_SHOW = VALID_SUBSEQUENT_FIELDS_FOR_HIDE;

    public static final List<String> ALSO_VALID_FIELDS = new ArrayList<>(List.of("room", "matric"));

    public static final List<String> HIDDEN_FIELDS_AFTER_VALID_SUBSEQUENT_HIDEONLY_COMMAND = new ArrayList<>(
            List.of("name", "phone", "email", "tags", "gender", "house"));
}

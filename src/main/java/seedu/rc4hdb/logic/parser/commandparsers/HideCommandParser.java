package seedu.rc4hdb.logic.parser.commandparsers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;
import seedu.rc4hdb.logic.commands.modelcommands.HideCommand;
import seedu.rc4hdb.logic.parser.Parser;
import seedu.rc4hdb.logic.parser.exceptions.ParseException;
import seedu.rc4hdb.model.resident.fields.ResidentFields;

public class HideCommandParser implements Parser<HideCommand> {

    public static final String INTENDED_USAGE = "Please (only) enter some fields after the hide command\n"
            + "Example: hide room gender matric";

    public static final String ERROR_MESSAGE = "Please only specify fields that correspond to resident data, "
            + "or check if you have made a typo.";

    @Override
    public HideCommand parse(String args) throws ParseException {
        requireNonNull(args);

        if (args.isEmpty()) {
            throw new ParseException(INTENDED_USAGE);
        }

        // Process global list of fields into lowercase list first
        List<String> allFields = ResidentFields.FIELDS.stream().map(String::toLowerCase).collect(Collectors.toList());

        // Create result list
        List<String> fieldsToInclude = new ArrayList<>();

        String[] specifiedFields = getSpecifiedFields(args);

        populateFieldLists(specifiedFields, fieldsToInclude, allFields);

        return new HideCommand(fieldsToInclude);
    }

    private String[] getSpecifiedFields(String args) {
        return args.trim().toLowerCase().split(" ");
    }

    private void populateFieldLists(String[] specifiedFields,
                                    List<String> fieldsToInclude,
                                    List<String> allFields) throws ParseException {
        for (String field : specifiedFields) {
            if (!allFields.contains(field)) {
                throw new ParseException(ERROR_MESSAGE);
            }
            fieldsToInclude.add(field);
        }
    }
}

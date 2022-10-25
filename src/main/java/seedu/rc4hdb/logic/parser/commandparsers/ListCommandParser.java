package seedu.rc4hdb.logic.parser.commandparsers;

import java.util.List;

import static java.util.Objects.requireNonNull;
import seedu.rc4hdb.logic.commands.modelcommands.HideOnlyCommand;
import seedu.rc4hdb.logic.commands.modelcommands.ListCommand;
import static seedu.rc4hdb.logic.commands.modelcommands.ListCommand.EXCLUDE_SPECIFIER;
import static seedu.rc4hdb.logic.commands.modelcommands.ListCommand.INCLUDE_SPECIFIER;
import seedu.rc4hdb.logic.parser.exceptions.ParseException;

public class ListCommandParser extends ColumnManipulatorCommandParser {

    public static final List<String> LIST_OF_SPECIFIERS = List.of(INCLUDE_SPECIFIER, EXCLUDE_SPECIFIER);

    @Override
    public ListCommand parse(String args) throws ParseException {
        requireNonNull(args);
        if (args.isEmpty()) {
            return new ListCommand();
        }

        String listSpecifier = getSpecifierIfPresent(args, LIST_OF_SPECIFIERS);
        String stringOfFieldsToProcess = getArgumentsAfterSpecifierIfPresent(args, LIST_OF_SPECIFIERS);

        List<String> fieldsToShow = getBaseFieldList(stringOfFieldsToProcess);
        List<String> fieldsToHide = getComplementFieldList(stringOfFieldsToProcess);

        if (listSpecifier.equals(INCLUDE_SPECIFIER)) {
            return new ListCommand(fieldsToShow, fieldsToHide);
        } else {
            return new ListCommand(fieldsToHide, fieldsToShow);
        }
    }

    @Override
    public String getCommandWord() {
        return HideOnlyCommand.COMMAND_WORD;
    }

    @Override
    public String getCommandVerbs() {
        return HideOnlyCommand.COMMAND_VERBS;
    }
}

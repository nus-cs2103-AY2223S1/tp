package seedu.uninurse.logic.parser;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_MEDICATION;

import java.util.List;
import java.util.NoSuchElementException;

import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.EditMedicationCommand;
import seedu.uninurse.logic.commands.EditMedicationCommand.EditMedicationDescriptor;
import seedu.uninurse.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditMedicationCommand object
 */
public class EditMedicationCommandParser implements Parser<EditMedicationCommand> {
    /**
     * Parses the given arguments in the context of the EditMedicationCommand
     * and returns an EditMedicationCommand object for execution.
     *
     * @param args the string of arguments given
     * @return EditMedicationCommand
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditMedicationCommand parse(String args) throws ParseException {
        requireAllNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MEDICATION);

        try {
            List<Index> indices = ParserUtil.parseTwoIndex(argMultimap.getPreamble());
            EditMedicationDescriptor editMedicationDescriptor =
                    ParserUtil.parseEditMedicationDescriptor(argMultimap.getValue(PREFIX_MEDICATION).orElseThrow());

            return new EditMedicationCommand(indices.get(0), indices.get(1), editMedicationDescriptor);
        } catch (NoSuchElementException nse) {
            // Handles missing prefix
            throw new ParseException(EditMedicationCommand.MESSAGE_NOT_EDITED);
        }
    }
}

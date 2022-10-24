package seedu.uninurse.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.uninurse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_MEDICATION;

import java.util.NoSuchElementException;

import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.AddMedicationCommand;
import seedu.uninurse.logic.parser.exceptions.ParseException;
import seedu.uninurse.model.medication.Medication;

/**
 * Parses input arguments and creates a new {@code AddMedicationCommand}.
 */
public class AddMedicationCommandParser implements Parser<AddMedicationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddMedicationCommand
     * and returns an AddMedicationCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddMedicationCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MEDICATION);

        Index index;
        Medication medication;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            medication = ParserUtil.parseMedication(argMultimap.getValue(PREFIX_MEDICATION).orElseThrow());
        } catch (ParseException pe) {
            // handles invalid indices and medications
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddMedicationCommand.MESSAGE_USAGE), pe);
        } catch (NoSuchElementException nse) {
            // Handles missing prefix
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMedicationCommand.MESSAGE_USAGE));
        }

        return new AddMedicationCommand(index, medication);
    }
}

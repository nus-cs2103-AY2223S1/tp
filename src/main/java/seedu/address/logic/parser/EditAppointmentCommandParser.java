package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL_TEST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SLOT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditAppointmentCommand;
import seedu.address.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
/**
 * Parses input arguments and creates a new EditAppointmentCommand object
 */
public class EditAppointmentCommandParser implements Parser<EditAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditAppointmentCommand
     * and returns an EditAppointmentCommand object for execution.
     *
     * @throws seedu.address.logic.parser.exceptions.ParseException if the user input does not conform
     *                                                              the expected format
     */
    public EditAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_MEDICAL_TEST, PREFIX_SLOT, PREFIX_DOCTOR);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditAppointmentCommand.MESSAGE_USAGE), pe);
        }

        EditAppointmentDescriptor editAppointmentDescriptor = createEditAppointmentDescriptor(argMultimap);

        return new EditAppointmentCommand(index, editAppointmentDescriptor);
    }

    private EditAppointmentDescriptor createEditAppointmentDescriptor(ArgumentMultimap argMultimap)
            throws ParseException {
        EditAppointmentDescriptor editAppointmentDescriptor = new EditAppointmentDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editAppointmentDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_SLOT).isPresent()) {
            editAppointmentDescriptor.setSlot(ParserUtil.parseSlot(argMultimap.getValue(PREFIX_SLOT).get()));
        }
        if (argMultimap.getValue(PREFIX_MEDICAL_TEST).isPresent()) {
            editAppointmentDescriptor.setMedicalTest(
                    ParserUtil.parseMedicalTest(argMultimap.getValue(PREFIX_MEDICAL_TEST).get()));
        }
        if (argMultimap.getValue(PREFIX_DOCTOR).isPresent()) {
            editAppointmentDescriptor.setDoctor(ParserUtil.parseDoctor(argMultimap.getValue(PREFIX_DOCTOR).get()));
        }
        if (!editAppointmentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditAppointmentCommand.MESSAGE_NOT_EDITED);
        }
        return editAppointmentDescriptor;
    }
}

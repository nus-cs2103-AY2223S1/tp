package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECORD;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditRecordCommand;
import seedu.address.logic.commands.EditRecordCommand.EditRecordDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.record.Medication;

/**
 * Parses input arguments and creates a new EditRecordCommand object
 */
public class EditRecordCommandParser implements Parser<EditRecordCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditRecordCommand
     * and returns an EditRecordCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditRecordCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_RECORD, PREFIX_MEDICATION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditRecordCommand.MESSAGE_USAGE), pe);
        }

        EditRecordDescriptor editRecordDescriptor = new EditRecordDescriptor();
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editRecordDescriptor.setRecordDate(ParserUtil.parseRecordDate(argMultimap.getValue(PREFIX_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_RECORD).isPresent()) {
            editRecordDescriptor.setRecord(ParserUtil.parseRecordData(argMultimap.getValue(PREFIX_RECORD).get()));
        }

        parseMedicationsForEdit(argMultimap.getAllValues(PREFIX_MEDICATION))
                .ifPresent(editRecordDescriptor::setMedications);

        if (!editRecordDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditRecordCommand.MESSAGE_NOT_EDITED);
        }

        return new EditRecordCommand(index, editRecordDescriptor);
    }

    /**
     * Parses {@code Collection<String> medications} into a {@code Set<Medication>} if {@code medications} is non-empty.
     * If {@code medications} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Medication>} containing zero medications.
     */
    private Optional<Set<Medication>> parseMedicationsForEdit(Collection<String> medications) throws ParseException {
        assert medications != null;

        if (medications.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> medicationSet =
                medications.size() == 1 && medications.contains("") ? Collections.emptySet() : medications;
        return Optional.of(ParserUtil.parseMedications(medicationSet));
    }

}

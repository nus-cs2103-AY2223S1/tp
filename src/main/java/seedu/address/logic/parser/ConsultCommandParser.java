package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_MISSING_DIAGNOSIS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIAGNOSIS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICATION;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ConsultCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.PastAppointment;
import seedu.address.model.tag.Medication;

/**
 * Parses input arguments and creates a new ConsultCommand object.
 */
public class ConsultCommandParser implements Parser<ConsultCommand> {
    @Override
    public ConsultCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MEDICATION, PREFIX_DIAGNOSIS);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ConsultCommand.MESSAGE_USAGE), pe);
        }
        if (argMultimap.getValue(PREFIX_DIAGNOSIS).isEmpty()
                || argMultimap.getValue(PREFIX_DIAGNOSIS).get().equals("")) {
            throw new ParseException(MESSAGE_MISSING_DIAGNOSIS);
        }
        Set<Medication> medicationSet = new HashSet<>();
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_MEDICATION)).ifPresent(medicationSet::addAll);
        PastAppointment appt = new PastAppointment(LocalDate.now(), medicationSet,
                argMultimap.getValue(PREFIX_DIAGNOSIS).get());



        return new ConsultCommand(index, appt);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Medication>> parseTagsForEdit(Collection<String> medications) throws ParseException {
        assert medications != null;

        if (medications.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> medicationSet = medications.size() == 1 && medications.contains("")
                ? Collections.emptySet() : medications;
        return Optional.of(ParserUtil.parseMedications(medicationSet));
    }
}

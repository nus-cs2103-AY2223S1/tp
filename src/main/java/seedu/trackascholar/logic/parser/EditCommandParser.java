package seedu.trackascholar.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.trackascholar.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_APPLICATION_STATUS;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_SCHOLARSHIP;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.trackascholar.commons.core.index.Index;
import seedu.trackascholar.logic.commands.EditCommand;
import seedu.trackascholar.logic.parser.exceptions.ParseException;
import seedu.trackascholar.model.major.Major;

/**
 * Parses input arguments and creates a new EditCommand object.
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_SCHOLARSHIP, PREFIX_APPLICATION_STATUS, PREFIX_MAJOR);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditCommand.EditApplicantDescriptor editApplicantDescriptor = new EditCommand.EditApplicantDescriptor();
        if (isPrefixPresent(argMultimap, PREFIX_NAME)) {
            editApplicantDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (isPrefixPresent(argMultimap, PREFIX_PHONE)) {
            editApplicantDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (isPrefixPresent(argMultimap, PREFIX_EMAIL)) {
            editApplicantDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (isPrefixPresent(argMultimap, PREFIX_SCHOLARSHIP)) {
            editApplicantDescriptor.setScholarship(ParserUtil
                    .parseScholarship(argMultimap.getValue(PREFIX_SCHOLARSHIP).get()));
        }
        if (isPrefixPresent(argMultimap, PREFIX_APPLICATION_STATUS)) {
            editApplicantDescriptor.setApplicationStatus(ParserUtil
                    .parseApplicationStatus(argMultimap.getValue(PREFIX_APPLICATION_STATUS).get()));
        }
        parseMajorsForEdit(argMultimap.getAllValues(PREFIX_MAJOR)).ifPresent(editApplicantDescriptor::setMajors);

        if (!editApplicantDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editApplicantDescriptor);
    }

    /**
     * Returns true if the prefix does not contain an empty {@code Optional} value in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isPrefixPresent(ArgumentMultimap argumentMultimap, Prefix prefix) {
        Optional<String> prefixValue = argumentMultimap.getValue(prefix);
        return prefixValue.isPresent();
    }

    /**
     * Parses {@code Collection<String> majors} into a {@code Set<Major>} if {@code majors} is non-empty.
     * If {@code majors} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Major>} containing zero majors.
     */
    private Optional<Set<Major>> parseMajorsForEdit(Collection<String> majors) throws ParseException {
        assert majors != null;

        if (majors.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> majorSet = majors.size() == 1 && majors.contains("") ? Collections.emptySet() : majors;
        return Optional.of(ParserUtil.parseMajors(majorSet));
    }

}

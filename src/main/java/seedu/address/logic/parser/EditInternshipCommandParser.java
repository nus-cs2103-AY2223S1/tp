package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditInternshipCommand;
import seedu.address.logic.commands.EditInternshipCommand.EditInternshipDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditInternshipCommand object
 */
public class EditInternshipCommandParser implements Parser<EditInternshipCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditInternshipCommand
     * and returns an EditInternshipCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditInternshipCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_COMPANY_NAME, PREFIX_INTERNSHIP_ROLE, PREFIX_INTERNSHIP_STATUS, PREFIX_INTERVIEW_DATE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditInternshipCommand.MESSAGE_USAGE), pe);
        }

        EditInternshipDescriptor editInternshipDescriptor = new EditInternshipDescriptor();
        if (argMultimap.getValue(PREFIX_COMPANY_NAME).isPresent()) {
            editInternshipDescriptor.setCompanyName(
                    ParserUtil.parseCompanyName(argMultimap.getValue(PREFIX_COMPANY_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_INTERNSHIP_ROLE).isPresent()) {
            editInternshipDescriptor.setInternshipRole(
                    ParserUtil.parseInternshipRole(argMultimap.getValue(PREFIX_INTERNSHIP_ROLE).get()));
        }
        if (argMultimap.getValue(PREFIX_INTERNSHIP_STATUS).isPresent()) {
            editInternshipDescriptor.setInternshipStatus(
                    ParserUtil.parseInternshipStatus(argMultimap.getValue(PREFIX_INTERNSHIP_STATUS).get()));
        }
        if (argMultimap.getValue(PREFIX_INTERVIEW_DATE).isPresent()) {
            editInternshipDescriptor.setInterviewDate(
                    ParserUtil.parseInterviewDate(argMultimap.getValue(PREFIX_INTERVIEW_DATE).get()));
        }

        if (!editInternshipDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditInternshipCommand.MESSAGE_NOT_EDITED);
        }

        return new EditInternshipCommand(index, editInternshipDescriptor);
    }


}

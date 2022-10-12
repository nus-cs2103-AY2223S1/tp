package seedu.phu.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.phu.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_APPLICATION_PROCESS;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_WEBSITE;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.phu.commons.core.index.Index;
import seedu.phu.logic.commands.EditCommand;
import seedu.phu.logic.commands.EditCommand.EditInternshipDescriptor;
import seedu.phu.logic.parser.exceptions.ParseException;
import seedu.phu.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_REMARK,
                        PREFIX_POSITION, PREFIX_APPLICATION_PROCESS, PREFIX_DATE, PREFIX_WEBSITE, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditInternshipDescriptor editInternshipDescriptor = new EditInternshipDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editInternshipDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editInternshipDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editInternshipDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_REMARK).isPresent()) {
            editInternshipDescriptor.setRemark(ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).get()));
        }
        if (argMultimap.getValue(PREFIX_POSITION).isPresent()) {
            editInternshipDescriptor.setPosition(ParserUtil.parsePosition(argMultimap.getValue(PREFIX_POSITION).get()));
        }
        if (argMultimap.getValue(PREFIX_APPLICATION_PROCESS).isPresent()) {
            editInternshipDescriptor.setApplicationProcess(
                    ParserUtil.parseApplicationProcess(argMultimap.getValue(PREFIX_APPLICATION_PROCESS).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editInternshipDescriptor.setDate(ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_WEBSITE).isPresent()) {
            editInternshipDescriptor.setWebsite(ParserUtil.parseWebsite(argMultimap.getValue(PREFIX_WEBSITE).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editInternshipDescriptor::setTags);

        if (!editInternshipDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editInternshipDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}

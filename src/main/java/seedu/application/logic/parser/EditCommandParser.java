package seedu.application.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.application.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.application.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.application.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.application.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.application.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.application.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.application.commons.core.index.Index;
import seedu.application.logic.commands.EditCommand;
import seedu.application.logic.commands.EditCommand.EditApplicationDescriptor;
import seedu.application.logic.parser.exceptions.ParseException;
import seedu.application.logic.parser.exceptions.ParseIntegerOverflowException;
import seedu.application.model.tag.Tag;

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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_COMPANY, PREFIX_CONTACT, PREFIX_EMAIL,
                PREFIX_POSITION, PREFIX_DATE, PREFIX_STATUS, PREFIX_TAG);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseIntegerOverflowException e) {
            // Rethrow exception if index formatted correctly but too large to store in an int
            throw e;
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditApplicationDescriptor editApplicationDescriptor = new EditApplicationDescriptor();
        if (argMultimap.getValue(PREFIX_COMPANY).isPresent()) {
            editApplicationDescriptor.setCompany(ParserUtil.parseCompany(argMultimap.getValue(PREFIX_COMPANY).get()));
        }
        if (argMultimap.getValue(PREFIX_CONTACT).isPresent()) {
            editApplicationDescriptor.setContact(ParserUtil.parseContact(argMultimap.getValue(PREFIX_CONTACT).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editApplicationDescriptor.setDate(ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editApplicationDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_POSITION).isPresent()) {
            editApplicationDescriptor.setPosition(
                    ParserUtil.parsePosition(argMultimap.getValue(PREFIX_POSITION).get()));
        }
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            editApplicationDescriptor.setStatus(ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editApplicationDescriptor::setTags);

        if (!editApplicationDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editApplicationDescriptor);
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

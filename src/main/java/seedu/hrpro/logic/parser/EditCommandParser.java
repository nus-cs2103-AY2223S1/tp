package seedu.hrpro.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.hrpro.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_BUDGET;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_PROJECT_NAME;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.hrpro.commons.core.index.Index;
import seedu.hrpro.logic.commands.EditCommand;
import seedu.hrpro.logic.commands.EditCommand.EditProjectDescriptor;
import seedu.hrpro.logic.parser.exceptions.ParseException;
import seedu.hrpro.model.tag.Tag;

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
                ArgumentTokenizer.tokenize(args, PREFIX_PROJECT_NAME, PREFIX_BUDGET, PREFIX_DEADLINE, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditProjectDescriptor editProjectDescriptor = new EditProjectDescriptor();
        if (argMultimap.getValue(PREFIX_PROJECT_NAME).isPresent()) {
            editProjectDescriptor.setProjectName(ParserUtil
                    .parseProjectName(argMultimap.getValue(PREFIX_PROJECT_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_BUDGET).isPresent()) {
            editProjectDescriptor.setBudget(ParserUtil.parseBudget(argMultimap.getValue(PREFIX_BUDGET).get()));
        }
        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            editProjectDescriptor.setDeadline(ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editProjectDescriptor::setTags);

        if (!editProjectDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editProjectDescriptor);
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

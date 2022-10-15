package hobbylist.logic.parser;

import static hobbylist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import hobbylist.commons.core.index.Index;
import hobbylist.logic.commands.EditCommand;
import hobbylist.logic.commands.EditCommand.EditActivityDescriptor;
import hobbylist.logic.parser.exceptions.ParseException;
import hobbylist.model.date.Date;
import hobbylist.model.tag.Tag;

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
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_NAME,
                        CliSyntax.PREFIX_DESCRIPTION, CliSyntax.PREFIX_TAG, CliSyntax.PREFIX_DATE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditActivityDescriptor editActivityDescriptor = new EditActivityDescriptor();
        if (argMultimap.getValue(CliSyntax.PREFIX_NAME).isPresent()) {
            editActivityDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(CliSyntax.PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_DESCRIPTION).isPresent()) {
            editActivityDescriptor
                    .setDescription(ParserUtil.parseDescription(argMultimap.getValue(CliSyntax.PREFIX_DESCRIPTION)
                    .get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(CliSyntax.PREFIX_TAG)).ifPresent(editActivityDescriptor::setTags);
        parseDateForEdit(argMultimap.getAllValues(CliSyntax.PREFIX_DATE)).ifPresent(editActivityDescriptor::setDate);
        if (!editActivityDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editActivityDescriptor);
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
    private Optional<List<Date>> parseDateForEdit(List<String> s) throws ParseException {
        assert s != null;
        if (s.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(ParserUtil.parseDate(s));
    }

}

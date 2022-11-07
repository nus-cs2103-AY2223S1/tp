package hobbylist.logic.parser;

import static hobbylist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Collections;
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
                        CliSyntax.PREFIX_DESCRIPTION, CliSyntax.PREFIX_TAG, CliSyntax.PREFIX_DATE,
                        CliSyntax.PREFIX_STATUS);

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
        if (argMultimap.getValue(CliSyntax.PREFIX_DATE).isPresent()) {
            if (argMultimap.getValue(CliSyntax.PREFIX_DATE).get().equals("")) {
                editActivityDescriptor.setDate(Optional.empty());
            } else {
                editActivityDescriptor.setDate(ParserUtil.parseDate(argMultimap.getValue(CliSyntax.PREFIX_DATE)));
            }
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_STATUS).isPresent()) {
            editActivityDescriptor
                    .setStatus(ParserUtil.parseStatus(argMultimap.getValue(CliSyntax.PREFIX_STATUS)
                            .get()));
        }
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
    private Optional<Date> parseDateForEdit(Optional<String> s) throws ParseException {
        assert s != null;
        return ParserUtil.parseDate(s);
    }

}

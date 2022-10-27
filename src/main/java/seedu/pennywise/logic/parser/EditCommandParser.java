package seedu.pennywise.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.pennywise.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.pennywise.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.pennywise.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.pennywise.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.pennywise.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.pennywise.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.Optional;

import seedu.pennywise.commons.core.index.Index;
import seedu.pennywise.logic.commands.EditCommand;
import seedu.pennywise.logic.commands.EditCommand.EditEntryDescriptor;
import seedu.pennywise.logic.parser.exceptions.ParseException;
import seedu.pennywise.model.entry.EntryType;
import seedu.pennywise.model.entry.Tag;

/**
 * Parses input arguments and creates a new {@code EditCommand} object.
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code EditCommand}
     * and returns an {@code EditCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TYPE, PREFIX_DESCRIPTION,
                        PREFIX_AMOUNT, PREFIX_DATE, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditEntryDescriptor editEntryDescriptor = new EditEntryDescriptor();
        if (argMultimap.getValue(PREFIX_TYPE).isPresent()) {
            editEntryDescriptor.setType(
                    ParserUtil.parseEntryType(argMultimap.getValue(PREFIX_TYPE).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editEntryDescriptor.setDescription(
                    ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_AMOUNT).isPresent()) {
            editEntryDescriptor.setAmount(ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editEntryDescriptor.setDate(ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent() && editEntryDescriptor.getType().isPresent()) {
            editEntryDescriptor.setTag(parseTagForEdit(
                    editEntryDescriptor.getType().get(),
                    argMultimap.getValue(PREFIX_TAG).get()).get());
        }

        if (!editEntryDescriptor.isAnyFieldEdited() || argMultimap.getValue(PREFIX_TYPE).isEmpty()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editEntryDescriptor);
    }

    /**
     * Parses {@code Collection<String> Tag} into a {@code Set<Tag>} if {@code tag} is non-empty.
     * If {@code tag} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tag.
     */
    private Optional<Tag> parseTagForEdit(EntryType type, String tag) throws ParseException {
        assert tag != null;
        return Optional.of(ParserUtil.parseTag(type, tag));
    }

}

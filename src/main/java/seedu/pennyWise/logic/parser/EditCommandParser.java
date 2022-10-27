package seedu.pennyWise.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.pennyWise.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.pennyWise.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.pennyWise.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.pennyWise.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.pennyWise.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.pennyWise.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.Optional;

import seedu.pennyWise.commons.core.index.Index;
import seedu.pennyWise.logic.commands.EditCommand;
import seedu.pennyWise.logic.commands.EditCommand.EditEntryDescriptor;
import seedu.pennyWise.logic.parser.exceptions.ParseException;
import seedu.pennyWise.model.entry.EntryType;
import seedu.pennyWise.model.entry.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
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

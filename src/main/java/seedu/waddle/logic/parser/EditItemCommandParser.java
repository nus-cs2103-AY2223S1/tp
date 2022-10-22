package seedu.waddle.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.waddle.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_PRIORITY;

import seedu.waddle.commons.core.index.Index;
import seedu.waddle.logic.commands.EditItemCommand;
import seedu.waddle.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditItemCommand object
 */
public class EditItemCommandParser implements Parser<EditItemCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditItemCommand
     * and returns an EditItemCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditItemCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_PRIORITY,
                                           PREFIX_COST, PREFIX_DURATION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                                   EditItemCommand.MESSAGE_USAGE), pe);
        }

        EditItemCommand.EditItemDescriptor editItemDescriptor = new EditItemCommand.EditItemDescriptor();
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editItemDescriptor.setDescription(
                    ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }

        if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            editItemDescriptor.setPriority(
                    ParserUtil.parsePriority(argMultimap.getValue(PREFIX_PRIORITY).get()));
        }

        if (argMultimap.getValue(PREFIX_COST).isPresent()) {
            editItemDescriptor.setCost(
                    ParserUtil.parseCost(argMultimap.getValue(PREFIX_COST).get()));
        }

        if (argMultimap.getValue(PREFIX_DURATION).isPresent()) {
            editItemDescriptor.setDuration(
                    ParserUtil.parseDuration(argMultimap.getValue(PREFIX_DURATION).get()));
        }

        if (!editItemDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditItemCommand.MESSAGE_NOT_EDITED);
        }

        return new EditItemCommand(index, editItemDescriptor);
    }

}

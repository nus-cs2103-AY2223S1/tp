package seedu.waddle.logic.parser;

import seedu.waddle.commons.core.index.Index;
import seedu.waddle.commons.exceptions.IllegalValueException;
import seedu.waddle.logic.Stages;
import seedu.waddle.logic.commands.FindCommand;
import seedu.waddle.logic.commands.PlanCommand;
import seedu.waddle.logic.commands.StageCommand;
import seedu.waddle.logic.parser.exceptions.ParseException;
import seedu.waddle.model.itinerary.NameContainsKeywordsPredicate;

import java.util.Arrays;
import java.util.Locale;

import static seedu.waddle.commons.core.Messages.*;
import static seedu.waddle.logic.parser.CliSyntax.PREFIX_STAGE;

import static java.util.Objects.requireNonNull;

/**
 * Parses input arguments and creates a new {@code RemarkCommand} object
 */
public class StageCommandParser implements Parser<StageCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code StageCommand}
     * and returns a {@code StageCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public StageCommand parse(String args) throws ParseException {
        Stages stage;

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, StageCommand.MESSAGE_USAGE));
        }

        try {
            stage = Stages.valueOf(trimmedArgs.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_STAGE, StageCommand.MESSAGE_USAGE));
        }

        return new StageCommand(stage);
    }
}
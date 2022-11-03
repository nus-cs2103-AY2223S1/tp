package seedu.address.logic.commands.teams;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.item.NameContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindTeamCommand extends TeamCommand {

    public static final String SUBCOMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = getFullCommand(SUBCOMMAND_WORD)
        + ": Finds all persons whose names contain any of "
        + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
        + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
        + "Example: " + getFullCommand(SUBCOMMAND_WORD) + " alice bob charlie";

    private NameContainsKeywordsPredicate<Group> predicate;

    public FindTeamCommand(NameContainsKeywordsPredicate<Group> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTeamList(predicate);
        return new CommandResult(
            String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredTeamList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof FindTeamCommand
                && predicate.equals(((FindTeamCommand) other).predicate)); // state check
    }

    @Override
    public Command setInput(Object additionalData) throws CommandException {
        if (additionalData == null || !(additionalData instanceof String)) {
            return this;
        }
        predicate = new NameContainsKeywordsPredicate<>(Arrays.asList(additionalData.toString().split("\\s+")));
        return this;
    }

    /**
     * Returns a Parser that parses the given {@code String} of arguments in the context of the
     * FindCommand and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public static Parser<FindTeamCommand> parser() {
        return new Parser<FindTeamCommand>() {
            /**
             * Parses the given {@code String} of arguments in the context of the FindCommand and returns a
             * FindCommand object for execution.
             *
             * @throws ParseException if the user input does not conform the expected format
             */
            public FindTeamCommand parse(String args) throws ParseException {
                String trimmedArgs = args.trim();
                if (trimmedArgs.isEmpty()) {
                    return new FindTeamCommand(null);
                }

                String[] nameKeywords = trimmedArgs.split("\\s+");

                return new FindTeamCommand(new NameContainsKeywordsPredicate<Group>(Arrays.asList(nameKeywords)));
            }
        };
    }
}

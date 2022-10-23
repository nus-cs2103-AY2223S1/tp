package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.ModContainsKeywordsPredicate;

/**
 * Finds and lists those in address book who have taken or are taking module(s) that contain(s)
 * the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class ModFindCommand extends ModCommand {
    public static final String COMMAND_WORD = "find";
    public static final String COMMAND_WORD_TAKEN = "taken";
    public static final String COMMAND_WORD_TAKING = "taking";
    public static final String MESSAGE_SUCCESS = "Successfully found those who are taking or have taken this mod!";
    public static final String MESSAGE_USAGE = ModCommand.COMMAND_WORD + " " + COMMAND_WORD + ": Finds those "
            + "who had taken or are now taking the specified module(s)"
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";
    private final ModContainsKeywordsPredicate predicate;

    /**
     * Constructor for ModFindCommand.
     * @param predicate Boolean condition for whether modules match all the given user inputs and the given condition,
     *     if any.
     */
    public ModFindCommand(ModContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()),
                false, false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.logic.commands.ModFindCommand // instanceof handles nulls
                && predicate.equals(((seedu.address.logic.commands.ModFindCommand) other).predicate)); // state check
    }
}

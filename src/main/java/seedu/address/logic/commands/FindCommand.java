package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.FunctionalInterfaces.Changer;
import seedu.address.commons.util.FunctionalInterfaces.Retriever;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.DisplayItem;
import seedu.address.model.item.NameContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand<T extends DisplayItem> extends Command {

    public static final String SUBCOMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = "type find"
        + ": Finds all items whose names contain any of "
        + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
        + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
        + "Example: person find alice bob charlie";

    public static final String SUCCESS_MESSAGE = "%1$d items found!";


    private NameContainsKeywordsPredicate<T> predicate;
    private final Changer<Predicate<T>> changer;
    private final Retriever<Integer> getSize;

    /**
     * Constructor for find command
     *
     * @param predicate filtering tool
     * @param changer sam to filter specific model list
     * @param getSize sam to get final size of filtered elements
     */
    public FindCommand(NameContainsKeywordsPredicate<T> predicate, Changer<Predicate<T>> changer,
        Retriever<Integer> getSize) {
        requireNonNull(changer);
        requireNonNull(getSize);
        this.predicate = predicate;
        if (predicate == null) {
            this.predicate = new NameContainsKeywordsPredicate<T>(List.of());
        }
        this.changer = changer;
        this.getSize = getSize;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        changer.apply(model, predicate);
        return new CommandResult(
            String.format(SUCCESS_MESSAGE, getSize.apply(model)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand<?>) other).predicate)); // state check
    }

    @Override
    public Command setInput(Object additionalData) throws CommandException {
        if (additionalData == null || !(additionalData instanceof String)) {
            return this;
        }
        predicate = new NameContainsKeywordsPredicate<>(Arrays.asList(additionalData.toString().split("\\s+")));
        return this;
    }
}

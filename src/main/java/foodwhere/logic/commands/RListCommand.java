package foodwhere.logic.commands;

import static foodwhere.model.Model.PREDICATE_SHOW_ALL_REVIEWS;
import static java.util.Objects.requireNonNull;

import foodwhere.model.Model;

/**
 * Lists all reviews in the address book in FoodWhere for the user.
 */
public class RListCommand extends Command {

    public static final String COMMAND_WORD = "rlist";

    public static final String MESSAGE_SUCCESS = "Listed all reviews";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredReviewList(PREDICATE_SHOW_ALL_REVIEWS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

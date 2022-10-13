package tuthub.logic.commands;

import static java.util.Objects.requireNonNull;
import static tuthub.model.Model.PREDICATE_SHOW_ALL_TUTORS;

import tuthub.model.Model;

/**
 * Lists all tutors in tuthub to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all tutor profiles";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTutorList(PREDICATE_SHOW_ALL_TUTORS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

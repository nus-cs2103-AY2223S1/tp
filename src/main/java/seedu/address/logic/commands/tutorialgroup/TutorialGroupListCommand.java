package seedu.address.logic.commands.tutorialgroup;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TUTORIAL_GROUPS;

import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.student.TutorialGroup;



/**
 * Lists all persons in the address book to the user.
 */
public class TutorialGroupListCommand extends Command {

    public static final String COMMAND_WORD = "tutorialList";

    public static final String MESSAGE_SUCCESS = "Listed all tutorial groups";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTutorialGroupList(PREDICATE_SHOW_ALL_TUTORIAL_GROUPS);
        List<TutorialGroup> groups = model.getFilteredTutorialGroupList();
        String display = "";

        for (int i = 0; i < groups.size(); i++) {
            display += groups.get(i).toString() + "\n";
        }

        return new CommandResult(display + MESSAGE_SUCCESS);
    }
}

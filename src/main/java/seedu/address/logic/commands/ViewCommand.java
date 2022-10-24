package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_SUCCESS = "Viewed Person: %1$s";

    private final Index index;
    private final Person person;

    public ViewCommand (Index index, Person person) {
        this.index = index;
        this.person = person;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        //view specific person in right side
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, person));
    }
}

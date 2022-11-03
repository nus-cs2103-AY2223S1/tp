package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.person.IsPersonInGroup;
import seedu.address.model.person.Person;


/**
 * Displays a group window.
 */
public class GroupCommand extends Command {

    public static final String COMMAND_WORD = "group";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Opens a group window containing the members "
            + "of the identified group. "
            + "Group names should not contain spaces.\n"
            + "Parameters: INDEX (must be a positive integer) GROUPNAME\n"
            + "Example: " + COMMAND_WORD + " friends";

    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";
    public static final String MESSAGE_SHOWING_GROUP = "Opened group window.";
    public static final String MESSAGE_SHOW_GROUP_FAIL = "The group does not exist.";

    private final Group groupToDisplay;
    private final IsPersonInGroup predicate;

    /**
     * @param groupToDisplay group to display
     */
    public GroupCommand(Group groupToDisplay) {
        requireNonNull(groupToDisplay);
        this.groupToDisplay = groupToDisplay;
        this.predicate = new IsPersonInGroup(groupToDisplay);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> personList = model.getAddressBook().getPersonList();
        List<Group> groupList = new ArrayList<>();

        personList.forEach((person) -> groupList.addAll(person.getGroups()));
        if (!groupList.contains(groupToDisplay)) {
            throw new CommandException(MESSAGE_SHOW_GROUP_FAIL);
        }

        model.updateGroupedPersonList(predicate);

        return new CommandResult(String.format(MESSAGE_SHOWING_GROUP), true, groupToDisplay);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GroupCommand // instanceof handles nulls
                && groupToDisplay.equals(((GroupCommand) other).groupToDisplay));
    }

}

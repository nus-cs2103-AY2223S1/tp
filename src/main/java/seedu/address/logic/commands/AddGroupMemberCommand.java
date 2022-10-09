package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.person.Assignment;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonGroup;

/**
 * Changes the remark of an existing person in the address book.
 */
public class AddGroupMemberCommand extends Command {

    public static final String COMMAND_WORD = "addmember";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds member to a specified group"
            + " Parameters: NAME " + PREFIX_GROUP + "GROUP " + PREFIX_NAME + "NAME\n"
            + "Example: " + COMMAND_WORD + " g/Group Alpha n/Alice Chee";

    public static final String MESSAGE_DUPLICATE_GROUP = "This person already exists in the address book.";
    public static final String MESSAGE_INVALID_PERSON = "This person is not in the address book.";
    public static final String MESSAGE_ASSIGN_GROUP_SUCCESS = "Added member";

    private final Name name;
    private final PersonGroup personGroup;

    /**
     * @param name of the person in the filtered person list to edit the remark
     * @param group of the person to be added to
     */
    public AddGroupMemberCommand(String group, String name) {
        requireAllNonNull(name, group);
        this.name = new Name(name);
        this.personGroup = new PersonGroup(group);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        ObservableList<Person> personList = model.getPersonWithName(this.name);
        Person personToGroup;

        try {
            personToGroup = personList.get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(MESSAGE_INVALID_PERSON);
        }

        ArrayList<PersonGroup> personGroup1 = personToGroup.getPersonGroup();

        Person editedPerson = new Person(
                personToGroup.getName(), personToGroup.getPhone(), personToGroup.getEmail(),
                personToGroup.getAddress(), personToGroup.getTags(), personToGroup.getAssignments(),
                personGroup);

        if (!personToGroup.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_GROUP);
        }

        model.setPerson(personToGroup, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_ASSIGN_GROUP_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddGroupMemberCommand)) {
            return false;
        }

        // state check
        AddGroupMemberCommand e = (AddGroupMemberCommand) other;
        return name.equals(e.name)
                && personGroup.equals(e.personGroup);
    }
}

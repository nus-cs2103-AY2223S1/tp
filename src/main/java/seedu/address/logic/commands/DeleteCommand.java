package seedu.address.logic.commands;


import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;



/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        List<Task> lastShownTaskList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }


        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());

        for (Task task : lastShownTaskList) {
            if (task.getPerson().equals(personToDelete)) {
                task.setPerson(new deletedPerson());
            }
        }

        model.deletePerson(personToDelete);
        model.update();
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }

    private static class deletedPerson extends Person {
        private static final Name name = new Name("This person has been deleted");
        private static final Address address = new Address("Deleted address");
        private static final Email email = new Email("dummyEmail@dummy.com");

        private static final Phone phone = new Phone("00000000");
        private static final Set<Tag> tags = new HashSet<>();
        private static final List<Task> tasks = new ArrayList<>();
        public deletedPerson() {
            super(name, phone, email, address, tags, tasks);
        }
    }

}

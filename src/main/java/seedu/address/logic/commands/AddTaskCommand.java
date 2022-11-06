package seedu.address.logic.commands;


import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.task.Task;

/**
 * Adds a task to the address book.
 */
public class AddTaskCommand extends Command {
    public static final String COMMAND_WORD = "addTask";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the address book. \n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_PRIORITY + "PRIORITY (low/medium/high)] "
            + "[" + PREFIX_CATEGORY + "CATEGORY (database/frontend/backend/uiux/presentation/others)] "
            + "[" + PREFIX_DEADLINE + "DEADLINE (YYYY-MM-DD, must be after current date)] "
            + "[" + PREFIX_PERSON + "PERSON EMAIL ADDRESS (must be a valid email)]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Create Initial UIUX Design "
            + PREFIX_DESCRIPTION + "Use FIGMA to create initial UIUX Design "
            + PREFIX_PRIORITY + "medium "
            + PREFIX_CATEGORY + "uiux "
            + PREFIX_DEADLINE + "2023-01-01 "
            + PREFIX_PERSON + "alexyeoh@example.com ";

    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the address book";
    private static final String MESSAGE_NO_PERSON_WITH_EMAIL = "There is no person with that email";
    private static final String MESSAGE_SUCCESS = "New task added: %1$s";
    private final Task toAdd;
    private final Email personEmailAddress;

    /**
     * Creates an AddTaskCommand to add the specified {@code Task}
     */
    public AddTaskCommand(Task task, Email personEmailAddress) {
        requireNonNull(task);
        toAdd = task;
        this.personEmailAddress = personEmailAddress;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTask(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        if (personEmailAddress != null) {
            try {
                Person person = model.getPersonByEmail(personEmailAddress);
                toAdd.setPerson(person);
                person.addTask(toAdd);
            } catch (PersonNotFoundException e) {
                throw new CommandException(MESSAGE_NO_PERSON_WITH_EMAIL);
            }
        }

        model.addTask(toAdd);
        model.update();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddTaskCommand)) {
            return false;
        }

        AddTaskCommand otherCommand = (AddTaskCommand) other;

        if ((personEmailAddress == null && otherCommand.personEmailAddress != null)
                || (personEmailAddress != null && otherCommand.personEmailAddress == null)) {
            return false;
        }

        return toAdd.equals(otherCommand.toAdd)
                && (personEmailAddress == null
                || personEmailAddress.equals(otherCommand.personEmailAddress));
    }
}

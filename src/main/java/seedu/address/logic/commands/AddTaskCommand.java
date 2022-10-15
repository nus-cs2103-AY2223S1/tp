package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.task.Task;
import seedu.address.model.Model;
import seedu.address.model.person.Person;



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
            + "[" + PREFIX_DEADLINE + "DEADLINE] "
            + "[" + PREFIX_PERSON + "PERSON INDEX (must be a positive integer)]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Create Initial UIUX Design "
            + PREFIX_DESCRIPTION + "Use FIGMA to create initial UIUX Design "
            + PREFIX_PRIORITY + "medium "
            + PREFIX_CATEGORY + "uiux "
            + PREFIX_DEADLINE + "2022-01-01 "
            + PREFIX_PERSON + "1 ";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the address book";

    private final Task toAdd;
    private final Index personIndex;

    /**
     * Creates an AddTaskCommand to add the specified {@code Task}
     */
    public AddTaskCommand(Task task, Index personIndex) {
        requireNonNull(task);
        toAdd = task;
        this.personIndex = personIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (personIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person person = lastShownList.get(personIndex.getZeroBased());
        toAdd.assignPerson(person);

        if (model.hasTask(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTaskCommand // instanceof handles nulls
                && toAdd.equals(((AddTaskCommand) other).toAdd));
    }
}

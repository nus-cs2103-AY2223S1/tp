//package seedu.address.logic.commands;
//
//
//import static java.util.Objects.requireNonNull;
//import static seedu.address.logic.parser.CliSyntax.*;
//
//import seedu.address.logic.commands.exceptions.CommandException;
//import seedu.address.model.Model;
//import seedu.address.model.person.Person;
//import seedu.address.model.task.Task;
//
///**
// * Adds a person to the address book.
// */
//public class AddTaskCommand extends Command {
//
//    public static final String COMMAND_WORD = "addtask";
//
//    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to MODPRO. "
//            + "Parameters: "
//            + PREFIX_NAME + "NAME "
//            + PREFIX_MOD_CODE + "MODCODE ";
//
//    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
//    public static final String MESSAGE_DUPLICATE_PERSON = "This task already exists in MODPRO";
//
//    private final Task toAdd;
//
//    /**
//     * Creates an AddCommand to add the specified {@code Person}
//     */
//
//    public AddTaskCommand(Task t) {
//        requireNonNull(t);
//        toAdd = t;
//    }
//
//    @Override
//    public CommandResult execute(Model model) throws CommandException {
//        requireNonNull(model);
//
//        if (model.hasTask(toAdd)) {
//            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
//        }
//
//        model.addTask(toAdd);
//        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        return other == this // short circuit if same object
//                || (other instanceof AddTaskCommand // instanceof handles nulls
//                && toAdd.equals(((AddTaskCommand) other).toAdd));
//
//    }
//}
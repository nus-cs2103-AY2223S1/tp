package taskbook.logic.commands.categoryless;

import static taskbook.logic.parser.CliSyntax.PREFIX_HELP_COMMAND;

import java.util.Arrays;
import java.util.stream.Collectors;

import taskbook.logic.commands.Command;
import taskbook.logic.commands.CommandResult;
import taskbook.logic.commands.contacts.ContactAddCommand;
import taskbook.logic.commands.contacts.ContactDeleteCommand;
import taskbook.logic.commands.contacts.ContactEditCommand;
import taskbook.logic.commands.contacts.ContactFindCommand;
import taskbook.logic.commands.contacts.ContactListCommand;
import taskbook.logic.commands.contacts.ContactSortCommand;
import taskbook.logic.commands.tasks.TaskDeadlineCommand;
import taskbook.logic.commands.tasks.TaskDeleteCommand;
import taskbook.logic.commands.tasks.TaskEditCommand;
import taskbook.logic.commands.tasks.TaskEventCommand;
import taskbook.logic.commands.tasks.TaskFindCommand;
import taskbook.logic.commands.tasks.TaskListCommand;
import taskbook.logic.commands.tasks.TaskMarkCommand;
import taskbook.logic.commands.tasks.TaskSortCommand;
import taskbook.logic.commands.tasks.TaskTodoCommand;
import taskbook.logic.commands.tasks.TaskUnmarkCommand;
import taskbook.logic.parser.contacts.ContactCategoryParser;
import taskbook.logic.parser.tasks.TaskCategoryParser;
import taskbook.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Parameters: [" + PREFIX_HELP_COMMAND + "COMMAND]\n"
            + "Example 1: " + COMMAND_WORD + "\n"
            + "Example 2: " + COMMAND_WORD + " " + PREFIX_HELP_COMMAND + CommandWord.CONTACT_DELETE;
    public static final String COMMANDS =
        Arrays.stream(CommandWord.values())
        .map(Enum::toString)
        .map(s -> "\n  * " + s) // prefix before each command
        .collect(Collectors.joining());
    public static final String USER_GUIDE_LINK = "User Guide Link: "
            + "https://ay2223s1-cs2103t-t13-4.github.io/tp/UserGuide.html\n";
    public static final String MESSAGE_GENERAL_USAGE = "Use "
        + COMMAND_WORD + " " + PREFIX_HELP_COMMAND + "COMMAND to find out more about a particular command.\n"
        + "Available commands: " + COMMANDS;

    private final CommandWord commandWord;

    /**
     * Creates a HelpCommand with the specified parameter.
     */
    public HelpCommand(CommandWord commandWord) {
        this.commandWord = commandWord;
    }

    @Override
    public CommandResult execute(Model model) {
        if (commandWord == null) {
            return new CommandResult(USER_GUIDE_LINK + MESSAGE_GENERAL_USAGE);
        }

        return new CommandResult(getHelp(commandWord));
    }

    private String getHelp(CommandWord commandWord) {
        switch (commandWord) {
        case HELP:
            return MESSAGE_USAGE;
        case CONTACT_LIST:
            return ContactListCommand.MESSAGE_USAGE;
        case TASK_LIST:
            return TaskListCommand.MESSAGE_USAGE;
        case CONTACT_ADD:
            return ContactAddCommand.MESSAGE_USAGE;
        case TASK_TODO:
            return TaskTodoCommand.MESSAGE_USAGE;
        case TASK_DEADLINE:
            return TaskDeadlineCommand.MESSAGE_USAGE;
        case TASK_EVENT:
            return TaskEventCommand.MESSAGE_USAGE;
        case CONTACT_EDIT:
            return ContactEditCommand.MESSAGE_USAGE;
        case TASK_EDIT:
            return TaskEditCommand.MESSAGE_USAGE;
        case CONTACT_DELETE:
            return ContactDeleteCommand.MESSAGE_USAGE;
        case TASK_DELETE:
            return TaskDeleteCommand.MESSAGE_USAGE;
        case CONTACT_FIND:
            return ContactFindCommand.MESSAGE_USAGE;
        case TASK_FIND:
            return TaskFindCommand.MESSAGE_USAGE;
        case CONTACT_SORT:
            return ContactSortCommand.MESSAGE_USAGE;
        case TASK_SORT:
            return TaskSortCommand.MESSAGE_USAGE;
        case TASK_MARK:
            return TaskMarkCommand.MESSAGE_USAGE;
        case TASK_UNMARK:
            return TaskUnmarkCommand.MESSAGE_USAGE;
        case UNDO:
            return UndoCommand.MESSAGE_USAGE;
        case REDO:
            return RedoCommand.MESSAGE_USAGE;
        case EXIT:
            return ExitCommand.MESSAGE_USAGE;
        default:
            return MESSAGE_USAGE;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (!(other instanceof HelpCommand)) {
            return false;
        }

        HelpCommand otherCommand = (HelpCommand) other;
        if (commandWord == null) {
            return otherCommand.commandWord == null;
        }
        return commandWord.equals(otherCommand.commandWord);
    }

    /**
     * Represents the different types of command words that TaskBook supports.
     */
    public enum CommandWord {
        HELP(COMMAND_WORD),
        CONTACT_LIST(ContactCategoryParser.CATEGORY_WORD, ContactListCommand.COMMAND_WORD),
        TASK_LIST(TaskCategoryParser.CATEGORY_WORD, TaskListCommand.COMMAND_WORD),
        CONTACT_ADD(ContactCategoryParser.CATEGORY_WORD, ContactAddCommand.COMMAND_WORD),
        TASK_TODO(TaskCategoryParser.CATEGORY_WORD, TaskTodoCommand.COMMAND_WORD),
        TASK_DEADLINE(TaskCategoryParser.CATEGORY_WORD, TaskDeadlineCommand.COMMAND_WORD),
        TASK_EVENT(TaskCategoryParser.CATEGORY_WORD, TaskEventCommand.COMMAND_WORD),
        CONTACT_EDIT(ContactCategoryParser.CATEGORY_WORD, ContactEditCommand.COMMAND_WORD),
        TASK_EDIT(TaskCategoryParser.CATEGORY_WORD, TaskEditCommand.COMMAND_WORD),
        CONTACT_DELETE(ContactCategoryParser.CATEGORY_WORD, ContactDeleteCommand.COMMAND_WORD),
        TASK_DELETE(TaskCategoryParser.CATEGORY_WORD, TaskDeleteCommand.COMMAND_WORD),
        CONTACT_FIND(ContactCategoryParser.CATEGORY_WORD, ContactFindCommand.COMMAND_WORD),
        TASK_FIND(TaskCategoryParser.CATEGORY_WORD, TaskFindCommand.COMMAND_WORD),
        CONTACT_SORT(ContactCategoryParser.CATEGORY_WORD, ContactSortCommand.COMMAND_WORD),
        TASK_SORT(TaskCategoryParser.CATEGORY_WORD, TaskSortCommand.COMMAND_WORD),
        TASK_MARK(TaskCategoryParser.CATEGORY_WORD, TaskMarkCommand.COMMAND_WORD),
        TASK_UNMARK(TaskCategoryParser.CATEGORY_WORD, TaskUnmarkCommand.COMMAND_WORD),
        UNDO(UndoCommand.COMMAND_WORD),
        REDO(RedoCommand.COMMAND_WORD),
        EXIT(ExitCommand.COMMAND_WORD);

        private final String name;

        CommandWord(String category, String command) {
            name = category + " " + command;
        }

        CommandWord(String command) {
            name = command;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}

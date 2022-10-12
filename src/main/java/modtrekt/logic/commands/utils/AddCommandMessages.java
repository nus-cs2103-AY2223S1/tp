package modtrekt.logic.commands.utils;

import modtrekt.logic.commands.AddCommand;
import modtrekt.logic.commands.AddDeadlineCommand;
import modtrekt.logic.commands.AddTaskCommand;

/**
 * Container for user visible messages related to add command.
 */
public class AddCommandMessages {
    public static final String MESSAGE_ADD_COMMAND_PREFIXES = "please suffix the add command with a -t "
            + "for tasks and -m for modules";
    public static final String COMBINED_TASK_DEADLINE_USAGE = AddTaskCommand.MESSAGE_USAGE + "\n"
            + AddDeadlineCommand.MESSAGE_USAGE;
    public static final String COMBINED_TASK_DEADLINE_MODULE_USAGE = AddTaskCommand.MESSAGE_USAGE + "\n"
            + AddDeadlineCommand.MESSAGE_USAGE + "\n" + AddCommand.MESSAGE_USAGE;
}

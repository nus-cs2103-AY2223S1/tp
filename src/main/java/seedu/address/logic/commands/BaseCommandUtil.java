package seedu.address.logic.commands;

/**
 * Contains base command words for all commands that are shared among some commands and generate approperiate error
 * messages
 */
public class BaseCommandUtil {

    /* Base Commands */
    public static final String ADD_COMMAND = "add";
    public static final String DELETE_COMMAND = "delete";
    public static final String EDIT_COMMAND = "edit";
    public static final String FIND_COMMAND = "find";
    public static final String LIST_COMMAND = "list";
    public static final String SORT_COMMAND = "sort";

    /* Error Messages */
    public static final String MESSAGE_BASE_COMMAND =
            "Please use %1$s -p or %1$s -i to specify Person or Internship.";

    // addressBookParser already checks if commandWord is valid and is the only class calling this method
    public static String getErrorMessage(String commandWord) {
        return String.format(MESSAGE_BASE_COMMAND, commandWord);
    };
}

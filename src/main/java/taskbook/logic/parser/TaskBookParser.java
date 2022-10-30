package taskbook.logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import taskbook.commons.core.Messages;
import taskbook.logic.commands.Command;
import taskbook.logic.commands.categoryless.HelpCommand;
import taskbook.logic.parser.categoryless.CategorylessParser;
import taskbook.logic.parser.contacts.ContactCategoryParser;
import taskbook.logic.parser.exceptions.ParseException;
import taskbook.logic.parser.tasks.TaskCategoryParser;

/**
 * Parses user input into different categories of commands.
 */
public class TaskBookParser {

    /**
     * Used for joining the various category words into a regex-compatible format.
     * When updating the command categories, there is a need to update this too.
     */
    private static final String CATEGORIES = String.join("|",
        ContactCategoryParser.CATEGORY_WORD,
        ContactCategoryParser.CATEGORY_WORD_SHORTCUT,
        TaskCategoryParser.CATEGORY_WORD,
        TaskCategoryParser.CATEGORY_WORD_SHORTCUT
    );
    private static final String BASIC_COMMAND_REGEX =
        String.format("(?:(?<category>%s)\\s)?(?<commandWord>\\S+)(?<arguments>.*)", CATEGORIES);
    /**
     * Used for initial separation of commands into categories.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile(BASIC_COMMAND_REGEX);

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        String filteredInput = removeNonAscii(userInput);
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(filteredInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String category = matcher.group("category");
        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        if (category == null) {
            return CategorylessParser.parseCommand(commandWord, arguments);
        } else if (category.equals(ContactCategoryParser.CATEGORY_WORD)
                || category.equals(ContactCategoryParser.CATEGORY_WORD_SHORTCUT)) {
            return ContactCategoryParser.parseCommand(commandWord, arguments);
        } else if (category.equals(TaskCategoryParser.CATEGORY_WORD)
                || category.equals(TaskCategoryParser.CATEGORY_WORD_SHORTCUT)) {
            return TaskCategoryParser.parseCommand(commandWord, arguments);
        }

        throw new ParseException(Messages.MESSAGE_UNKNOWN_CATEGORY);
    }

    /**
     * Removes all non ascii characters from a string.
     */
    private String removeNonAscii(String string) {
        return string.replaceAll("\\P{InBasic_Latin}", "");
    }

}

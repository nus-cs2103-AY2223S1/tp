package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_CATEGORY;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.categoryless.CategorylessParser;
import seedu.address.logic.parser.contacts.ContactCategoryParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.tasks.TaskCategoryParser;

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
        TaskCategoryParser.CATEGORY_WORD
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
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String category = matcher.group("category");
        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        if (category == null) {
            return CategorylessParser.parseCommand(commandWord, arguments);
        } else if (category.equals(ContactCategoryParser.CATEGORY_WORD)) {
            return ContactCategoryParser.parseCommand(commandWord, arguments);
        } else if (category.equals(TaskCategoryParser.CATEGORY_WORD)) {
            return TaskCategoryParser.parseCommand(commandWord, arguments);
        }

        throw new ParseException(MESSAGE_UNKNOWN_CATEGORY);
    }

}

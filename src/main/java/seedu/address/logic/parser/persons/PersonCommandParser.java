package seedu.address.logic.parser.persons;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.ForEachCommand;
import seedu.address.logic.commands.SelectCommand;
import seedu.address.logic.commands.persons.AddPersonCommand;
import seedu.address.logic.commands.persons.PersonCommand;
import seedu.address.logic.parser.FindCommandParser;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;

/**
 * Parser for all Task commands
 */
public class PersonCommandParser implements Parser<Command> {
    private static final String MESSAGE_USAGE = PersonCommand.COMMAND_WORD + " [new|delete|select|find]";
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<subcommandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution. The input must be a valid subcommand for Task.
     * There should not be a TaskCommand prefix in the input.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parse(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("subcommandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        case AddPersonCommand.SUBCOMMAND_WORD:
            return new AddPersonCommandParser().parse(arguments);
        case DeleteCommand.SUBCOMMAND_WORD:
            return DeleteCommand
                .<Person>parser((m, i) -> m.getFromFilteredPerson(i), (m, item) -> m.deletePerson(item),
                    o -> o instanceof Person)
                .parse(arguments);
        case SelectCommand.SUBCOMMAND_WORD:
            return SelectCommand.parser((m, i) -> m.getFromFilteredPerson(i)).parse(arguments);
        case ForEachCommand.SUBCOMMAND_WORD:
            return ForEachCommand.parser(m -> m.getFilteredPersonList()).parse(arguments);
        case FindCommand.SUBCOMMAND_WORD:
            return new FindCommandParser<Person>((m, p) -> m.updateFilteredPersonList(p),
                m -> m.getFilteredPersonList().size()).parse(arguments);
        default:
            throw new ParseException(MESSAGE_USAGE);
        }
    }
}

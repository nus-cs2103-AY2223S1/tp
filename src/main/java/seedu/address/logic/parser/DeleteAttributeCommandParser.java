package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SLACK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMEZONE;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.DeleteAttributeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser Class for Delete Attribute Command.
 */
public class DeleteAttributeCommandParser implements Parser<DeleteAttributeCommand> {

    public static final String MESSAGE_INVALID_FORMAT = "Attribute to delete cannot be empty";

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteAttributeCommand
     * and returns an DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public DeleteAttributeCommand parse(String userInput) throws ParseException {
        assert userInput != null : "User input cannot be null";

        // Throw ParseException when empty String is provided
        if (userInput.equals("")) {
            throw new ParseException(MESSAGE_INVALID_FORMAT);
        }
        // Allows user to not specify '/' when deleting attribute
        userInput = userInput + "/";
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(userInput, PREFIX_EMAIL, PREFIX_PHONE, PREFIX_SLACK, PREFIX_TELEGRAM,
                                            PREFIX_ADDRESS, PREFIX_TAG, PREFIX_ROLE, PREFIX_GITHUB,
                                            PREFIX_TIMEZONE);

        List<Prefix> prefixesToDelete = findPrefixesToDelete(argMultimap, PREFIX_EMAIL,
                PREFIX_PHONE, PREFIX_SLACK, PREFIX_TELEGRAM,
                PREFIX_ADDRESS, PREFIX_TAG, PREFIX_ROLE, PREFIX_GITHUB,
                PREFIX_TIMEZONE);

        if (prefixesToDelete.size() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteAttributeCommand.MESSAGE_USAGE));
        }

        return new DeleteAttributeCommand(prefixesToDelete.get(0));

    }

    /**
     * Returns the prefix to be passed into DeleteAttributeCommand, i.e. the attribute to delete.
     * {@code ArgumentMultimap}.
     */
    private static List<Prefix> findPrefixesToDelete(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).filter(prefix ->
                argumentMultimap.containsPrefix(prefix)).collect(Collectors.toList());
    }

}

package seedu.address.logic.parser;

import seedu.address.logic.commands.DeleteAttributeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SLACK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMEZONE;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeleteAttributeCommandParser implements Parser<DeleteAttributeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SetCommand
     * and returns an SetCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public DeleteAttributeCommand parse(String userInput) throws ParseException {

        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(userInput, PREFIX_EMAIL, PREFIX_PHONE, PREFIX_SLACK, PREFIX_TELEGRAM,
                                            PREFIX_ADDRESS, PREFIX_NAME, PREFIX_TAG, PREFIX_ROLE,
                                            PREFIX_TIMEZONE);
        
        List<Prefix> prefixesToDelete = findPrefixesToDelete(argMultimap, PREFIX_EMAIL, 
                PREFIX_PHONE, PREFIX_SLACK, PREFIX_TELEGRAM,
                PREFIX_ADDRESS, PREFIX_NAME, PREFIX_TAG, PREFIX_ROLE,
                PREFIX_TIMEZONE);
        
        if (prefixesToDelete.size() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAttributeCommand.MESSAGE_USAGE));
        }

        return new DeleteAttributeCommand(prefixesToDelete.get(0));
            
    }

    /**
     * Returns true if one of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static List<Prefix> findPrefixesToDelete(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).filter(prefix -> argumentMultimap.hasGivenPrefix(prefix)).collect(Collectors.toList());
    }
    
}

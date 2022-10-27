package swift.logic.parser;

import static java.util.Objects.requireNonNull;
import static swift.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static swift.logic.parser.CliSyntax.PREFIX_CONTACT;
import static swift.logic.parser.CliSyntax.PREFIX_TASK;

import java.util.stream.Stream;

import swift.commons.core.index.Index;
import swift.logic.commands.AssignCommand;
import swift.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AssignCommand object
 */
public class AssignCommandParser implements Parser<AssignCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * AssignCommand
     * and returns a AssignCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AssignCommand parse(String args) throws ParseException {
        requireNonNull(args);
        System.out.println(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_CONTACT, PREFIX_TASK);

        if (!arePrefixesPresent(argMultimap, PREFIX_CONTACT) || !arePrefixesPresent(argMultimap, PREFIX_TASK)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignCommand.MESSAGE_USAGE));
        }

        try {
            Index contactIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_CONTACT).get());
            Index taskIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TASK).get());

            return new AssignCommand(contactIndex, taskIndex);
        } catch (Exception e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignCommand.MESSAGE_USAGE), e);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

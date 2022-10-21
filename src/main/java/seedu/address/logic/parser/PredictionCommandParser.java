package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;

import java.util.stream.Stream;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.PredictionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.subject.Subject;

public class PredictionCommandParser implements Parser<PredictionCommand> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    @Override
    public PredictionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SUBJECT);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_SUBJECT)
            || !argMultimap.getPreamble().isEmpty()) {
          throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PredictionCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Subject subject = ParserUtil.parseSubject(argMultimap.getValue(PREFIX_SUBJECT).get());

        return new PredictionCommand(name, subject);
    }
}

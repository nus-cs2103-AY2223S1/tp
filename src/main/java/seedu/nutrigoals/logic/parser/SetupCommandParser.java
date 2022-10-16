package seedu.nutrigoals.logic.parser;

import static seedu.nutrigoals.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.nutrigoals.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.nutrigoals.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.nutrigoals.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.nutrigoals.logic.parser.CliSyntax.PREFIX_IDEAL_WEIGHT;
import static seedu.nutrigoals.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.util.stream.Stream;

import seedu.nutrigoals.logic.commands.SetupCommand;
import seedu.nutrigoals.logic.parser.exceptions.ParseException;
import seedu.nutrigoals.model.user.Age;
import seedu.nutrigoals.model.user.Gender;
import seedu.nutrigoals.model.user.Height;
import seedu.nutrigoals.model.user.User;
import seedu.nutrigoals.model.user.Weight;


/**
 * Parses input arguments and creates new SetupCommand object
 */
public class SetupCommandParser implements Parser<SetupCommand> {

    /**
     * Parsers the given arguments and returns a {@code SetupCommand} object for execution
     * @param args arguments specified by the user
     * @return a SetupCommand for execution
     * @throws ParseException if the user input does not conform to the given format
     */
    public SetupCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_GENDER,
                PREFIX_HEIGHT, PREFIX_WEIGHT, PREFIX_IDEAL_WEIGHT, PREFIX_AGE);

        if (!arePrefixesPresent(argumentMultimap, PREFIX_GENDER, PREFIX_HEIGHT, PREFIX_WEIGHT,
                PREFIX_IDEAL_WEIGHT, PREFIX_AGE)
                || !argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetupCommand.MESSAGE_USAGE));
        }
        Gender gender = ParserUtil.parseGender(argumentMultimap.getValue(PREFIX_GENDER).get());
        Weight weight = ParserUtil.parseWeight(argumentMultimap.getValue(PREFIX_WEIGHT).get());
        Weight idealWeight = ParserUtil.parseWeight(argumentMultimap.getValue(PREFIX_IDEAL_WEIGHT).get());
        Height height = ParserUtil.parseHeight(argumentMultimap.getValue(PREFIX_HEIGHT).get());
        Age age = ParserUtil.parseAge(argumentMultimap.getValue(PREFIX_AGE).get());

        User user = new User(gender, height, weight, idealWeight, age);
        return new SetupCommand(user);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

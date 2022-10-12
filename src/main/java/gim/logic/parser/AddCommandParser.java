package gim.logic.parser;

import static gim.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static gim.logic.parser.CliSyntax.PREFIX_DATE;
import static gim.logic.parser.CliSyntax.PREFIX_NAME;
import static gim.logic.parser.CliSyntax.PREFIX_REPS;
import static gim.logic.parser.CliSyntax.PREFIX_SETS;
import static gim.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.util.stream.Stream;

import gim.logic.commands.AddCommand;
import gim.logic.parser.exceptions.ParseException;
import gim.model.exercise.Exercise;
import gim.model.exercise.Name;
import gim.model.exercise.Reps;
import gim.model.exercise.Sets;
import gim.model.exercise.Weight;
import gim.model.date.Date;


/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_WEIGHT, PREFIX_SETS,
                PREFIX_REPS, PREFIX_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_REPS, PREFIX_WEIGHT, PREFIX_SETS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Weight weight = ParserUtil.parseWeight(argMultimap.getValue(PREFIX_WEIGHT).get());
        Sets sets = ParserUtil.parseSets(argMultimap.getValue(PREFIX_SETS).get());
        Reps reps = ParserUtil.parseRep(argMultimap.getValue(PREFIX_REPS).get());
        Date date;
        Exercise exercise;
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
            exercise = new Exercise(name, weight, sets, reps, date);
        } else {
            exercise = new Exercise(name, weight, sets, reps, new Date());
        }

        return new AddCommand(exercise);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

package gim.logic.parser;

import static gim.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static gim.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static gim.logic.parser.CliSyntax.PREFIX_NAME;
import static gim.logic.parser.CliSyntax.PREFIX_SETS;
import static gim.logic.parser.CliSyntax.PREFIX_TAG;
import static gim.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.util.Set;
import java.util.stream.Stream;

import gim.logic.commands.AddCommand;
import gim.logic.parser.exceptions.ParseException;
import gim.model.exercise.Address;
import gim.model.exercise.Exercise;
import gim.model.exercise.Name;
import gim.model.exercise.Sets;
import gim.model.exercise.Weight;
import gim.model.tag.Tag;





/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_WEIGHT, PREFIX_SETS, PREFIX_ADDRESS, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_WEIGHT, PREFIX_SETS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Weight weight = ParserUtil.parseWeight(argMultimap.getValue(PREFIX_WEIGHT).get());
        Sets sets = ParserUtil.parseSets(argMultimap.getValue(PREFIX_SETS).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Exercise exercise = new Exercise(name, weight, sets, address, tagList);

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

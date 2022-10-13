package foodwhere.logic.parser;

import static foodwhere.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Set;
import java.util.stream.Stream;

import foodwhere.commons.core.index.Index;
import foodwhere.logic.commands.RAddCommand;
import foodwhere.logic.parser.exceptions.ParseException;
import foodwhere.model.commons.Tag;
import foodwhere.model.review.Content;
import foodwhere.model.review.Date;
import foodwhere.model.review.Rating;

/**
 * Parses input arguments and creates a new RAddCommand object
 */
public class RAddCommandParser implements Parser<RAddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RAddCommand
     * and returns an RAddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RAddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        CliSyntax.PREFIX_STALL_INDEX,
                        CliSyntax.PREFIX_DATE,
                        CliSyntax.PREFIX_CONTENT,
                        CliSyntax.PREFIX_RATING,
                        CliSyntax.PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap,
                CliSyntax.PREFIX_STALL_INDEX,
                CliSyntax.PREFIX_DATE,
                CliSyntax.PREFIX_CONTENT,
                CliSyntax.PREFIX_RATING)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RAddCommand.MESSAGE_USAGE));
        }

        Index stallIndex;
        try {
            stallIndex = ParserUtil.parseIndex(argMultimap.getValue(CliSyntax.PREFIX_STALL_INDEX).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RAddCommand.MESSAGE_USAGE), pe);
        }


        Date date = ParserUtil.parseDate(argMultimap.getValue(CliSyntax.PREFIX_DATE).get());
        Content content = ParserUtil.parseContent(argMultimap.getValue(CliSyntax.PREFIX_CONTENT).get());
        Rating rating = ParserUtil.parseRating(Integer.valueOf(argMultimap.getValue(CliSyntax.PREFIX_RATING).get()));
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(CliSyntax.PREFIX_TAG));


        return new RAddCommand(stallIndex, date, content, rating, tagList);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

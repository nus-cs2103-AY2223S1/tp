package hobbylist.logic.parser;

import static hobbylist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static hobbylist.commons.core.Messages.MESSAGE_INVALID_RATING;
import static java.util.Objects.requireNonNull;

import java.util.Optional;

import hobbylist.commons.core.index.Index;
import hobbylist.logic.commands.RateCommand;
import hobbylist.logic.parser.exceptions.ParseException;
import hobbylist.model.activity.Review;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class RateCommandParser implements Parser<RateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RateCommand
     * and returns a RateCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_RATING, CliSyntax.PREFIX_REVIEW);

        int rating;
        if (argMultimap.getValue(CliSyntax.PREFIX_RATING).isPresent()) {
            try {
                rating = Integer.parseInt(argMultimap.getValue(CliSyntax.PREFIX_RATING).get());
            } catch (NumberFormatException e) {
                throw new ParseException(MESSAGE_INVALID_RATING + RateCommand.MESSAGE_USAGE);
            }
            if (rating < 1 || rating > 5) {
                throw new ParseException(MESSAGE_INVALID_RATING + RateCommand.MESSAGE_USAGE);
            }
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RateCommand.MESSAGE_USAGE));
        }

        Optional<Review> review;
        if (argMultimap.getValue(CliSyntax.PREFIX_REVIEW).isPresent()) {
            if (!argMultimap.getValue(CliSyntax.PREFIX_REVIEW).get().equals("")) {
                review = Optional.of(new Review(argMultimap.getValue(CliSyntax.PREFIX_REVIEW).get()));
            } else {
                review = Optional.empty();
            }
        } else {
            review = null;
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RateCommand.MESSAGE_USAGE), pe);
        }

        return new RateCommand(index, rating, review);
    }

}

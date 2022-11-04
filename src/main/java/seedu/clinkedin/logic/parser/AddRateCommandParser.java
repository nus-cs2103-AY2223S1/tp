package seedu.clinkedin.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.clinkedin.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_RATING;

import seedu.clinkedin.commons.core.index.Index;
import seedu.clinkedin.commons.exceptions.IllegalValueException;
import seedu.clinkedin.logic.commands.AddRateCommand;
import seedu.clinkedin.logic.parser.exceptions.ParseException;
import seedu.clinkedin.model.person.Rating;

/**
 * Parses input arguments and creates a new RateCommand object
 */
public class AddRateCommandParser implements Parser<AddRateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * RateCommand
     */
    public AddRateCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_RATING);
        Index index;
        Rating rating;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddRateCommand.MESSAGE_USAGE), ive);
        }

        rating = new Rating(argMultimap.getValue(PREFIX_RATING).orElse("0"));


        return new AddRateCommand(index, rating);
    }
}

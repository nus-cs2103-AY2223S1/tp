package seedu.address.logic.parser.iteration;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITERATION_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITERATION_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITERATION_FEEDBACK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITERATION_IMAGEPATH;

import java.util.stream.Stream;

import seedu.address.logic.commands.iteration.AddIterationCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.iteration.Date;
import seedu.address.model.iteration.Feedback;
import seedu.address.model.iteration.ImagePath;
import seedu.address.model.iteration.Iteration;
import seedu.address.model.iteration.IterationDescription;

/**
 * Parses input arguments and creates a new AddIterationCommand object.
 */
public class AddIterationCommandParser implements Parser<AddIterationCommand> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddIterationCommand
     * and returns an AddIteration object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public AddIterationCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ITERATION_DATE,
                        PREFIX_ITERATION_DESCRIPTION, PREFIX_ITERATION_IMAGEPATH, PREFIX_ITERATION_FEEDBACK);

        if (!arePrefixesPresent(argMultimap, PREFIX_ITERATION_DATE,
                PREFIX_ITERATION_DESCRIPTION, PREFIX_ITERATION_IMAGEPATH, PREFIX_ITERATION_FEEDBACK)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddIterationCommand.MESSAGE_USAGE));
        }

        Date date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_ITERATION_DATE).get());
        IterationDescription description =
                ParserUtil.parseIterationDescription(argMultimap.getValue(PREFIX_ITERATION_DESCRIPTION).get());
        ImagePath imagePath =
                ParserUtil.parseImagePath(argMultimap.getValue(PREFIX_ITERATION_IMAGEPATH).get());
        Feedback feedback = ParserUtil.parseFeedback(argMultimap.getValue(PREFIX_ITERATION_FEEDBACK).get());

        Iteration iteration = new Iteration(date, description, imagePath, feedback);

        return new AddIterationCommand(iteration);
    }
}

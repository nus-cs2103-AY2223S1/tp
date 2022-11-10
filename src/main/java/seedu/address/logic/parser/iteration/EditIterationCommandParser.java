package seedu.address.logic.parser.iteration;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITERATION_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITERATION_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITERATION_FEEDBACK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITERATION_IMAGEPATH;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.iteration.EditIterationCommand;
import seedu.address.logic.commands.iteration.EditIterationCommand.EditIterationDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditIterationCommand object.
 */
public class EditIterationCommandParser implements Parser<EditIterationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditIterationCommand
     * and returns an EditIterationCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public EditIterationCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ITERATION_DATE,
                        PREFIX_ITERATION_DESCRIPTION, PREFIX_ITERATION_IMAGEPATH, PREFIX_ITERATION_FEEDBACK);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditIterationCommand.MESSAGE_USAGE), e);
        }

        EditIterationDescriptor editIterationDescriptor = new EditIterationDescriptor();

        if (argMultimap.getValue(PREFIX_ITERATION_DATE).isPresent()) {
            editIterationDescriptor.setDate(ParserUtil.parseDate(argMultimap.getValue(PREFIX_ITERATION_DATE).get()));
        }

        if (argMultimap.getValue(PREFIX_ITERATION_DESCRIPTION).isPresent()) {
            editIterationDescriptor.setDescription(
                    ParserUtil.parseIterationDescription(argMultimap.getValue(PREFIX_ITERATION_DESCRIPTION).get())
            );
        }

        if (argMultimap.getValue(PREFIX_ITERATION_IMAGEPATH).isPresent()) {
            editIterationDescriptor.setImagePath(
                    ParserUtil.parseImagePath(argMultimap.getValue(PREFIX_ITERATION_IMAGEPATH).get())
            );
        }

        if (argMultimap.getValue(PREFIX_ITERATION_FEEDBACK).isPresent()) {
            editIterationDescriptor.setFeedback(
                    ParserUtil.parseFeedback(argMultimap.getValue(PREFIX_ITERATION_FEEDBACK).get())
            );
        }

        if (!editIterationDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditIterationCommand.MESSAGE_NOT_EDITED);
        }

        return new EditIterationCommand(index, editIterationDescriptor);
    }
}

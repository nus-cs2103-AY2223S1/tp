package friday.logic.parser;

import static friday.logic.parser.CliSyntax.PREFIX_FINALS;
import static friday.logic.parser.CliSyntax.PREFIX_MIDTERM;
import static friday.logic.parser.CliSyntax.PREFIX_PRACTICAL;
import static friday.logic.parser.CliSyntax.PREFIX_RA1;
import static friday.logic.parser.CliSyntax.PREFIX_RA2;
import static java.util.Objects.requireNonNull;

import friday.commons.core.Messages;
import friday.commons.core.index.Index;
import friday.commons.exceptions.IllegalValueException;
import friday.logic.commands.EditCommand;
import friday.logic.commands.GradeCommand;
import friday.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new GradeCommand object
 */
public class GradeCommandParser implements Parser<GradeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code GradeCommand}
     * and returns a {@code GradeCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GradeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_RA1, PREFIX_RA2, PREFIX_MIDTERM,
                PREFIX_FINALS, PREFIX_PRACTICAL);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    GradeCommand.MESSAGE_USAGE), ive);
        }

        GradeCommand.EditGradeDescriptor editGradeDescriptor = new GradeCommand.EditGradeDescriptor();
        if (argMultimap.getValue(PREFIX_RA1).isPresent()) {
            editGradeDescriptor.setRa1(ParserUtil.parseGrade("RA1", argMultimap.getValue(PREFIX_RA1).get()));
        }
        if (argMultimap.getValue(PREFIX_RA2).isPresent()) {
            editGradeDescriptor.setRa2(ParserUtil.parseGrade("RA2", argMultimap.getValue(PREFIX_RA2).get()));
        }
        if (argMultimap.getValue(PREFIX_PRACTICAL).isPresent()) {
            editGradeDescriptor.setPa(ParserUtil.parseGrade("Practical",
                    argMultimap.getValue(PREFIX_PRACTICAL).get()));
        }
        if (argMultimap.getValue(PREFIX_MIDTERM).isPresent()) {
            editGradeDescriptor.setMt(ParserUtil.parseGrade("Midterm",
                    argMultimap.getValue(PREFIX_MIDTERM).get()));
        }
        if (argMultimap.getValue(PREFIX_FINALS).isPresent()) {
            editGradeDescriptor.setFt(ParserUtil.parseGrade("Finals",
                    argMultimap.getValue(PREFIX_FINALS).get()));
        }

        if (!editGradeDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new GradeCommand(index, editGradeDescriptor);
    }
}

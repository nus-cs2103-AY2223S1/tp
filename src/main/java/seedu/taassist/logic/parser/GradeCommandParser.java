package seedu.taassist.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_SESSION;

import java.util.List;

import seedu.taassist.commons.core.index.Index;
import seedu.taassist.logic.commands.GradeCommand;
import seedu.taassist.logic.parser.exceptions.ParseException;
import seedu.taassist.model.session.Session;

/**
 * Parses input arguments and creates a new GradeCommand object.
 */
public class GradeCommandParser implements Parser<GradeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GradeCommand
     * and returns a GradeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GradeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_GRADE, PREFIX_SESSION);

        if (!argMultimap.containsPrefixes(PREFIX_GRADE, PREFIX_SESSION) || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GradeCommand.COMMAND_WORD,
                    GradeCommand.MESSAGE_USAGE));
        }

        List<Index> indices;
        Session session;
        Double grade;

        try {
            indices = ParserUtil.parseIndices(argMultimap.getPreamble());
            session = ParserUtil.parseSession(argMultimap.getValue(PREFIX_SESSION).get());
            grade = ParserUtil.parseGrade(argMultimap.getValue(PREFIX_GRADE).get());
        } catch (ParseException pe) {
            throw new ParseException(pe.getMessage());
        }
        return new GradeCommand(indices, session, grade);
    }
}

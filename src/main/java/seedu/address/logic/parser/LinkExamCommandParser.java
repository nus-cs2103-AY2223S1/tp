package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.LinkExamCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * LinkExamCommandParser parses the arguments provided by the user
 * and creates a new LinkExamCommand object.
 */
public class LinkExamCommandParser implements Parser<LinkExamCommand> {

    public static final String TASK_INDEX_INVALID = "The index for the task should be "
            + "an unsigned positive integer greater than 0 and lesser than 2147483648.";
    public static final String EXAM_INDEX_INVALID = "The index for the exam should be "
            + "an unsigned positive integer greater than 0 and lesser than 2147483648.";

    @Override
    public LinkExamCommand parse(String userArgs) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userArgs,
                PREFIX_EXAM_INDEX, PREFIX_TASK_INDEX);
        Index examIndex;
        Index taskIndex;
        if (!areAllPrefixesPresent(argumentMultimap, PREFIX_TASK_INDEX, PREFIX_EXAM_INDEX)
                || !argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    LinkExamCommand.MESSAGE_USAGE));
        }
        try {
            examIndex = ParserUtil.parseIndex(argumentMultimap.getValue(PREFIX_EXAM_INDEX).get());
        } catch (ParseException pe) {
            throw new ParseException(EXAM_INDEX_INVALID);
        }
        try {
            taskIndex = ParserUtil.parseIndex(argumentMultimap.getValue(PREFIX_TASK_INDEX).get());
        } catch (ParseException pe) {
            throw new ParseException(TASK_INDEX_INVALID);
        }
        return new LinkExamCommand(examIndex, taskIndex);
    }

    private static boolean areAllPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

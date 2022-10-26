package seedu.address.logic.parser;


import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddExamCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.exam.Exam;
import seedu.address.model.exam.ExamDate;
import seedu.address.model.exam.ExamDescription;
import seedu.address.model.module.Module;

/**
 * Parses arguments to create a new AddExamCommand Object.
 */
public class AddExamCommandParser implements Parser<AddExamCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddExamCommand
     * and returns an AddExamCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddExamCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE, PREFIX_EXAM_DESCRIPTION, PREFIX_EXAM_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_MODULE, PREFIX_EXAM_DESCRIPTION, PREFIX_EXAM_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddExamCommand.MESSAGE_USAGE));
        }

        Module module = new Module(ParserUtil.parseModuleCode(argMultimap.getValue(PREFIX_MODULE).get()));
        ExamDescription description = ParserUtil.parseExamDescription(
                argMultimap.getValue(PREFIX_EXAM_DESCRIPTION).get());
        ExamDate examDate = ParserUtil.parseExamDate(argMultimap.getValue(PREFIX_EXAM_DATE).get());
        Exam exam = new Exam(module, description, examDate);

        return new AddExamCommand(exam);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}


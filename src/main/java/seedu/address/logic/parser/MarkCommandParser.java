package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Attendance;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS;

public class MarkCommandParser implements Parser<MarkCommand> {

    public MarkCommand parse(String args) throws ParseException {

        ParseException parseException = new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MarkCommand.MESSAGE_USAGE));
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_CLASS);

        Index index;
        String[] preamble = argMultimap.getPreamble().split("\\s+");
        if (preamble.length != 2) throw parseException;

        try {
            index = ParserUtil.parseIndex(preamble[0]);
        } catch (IllegalValueException ive) {
            throw parseException;
        }

        boolean attended = parseOption(preamble[1]);
        String className = ParserUtil.parseClassName(argMultimap.getValue(PREFIX_CLASS).orElse(""));
        Attendance attendance = new Attendance(className, attended);

        return new MarkCommand(index, attendance);
    }

    private boolean parseOption(String option) throws ParseException {
        if (option.equals("present")) {
            return true;
        } else if (option.equals("absent")) {
            return false;
        } else throw new ParseException("Option must either be 'present' or 'absent'!");
    }
}

package seedu.studmap.logic.parser;

import static seedu.studmap.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.studmap.logic.parser.ParserUtil.separatePreamble;

import seedu.studmap.logic.commands.EditStudentCommand;
import seedu.studmap.logic.commands.commons.StudentEditor;
import seedu.studmap.logic.parser.exceptions.ParseException;

/**
 * An in-between abstract class to flatten the class declaration.
 *
 * @param <S> StudentEditor that is used by the EditStudentCommand which this parser produces.
 */
public abstract class EditStudentCommandParser<S extends StudentEditor>
        implements IndexCommandParser<EditStudentCommand<S>> {

    @Override
    public EditStudentCommand<S> parse(String args) throws ParseException {
        EditStudentCommand<S> command = IndexCommandParser.super.parse(args);
        if (!command.hasEdits()) {
            throw new ParseException(command.getNoEditMessage());
        }
        return command;
    }

    @Override
    public void validatePreamble(String preamble) throws ParseException {
        if (separatePreamble(preamble).length != 1) {
            throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }
}

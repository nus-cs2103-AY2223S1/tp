package seedu.studmap.logic.parser;

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
}

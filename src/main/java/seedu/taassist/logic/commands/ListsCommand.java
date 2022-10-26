package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.core.Messages.MESSAGE_NOT_IN_FOCUS_MODE;

import java.util.List;
import java.util.StringJoiner;

import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.Model;
import seedu.taassist.model.session.Session;

/**
 * Lists all sessions in the focused class to the user.
 */
public class ListsCommand extends Command {

    public static final String COMMAND_WORD = "lists";

    public static final String MESSAGE_LIST_HEADER = "Recorded sessions for class $1%s:";

    @Override
    // TODO: Rudimentary implementation. Needs to be combined with UI.
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.isInFocusMode()) {
            throw new CommandException(String.format(MESSAGE_NOT_IN_FOCUS_MODE, COMMAND_WORD));
        }

        String focusedClassName = model.getFocusedClass().getClassName();
        List<Session> sessions = model.getFocusedClass().getSessions();
        StringJoiner sessionsString = new StringJoiner("\n");
        sessionsString.add(String.format(MESSAGE_LIST_HEADER, focusedClassName));
        for (int i = 0; i < sessions.size(); i++) {
            sessionsString.add((i + 1) + ". " + sessions.get(i));
        }
        return new CommandResult(sessionsString.toString());
    }
}


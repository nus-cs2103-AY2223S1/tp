package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.core.Messages.MESSAGE_NOT_IN_FOCUS_MODE;

import java.util.List;
import java.util.StringJoiner;

import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.session.Session;

/**
 * Lists all sessions in the focused class to the user.
 */
public class ListsCommand extends Command {

    public static final String COMMAND_WORD = "lists";

    public static final String MESSAGE_SUCCESS = "Recorded sessions for class [ %1s ]:\n%2s";
    public static final String MESSAGE_EMPTY_SESSION_LIST = "No sessions have been added for this class. "
            + "Add sessions with [ " + AddsCommand.COMMAND_WORD + " ] command.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.isInFocusMode()) {
            throw new CommandException(String.format(MESSAGE_NOT_IN_FOCUS_MODE, COMMAND_WORD));
        }

        List<Session> sessions = model.getFocusedClass().getSessions();
        ModuleClass focusedClass = model.getFocusedClass();

        return new CommandResult(getSuccessMessage(sessions, focusedClass));
    }

    public static String getSuccessMessage(List<Session> sessions, ModuleClass focusedClass) {
        if (sessions.isEmpty()) {
            return MESSAGE_EMPTY_SESSION_LIST;
        }
        StringJoiner sessionsString = new StringJoiner("\n");
        for (int i = 0; i < sessions.size(); i++) {
            Session session = sessions.get(i);
            String sessionDescription = String.format("[ %1s ] on %2s", session.getSessionName(), session.getDate());
            sessionsString.add((i + 1) + ". " + sessionDescription);
        }
        return String.format(MESSAGE_SUCCESS, focusedClass, sessionsString);
    }
}


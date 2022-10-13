package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.core.Messages.MESSAGE_NOT_IN_FOCUS_MODE;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_SESSION;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.session.Session;

/**
 * Deletes Session for a class.
 */
public class DeletesCommand extends Command {

    public static final String COMMAND_WORD = "deletes";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete sessions for a class. "
            + "Paramaters: "
            + PREFIX_SESSION + "SESSION_NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SESSION + "Lab1";

    public static final String MESSAGE_SUCCESS = "Sessions deleted: %s";
    public static final String MESSAGE_SESSION_DOES_NOT_EXIST = "Session %s does not exist!";

    private final Set<Session> sessions;

    /**
     * Creates a DeletesCommand to delete the specified {@code Session}s.
     */
    public DeletesCommand(Set<Session> sessions) {
        requireNonNull(sessions);
        this.sessions = sessions;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.isInFocusMode()) {
            throw new CommandException(String.format(MESSAGE_NOT_IN_FOCUS_MODE, COMMAND_WORD));
        }

        ModuleClass oldClass = model.getFocusedClass();
        for (Session session : sessions) {
            if (!oldClass.hasSession(session)) {
                throw new CommandException(String.format(MESSAGE_SESSION_DOES_NOT_EXIST, session.getSessionName()));
            }
        }

        List<Session> newSessions = new ArrayList<>(oldClass.getSessions());
        newSessions.removeAll(sessions);
        ModuleClass newClass = new ModuleClass(oldClass.getClassName(), newSessions);

        model.setModuleClass(oldClass, newClass);

        return new CommandResult(String.format(MESSAGE_SUCCESS, sessions));
    }
}

package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.core.Messages.MESSAGE_NOT_IN_FOCUS_MODE;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_SESSION;

import java.util.ArrayList;
import java.util.List;

import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.session.Session;

/**
 * Creates Session for a class.
 */
public class SessionCommand extends Command {

    public static final String COMMAND_WORD = "session";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a new session for a class. "
            + "Paramaters: "
            + PREFIX_SESSION + "SESSION_NAME "
            + "[" + PREFIX_DATE + "DATE]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SESSION + "Lab1 "
            + PREFIX_DATE + "2022-01-01";

    public static final String MESSAGE_SUCCESS = "New session added: %s";
    public static final String MESSAGE_SESSION_EXISTS = "Session %s already exists!";

    private final Session session;

    /**
     * Creates a SessionCommand to create the specified {@code Session}.
     */
    public SessionCommand(Session session) {
        requireNonNull(session);
        this.session = session;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.isInFocusMode()) {
            throw new CommandException(String.format(MESSAGE_NOT_IN_FOCUS_MODE, COMMAND_WORD));
        }

        ModuleClass oldClass = model.getFocusedClass();
        if (oldClass.hasSession(session)) {
            throw new CommandException(String.format(MESSAGE_SESSION_EXISTS, session.getSessionName()));
        }

        List<Session> newSessions = new ArrayList<>(oldClass.getSessions());
        newSessions.add(session);
        ModuleClass newClass = new ModuleClass(oldClass.getClassName(), newSessions);

        model.setModuleClass(oldClass, newClass);

        return new CommandResult(String.format(MESSAGE_SUCCESS, session));
    }
}

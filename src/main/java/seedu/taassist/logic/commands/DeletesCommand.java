package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.taassist.commons.util.StringUtil.commaSeparate;
import static seedu.taassist.logic.commands.CommandUtil.requireFocusMode;
import static seedu.taassist.logic.commands.CommandUtil.requireSessionsExists;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_SESSION;

import java.util.Set;

import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.session.Session;

/**
 * Deletes Sessions for a class.
 */
public class DeletesCommand extends Command {

    public static final String COMMAND_WORD = "deletes";

    public static final String MESSAGE_USAGE = "> Deletes sessions for a class.\n"
            + "Parameters: "
            + PREFIX_SESSION + "SESSION_NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SESSION + "Lab1";

    public static final String MESSAGE_SUCCESS = "Session(s) deleted: [ %s ]";

    private final Set<Session> sessions;

    /**
     * Creates a DeletesCommand to delete the specified {@code Session}s.
     */
    public DeletesCommand(Set<Session> sessions) {
        requireAllNonNull(sessions);
        this.sessions = sessions;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        requireFocusMode(model, COMMAND_WORD);
        ModuleClass focusedClass = model.getFocusedClass();
        requireSessionsExists(sessions, focusedClass);
        model.removeSessions(focusedClass, sessions);
        return new CommandResult(getCommandMessage(sessions));
    }

    public static String getCommandMessage(Set<Session> sessions) {
        requireAllNonNull(sessions);
        String sessionsString = commaSeparate(sessions, Session::getSessionName);
        return String.format(MESSAGE_SUCCESS, sessionsString);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletesCommand // instanceof handles nulls
                && sessions.equals(((DeletesCommand) other).sessions));
    }
}

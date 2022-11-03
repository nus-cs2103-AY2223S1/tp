package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.core.Messages.MESSAGE_NOT_IN_FOCUS_MODE;
import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.taassist.commons.util.StringUtil.commaSeparate;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_SESSION;

import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.session.Session;

/**
 * Creates Session for a class.
 */
public class AddsCommand extends Command {

    public static final String COMMAND_WORD = "adds";

    public static final String MESSAGE_USAGE = "> Creates a new session for a class.\n"
            + "Parameters: "
            + PREFIX_SESSION + "SESSION_NAME "
            + "[" + PREFIX_DATE + "DATE]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SESSION + "Lab1 "
            + PREFIX_DATE + "2022-01-01";

    public static final String MESSAGE_SUCCESS = "Added new sessions:\n%1$s";
    public static final String MESSAGE_DUPLICATE_SESSIONS = "Session(s) [ %s ] already exist(s)!";

    private final Set<Session> sessions;

    /**
     * Creates a AddsCommand to create the specified {@code Session}.
     */
    public AddsCommand(Set<Session> sessions) {
        requireNonNull(sessions);
        this.sessions = sessions;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Set<Session> duplicateSessions = new HashSet<>();
        Set<Session> newSessions = new HashSet<>();

        if (!model.isInFocusMode()) {
            throw new CommandException(String.format(MESSAGE_NOT_IN_FOCUS_MODE, COMMAND_WORD));
        }

        ModuleClass focusedClass = model.getFocusedClass();

        for (Session session: sessions) {
            if (focusedClass.hasSession(session)) {
                duplicateSessions.add(session);
            } else {
                newSessions.add(session);
            }
        }

        model.addSessions(focusedClass, newSessions);
        return new CommandResult(getCommandMessage(newSessions, duplicateSessions));
    }

    public static String getCommandMessage(Set<Session> newSessions, Set<Session> duplicateSessions) {
        StringBuilder outputString = new StringBuilder();
        if (!newSessions.isEmpty()) {
            outputString.append(getSessionsAddedMessage(newSessions)).append("\n");
        }

        if (!duplicateSessions.isEmpty()) {
            outputString.append(getDuplicateSessionsMessage(duplicateSessions)).append("\n");
        }

        // remove trailing newline character
        outputString.setLength(outputString.length() - 1);
        return outputString.toString();
    }
    private static String getSessionsAddedMessage(Set<Session> newSessions) {
        requireAllNonNull(newSessions);
        StringJoiner sessionsString = new StringJoiner("\n");
        int i = 1;
        for (Session session: newSessions) {
            String sessionDescription = String.format("[ %1s ] on %2s", session.getSessionName(), session.getDate());
            sessionsString.add((i) + ". " + sessionDescription);
            i++;
        }

        return String.format(MESSAGE_SUCCESS, sessionsString);
    }

    private static String getDuplicateSessionsMessage(Set<Session> duplicateSessions) {
        requireAllNonNull(duplicateSessions);
        String duplicateClassesStr = commaSeparate(duplicateSessions, Session::getSessionName);
        return String.format(MESSAGE_DUPLICATE_SESSIONS, duplicateClassesStr);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddsCommand // instanceof handles nulls
                && sessions.equals(((AddsCommand) other).sessions));
    }


}

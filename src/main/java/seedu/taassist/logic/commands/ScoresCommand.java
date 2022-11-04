package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.logic.commands.CommandUtil.requireFocusMode;
import static seedu.taassist.logic.commands.CommandUtil.requireSessionExists;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_SESSION;

import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.session.Session;

/**
 * Displays student's scores for a session within the focused class.
 */
public class ScoresCommand extends Command {

    public static final String COMMAND_WORD = "scores";

    public static final String MESSAGE_USAGE = "> Displays students' scores for a session within the focused class.\n"
            + "Parameters: "
            + PREFIX_SESSION + "SESSION_NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SESSION + "Lab1";

    public static final String MESSAGE_SUCCESS = "Listed scores of all students for session [ %s ].";

    private final Session session;

    /**
     * Constructs a {@code ScoresCommand} with the provided {@code Session}.
     *
     * @param session the provided {@code Session}.
     */
    public ScoresCommand(Session session) {
        requireNonNull(session);
        this.session = session;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        requireFocusMode(model, COMMAND_WORD);
        ModuleClass focusedClass = model.getFocusedClass();
        requireSessionExists(session, focusedClass);
        model.querySessionData(session);
        return new CommandResult(String.format(MESSAGE_SUCCESS, session.getSessionName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ScoresCommand // instanceof handles nulls
                && session.equals(((ScoresCommand) other).session));
    }
}

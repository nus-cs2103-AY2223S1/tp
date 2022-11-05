package tuthub.logic.commands;

import static java.util.Objects.requireNonNull;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import tuthub.commons.core.Messages;
import tuthub.commons.core.index.Index;
import tuthub.logic.commands.exceptions.CommandException;
import tuthub.model.Model;
import tuthub.model.tutor.Tutor;

/**
 * Opens the user's email client with the tutors to send to specified.
 */
public class MailCommand extends Command {
    public static final String COMMAND_WORD = "mail";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Launches the mail composing window with "
            + "the \"to\" specified for a tutor or all tutors displayed.\n"
            + "Parameters: "
            + "INDEX or \"all\" \n"
            + "Example: " + COMMAND_WORD + " "
            + "all";

    public static final String MESSAGE_MAIL_TUTOR_SUCCESS = "Launching mail composing window.";
    public static final String MESSAGE_MAIL_CLIENT_FAIL = "The user default mail client is not found "
            + "or fails to be launched";
    public static final String MESSAGE_INVALID_WORD = "Word %1$s is invalid.";
    public static final String MAILTO_PREFIX = "mailto:";
    public static final String ALL_WORD = "all";

    private final String target;
    private final Boolean isAll;
    private final Index targetIndex;

    /**
     * Creates a MailCommand to email all tutors.
     */
    public MailCommand(String target) {
        requireNonNull(target);
        this.target = target;
        this.isAll = true;
        this.targetIndex = Index.fromZeroBased(0);
    }

    /**
     * Creates a MailCommand to email the tutor at {@code targetIndex}.
     */
    public MailCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.target = "";
        this.isAll = false;
        this.targetIndex = targetIndex;
    }

    /**
     * Checks if the {@code target} is valid or not.
     * {@code target} is valid if and only if it is equal to "all"
     *
     * @throws CommandException If {@code target} is not valid.
     */
    public void checkValidTarget() throws CommandException {
        if (!target.equals(ALL_WORD)) {
            throw new CommandException(String.format(MESSAGE_INVALID_WORD, target));
        }
    }

    /**
     * Checks if the {@code targetIndex} is valid or not.
     * {@code targetIndex} is valid if and only if it is less than the list size.
     *
     * @param list The current list being displayed in Tuthub.
     * @throws CommandException If the {@code targetIndex} is not valid.
     */
    public void checkValidIndex(List<Tutor> list) throws CommandException {
        if (targetIndex.getZeroBased() >= list.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TUTOR_DISPLAYED_INDEX);
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Tutor> lastShownList = model.getFilteredTutorList();

        Desktop desktop = Desktop.getDesktop();
        String message = MAILTO_PREFIX;
        Tutor selectedTutor;
        URI uri;
        String email;

        if (isAll) {
            checkValidTarget();
            for (int i = 0; i < lastShownList.size() - 1; i++) {
                selectedTutor = lastShownList.get(i);
                email = selectedTutor.getEmail().toString();
                message += email + ",";
            }
            selectedTutor = lastShownList.get(lastShownList.size() - 1);
            email = selectedTutor.getEmail().toString();
            message += email;
            uri = URI.create(message);
        } else {
            checkValidIndex(lastShownList);
            selectedTutor = lastShownList.get(targetIndex.getZeroBased());
            email = selectedTutor.getEmail().toString();
            message += email;
            uri = URI.create(message);
        }

        try {
            desktop.mail(uri);
        } catch (IOException e) {
            throw new CommandException(MESSAGE_MAIL_CLIENT_FAIL);
        }

        return new CommandResult(MESSAGE_MAIL_TUTOR_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (isAll) {
            return other == this // short circuit if same object
                    || (other instanceof MailCommand // instanceof handles nulls
                    && target.equals(((MailCommand) other).target)
                    && isAll.equals(((MailCommand) other).isAll));
        } else {
            return other == this // short circuit if same object
                    || (other instanceof MailCommand // instanceof handles nulls
                    && targetIndex.equals(((MailCommand) other).targetIndex)
                    && isAll.equals(((MailCommand) other).isAll));
        }
    }
}

package seedu.intrack.logic.commands;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import seedu.intrack.commons.core.Messages;
import seedu.intrack.logic.commands.exceptions.CommandException;
import seedu.intrack.model.Model;
import seedu.intrack.model.internship.Internship;

/**
 * Sends an email to a target company.
 */
public class MailCommand extends Command {

    public static final String COMMAND_WORD = "mail";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Opens the mail app to send an email to the company in the selected internship.\n";

    public static final String MESSAGE_MAIL_SUCCESS = "Opening mail app now...";

    public static final String MESSAGE_MAIL_FAIL = "The mail app is failed to be launched";

    public static final String MAILTO = "mailto:";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Internship> lastShownList = model.getSelectedInternship();
        if (lastShownList.size() != 1) {
            throw new CommandException(Messages.MESSAGE_NO_INTERNSHIP_SELECTED);
        }
        Internship internshipToEmail = lastShownList.get(0);

        Desktop desktop = Desktop.getDesktop();
        URI uri = URI.create(MAILTO + internshipToEmail.getEmail());

        try {
            desktop.mail(uri);
        } catch (IOException ioe) {
            throw new CommandException(MESSAGE_MAIL_FAIL);
        }

        return new CommandResult(MESSAGE_MAIL_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MailCommand); // instanceof handles nulls
    }
}

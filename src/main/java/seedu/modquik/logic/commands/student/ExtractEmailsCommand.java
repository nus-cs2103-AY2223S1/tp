package seedu.modquik.logic.commands.student;

import java.util.Collection;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import seedu.modquik.logic.commands.Command;
import seedu.modquik.logic.commands.CommandResult;
import seedu.modquik.logic.commands.exceptions.CommandException;
import seedu.modquik.model.Model;
import seedu.modquik.model.student.Student;


/**
 * Generates and copies an email deeplink to clipboard, where recipients are the current list of students.
 */
public class ExtractEmailsCommand extends Command {

    public static final String COMMAND_WORD = "extract emails";

    public static final String MESSAGE_SUCCESS =
            "Copied link to clipboard! Open it by pasting link in the address bar of a browser.";

    private static final String WEBMAIL_DEEPLINK = "https://outlook.office.com/mail/deeplink/compose?to=";

    private static String generateUrl(Collection<Student> students) {

        return students.stream()
                .map(student -> student.getEmail().value)
                .collect(Collectors.joining(",", WEBMAIL_DEEPLINK, ""));
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        ObservableList<Student> students = model.getFilteredStudentList();
        if (students.size() == 0) {
            throw new CommandException("There are no students to copy emails from!");
        }

        // Combine emails into a deeplink string
        String deeplinkString = generateUrl(students);

        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(deeplinkString);
        clipboard.setContent(url);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

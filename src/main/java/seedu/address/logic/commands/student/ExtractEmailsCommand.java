package seedu.address.logic.commands.student;

import java.util.Collection;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;


/**
 * Shows student's grade distribution in ModQuik.
 */
public class ExtractEmailsCommand extends Command {

    public static final String COMMAND_WORD = "extract emails";

    public static final String MESSAGE_SUCCESS =
            "Copied link to clipboard! Open it by pasting link in the address bar of a browser.";

    private static String generateUrl(Collection<Student> students) {
        return students.stream()
                .map(student -> student.getEmail().value)
                .collect(Collectors.joining(",", "mailto:", ""));
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        ObservableList<Student> students = model.getFilteredPersonList();
        if (students.size() == 0) {
            throw new CommandException("There are no students to copy emails from!");
        }

        // Combine emails into a mailto: string
        String mailtoString = generateUrl(students);

        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(mailtoString);
        clipboard.setContent(url);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

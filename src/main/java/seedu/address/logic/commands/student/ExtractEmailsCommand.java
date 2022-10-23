package seedu.address.logic.commands.student;

import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.student.Student;


/**
 * Shows student's grade distribution in ModQuik.
 */
public class ExtractEmailsCommand extends Command {

    public static final String COMMAND_WORD = "extract emails";

    public static final String MESSAGE_SUCCESS = "Copied link to clipboard! Open it in a browser.";

    @Override
    public CommandResult execute(Model model) {
        ObservableList<Student> students = model.getFilteredPersonList();

        // Combine emails into a mailto: string
        String mailtoString = students.stream()
                .map(student -> student.getEmail().value)
                .collect(Collectors.joining(",", "mailto:", ""));

        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(mailtoString);
        clipboard.setContent(url);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

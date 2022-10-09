package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.note.Note;

public class ListNoteCommand extends Command {

    public static final String COMMAND_WORD = "listNote";

    public static final String MESSAGE_SUCCESS = "Listed all notes";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ObservableList<Note> notebook = model.getAddressBook().getNoteBook();
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < notebook.size(); i++) {
            builder.append((i + 1) + ") ")
                    .append(notebook.get(i).toString())
                    .append("\n");

        }
        builder.append(MESSAGE_SUCCESS);
        return new CommandResult(builder.toString());
    }
}

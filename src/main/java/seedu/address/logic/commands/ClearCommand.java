package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Collections;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears current displayed list.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Displayed list has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        switch(model.getCurrentListType()) {
        case STUDENT_LIST:
            model.setStudents(Collections.emptyList());
            break;

        case TUTOR_LIST:
            model.setTutors(Collections.emptyList());
            break;

        case TUITIONCLASS_LIST:
            model.setTuitionClasses(Collections.emptyList());
            break;

        default:
            model.setAddressBook(new AddressBook());
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

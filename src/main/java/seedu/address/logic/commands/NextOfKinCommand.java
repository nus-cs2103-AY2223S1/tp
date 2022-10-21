package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RELATIONSHIP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.nextofkin.NextOfKin;
import seedu.address.model.person.student.Student;

public class NextOfKinCommand extends Command {

    public static final String COMMAND_WORD = "nok";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds or removes next of kin particulars to student identified by the index number used in the "
            + "displayed list. "
            + "Existing particulars will be overwritten by the input values.\n"
            + "Specifying only the index without any particulars will remove the next of kin particulars.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_RELATIONSHIP + "RELATIONSHIP "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Mama Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "mamad@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_RELATIONSHIP + "mother "
            + PREFIX_TAG + "badBoy "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_ADD_NEXTOFKIN_SUCCESS = "Added Next Of Kin: %1$s to Student: %2$s";
    public static final String MESSAGE_REMOVE_NEXTOFKIN_SUCCESS = "Removed Next Of Kin from Student: %1$s";

    private final Index index;
    private final NextOfKin nextOfKin;

    public NextOfKinCommand(Index index) {
        requireNonNull(index);

        this.index = index;
        this.nextOfKin = null;
    }

    public NextOfKinCommand(Index index, NextOfKin nextOfKin) {
        requireNonNull(index);
        requireNonNull(nextOfKin);

        this.index = index;
        this.nextOfKin = nextOfKin;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.getCurrentListType() == Model.ListType.TUTOR_LIST
                || model.getCurrentListType() == Model.ListType.TUITIONCLASS_LIST
                || model.getCurrentListType() == Model.ListType.PERSON_LIST) {
            throw new CommandException((Messages.MESSAGE_WRONG_LIST_FOR_COMMAND_USAGE));
        }

        FilteredList<?> lastShownList = model.getCurrentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Student studentToEdit = (Student) lastShownList.get(index.getZeroBased());

        if (this.nextOfKin == null) {
            studentToEdit.removeNextOfKin();
            return new CommandResult(String.format(MESSAGE_REMOVE_NEXTOFKIN_SUCCESS, studentToEdit));
        } else {
            studentToEdit.addNextOfKin(this.nextOfKin);
            return new CommandResult(String.format(MESSAGE_ADD_NEXTOFKIN_SUCCESS, this.nextOfKin, studentToEdit));
        }
    }
}

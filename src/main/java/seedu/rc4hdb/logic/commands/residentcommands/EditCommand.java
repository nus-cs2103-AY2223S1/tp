package seedu.rc4hdb.logic.commands.residentcommands;

import static java.util.Objects.requireNonNull;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_HOUSE;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_MATRIC_NUMBER;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_ROOM;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.rc4hdb.model.Model.PREDICATE_SHOW_ALL_RESIDENTS;

import java.util.List;
import java.util.Set;

import seedu.rc4hdb.commons.core.Messages;
import seedu.rc4hdb.commons.core.index.Index;
import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.ModelCommand;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.resident.ResidentDescriptor;
import seedu.rc4hdb.model.resident.fields.Email;
import seedu.rc4hdb.model.resident.fields.Gender;
import seedu.rc4hdb.model.resident.fields.House;
import seedu.rc4hdb.model.resident.fields.MatricNumber;
import seedu.rc4hdb.model.resident.fields.Name;
import seedu.rc4hdb.model.resident.fields.Phone;
import seedu.rc4hdb.model.resident.fields.Room;
import seedu.rc4hdb.model.resident.fields.Tag;

/**
 * Edits the details of an existing resident in the address book.
 */
public class EditCommand implements ModelCommand {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the resident identified "
            + "by the index number used in the displayed resident list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ROOM + "FLOOR-UNIT] "
            + "[" + PREFIX_GENDER + "GENDER] "
            + "[" + PREFIX_HOUSE + "HOUSE] "
            + "[" + PREFIX_MATRIC_NUMBER + "MATRIC_NUMBER] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_RESIDENT_SUCCESS = "Edited Resident: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_RESIDENT = "This resident already exists in the address book.";

    private final Index index;
    private final ResidentDescriptor editResidentDescriptor;

    /**
     * @param index of the resident in the filtered resident list to edit
     * @param editResidentDescriptor details to edit the resident with
     */
    public EditCommand(Index index, ResidentDescriptor editResidentDescriptor) {
        requireNonNull(index);
        requireNonNull(editResidentDescriptor);

        this.index = index;
        this.editResidentDescriptor = new ResidentDescriptor(editResidentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Resident> lastShownList = model.getFilteredResidentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RESIDENT_DISPLAYED_INDEX);
        }

        Resident residentToEdit = lastShownList.get(index.getZeroBased());
        Resident editedResident = createEditedResident(residentToEdit, editResidentDescriptor);

        if (!residentToEdit.isSameResident(editedResident) && model.hasResident(editedResident)) {
            throw new CommandException(MESSAGE_DUPLICATE_RESIDENT);
        }

        model.setResident(residentToEdit, editedResident);
        model.updateFilteredResidentList(PREDICATE_SHOW_ALL_RESIDENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_RESIDENT_SUCCESS, editedResident));
    }

    /**
     * Creates and returns a {@code Resident} with the details of {@code residentToEdit}
     * edited with {@code editResidentDescriptor}.
     */
    private static Resident createEditedResident(Resident residentToEdit, ResidentDescriptor editResidentDescriptor) {
        assert residentToEdit != null;

        Name updatedName = editResidentDescriptor.getName().orElse(residentToEdit.getName());
        Phone updatedPhone = editResidentDescriptor.getPhone().orElse(residentToEdit.getPhone());
        Email updatedEmail = editResidentDescriptor.getEmail().orElse(residentToEdit.getEmail());
        Room updatedRoom = editResidentDescriptor.getRoom().orElse(residentToEdit.getRoom());
        Gender updatedGender = editResidentDescriptor.getGender().orElse(residentToEdit.getGender());
        House updatedHouse = editResidentDescriptor.getHouse().orElse(residentToEdit.getHouse());
        MatricNumber updatedMatricNumber = editResidentDescriptor.getMatricNumber().orElse(
                residentToEdit.getMatricNumber());
        Set<Tag> updatedTags = editResidentDescriptor.getTags().orElse(residentToEdit.getTags());

        return new Resident(updatedName, updatedPhone, updatedEmail, updatedRoom, updatedGender, updatedHouse,
                updatedMatricNumber, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editResidentDescriptor.equals(e.editResidentDescriptor);
    }

}

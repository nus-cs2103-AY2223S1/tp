package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.staff.Staff;
import seedu.address.model.staff.StaffContact;
import seedu.address.model.staff.StaffDepartment;
import seedu.address.model.staff.StaffInsurance;
import seedu.address.model.staff.StaffName;
import seedu.address.model.staff.StaffTitle;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Staff}.
 */
class JsonAdaptedStaff {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Staff's %s field is missing!";

    private final String staffContact;
    private final String staffDepartment;
    private final String staffInsurance;
    private final String staffName;
    private final String staffTitle;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedStaff} with the given staff details.
     */
    @JsonCreator
    public JsonAdaptedStaff(@JsonProperty("staffName") String staffName,
                            @JsonProperty("staffContact") String staffContact,
                            @JsonProperty("staffTitle") String staffTitle,
                            @JsonProperty("staffDepartment") String staffDepartment,
                            @JsonProperty("staffInsurance") String staffInsurance,
                            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.staffContact = staffContact;
        this.staffDepartment = staffDepartment;
        this.staffInsurance = staffInsurance;
        this.staffName = staffName;
        this.staffTitle = staffTitle;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Staff} into this class for Jackson use.
     */
    public JsonAdaptedStaff(Staff source) {
        staffName = source.getStaffName().staffName;
        staffContact = source.getStaffContact().staffContact;
        staffDepartment = source.getStaffDepartment().staffDepartment;
        staffInsurance = source.getStaffInsurance().staffInsurance;
        staffTitle = source.getStaffTitle().staffTitle;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted staff object into the model's {@code Staff} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted staff.
     */
    public Staff toModelType() throws IllegalValueException {
        final List<Tag> staffTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            staffTags.add(tag.toModelType());
        }

        if (staffName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StaffName.class.getSimpleName()));
        }
        if (!StaffName.isValidStaffName(staffName)) {
            throw new IllegalValueException(StaffName.MESSAGE_CONSTRAINTS);
        }
        final StaffName modelStaffName = new StaffName(staffName);

        if (staffContact == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StaffContact.class.getSimpleName()));
        }
        if (!StaffContact.isValidStaffContact(staffContact)) {
            throw new IllegalValueException(StaffContact.MESSAGE_CONSTRAINTS);
        }
        final StaffContact modelStaffContact = new StaffContact(staffContact);

        if (staffDepartment == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StaffDepartment.class.getSimpleName()));
        }
        if (!StaffDepartment.isValidStaffDepartment(staffDepartment)) {
            throw new IllegalValueException(StaffDepartment.MESSAGE_CONSTRAINTS);
        }
        final StaffDepartment modelStaffDepartment = new StaffDepartment(staffDepartment);

        if (staffTitle == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StaffTitle.class.getSimpleName()));
        }
        if (!StaffTitle.isValidStaffTitle(staffTitle)) {
            throw new IllegalValueException(StaffTitle.MESSAGE_CONSTRAINTS);
        }
        final StaffTitle modelStaffTitle = new StaffTitle(staffTitle);

        if (staffInsurance == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StaffInsurance.class.getSimpleName()));
        }
        if (!StaffInsurance.isValidStaffInsurance(staffInsurance)) {
            throw new IllegalValueException(StaffInsurance.MESSAGE_CONSTRAINTS);
        }
        final StaffInsurance modelStaffInsurance = new StaffInsurance(staffInsurance);

        final Set<Tag> modelTags = new HashSet<>(staffTags);
        return new Staff(modelStaffName, modelStaffContact, modelStaffTitle,
                         modelStaffDepartment, modelStaffInsurance, modelTags);
    }

}

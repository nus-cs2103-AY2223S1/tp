package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyStaffList;
import seedu.address.model.StaffList;
import seedu.address.model.staff.Staff;

@JsonRootName(value = "stafflist")
class JsonSerializableStaffList {

    public static final String MESSAGE_DUPLICATE_PROJECT = "Staffs list contains duplicate staff(s).";

    private final List<JsonAdaptedStaff> staffs = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableStaffList} with the given staffs.
     */
    @JsonCreator
    public JsonSerializableStaffList(@JsonProperty("staffs") List<JsonAdaptedStaff> staffs) {
        this.staffs.addAll(staffs);
    }

    /**
     * Converts a given {@code ReadOnlyStaffList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableStaffList}.
     */
    public JsonSerializableStaffList(ReadOnlyStaffList source) {
        staffs.addAll(source.getStaffList().stream().map(JsonAdaptedStaff::new).collect(Collectors.toList()));
    }

    /**
     * Converts this staff list into the model's {@code StaffList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public StaffList toModelType() throws IllegalValueException {
        StaffList staffList = new StaffList();
        for (JsonAdaptedStaff jsonAdaptedStaff : staffs) {
            Staff staff = jsonAdaptedStaff.toModelType();
            if (staffList.hasStaff(staff)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PROJECT);
            }
            staffList.addStaff(staff);
        }
        return staffList;
    }
}

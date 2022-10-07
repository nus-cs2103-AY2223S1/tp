package seedu.address.storage.acadyearmodulelist;

import java.util.ArrayList;
import java.util.List;

import org.openapitools.client.model.ModuleCondensed;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AcadYearModuleList;

/**
 * An Immutable acadYearModuleList that is serializable to JSON format.
 */
@JsonRootName(value = "acadyearmodulelist")
class JsonSerializableAcadYearModuleList {

    private final List<ModuleCondensed> modules = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAcadYearModuleList} with the given modules.
     */
    @JsonCreator
    public JsonSerializableAcadYearModuleList(@JsonProperty("persons") List<ModuleCondensed> modules) {
        this.modules.addAll(modules);
    }

    /**
     * Converts this module list into the model's {@code AcadYearModuleList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AcadYearModuleList toModelType() throws IllegalValueException {
        return new AcadYearModuleList(modules);
    }

}

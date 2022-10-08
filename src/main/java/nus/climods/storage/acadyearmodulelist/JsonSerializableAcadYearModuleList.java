package nus.climods.storage.acadyearmodulelist;

import java.util.List;

import org.openapitools.client.model.ModuleCondensed;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import nus.climods.commons.exceptions.IllegalValueException;
import nus.climods.model.AcadYearModuleList;

/**
 * An Immutable acadYearModuleList that is serializable to JSON format.
 */
@JsonRootName(value = "modules")
class JsonSerializableAcadYearModuleList {

    private final List<ModuleCondensed> modules;

    /**
     * Constructs a {@code JsonSerializableAcadYearModuleList} with the given modules.
     */
    @JsonCreator
    public JsonSerializableAcadYearModuleList(@JsonProperty("modules") List<ModuleCondensed> modules) {
        this.modules = modules;
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

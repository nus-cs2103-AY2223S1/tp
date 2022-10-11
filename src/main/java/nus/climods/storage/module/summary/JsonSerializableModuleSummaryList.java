package nus.climods.storage.module.summary;

import java.util.List;

import org.openapitools.client.model.ModuleCondensed;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import nus.climods.commons.exceptions.IllegalValueException;
import nus.climods.model.module.ModuleSummaryList;

/**
 * An Immutable acadYearModuleList that is serializable to JSON format.
 */
@JsonRootName(value = "modules")
class JsonSerializableModuleSummaryList {

    private final List<ModuleCondensed> modules;

    /**
     * Constructs a {@code JsonSerializableModuleSummaryList} with the given modules.
     */
    @JsonCreator
    public JsonSerializableModuleSummaryList(@JsonProperty("modules") List<ModuleCondensed> modules) {
        this.modules = modules;
    }

    /**
     * Converts this module list into the model's {@code ModuleSummaryList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ModuleSummaryList toModelType() throws IllegalValueException {
        return new ModuleSummaryList(modules);
    }

}

package nus.climods.storage.module;

import java.util.List;
import java.util.stream.Collectors;

import org.openapitools.client.model.ModuleInformation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import nus.climods.commons.exceptions.IllegalValueException;
import nus.climods.model.module.ModuleList;

@JsonRootName(value = "moduleList")
class JsonSerializableModuleList {

    private final List<ModuleInformation> modules;

    /**
     * Constructs a {@code JsonSerializableModuleList} with the given modules.
     */
    @JsonCreator
    public JsonSerializableModuleList(@JsonProperty("modules") List<ModuleInformation> modules) {
        this.modules = modules;
    }

    /**
     * Converts this module list into the model's {@code ModuleList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ModuleList toModelType() throws IllegalValueException {
        return new ModuleList(modules.stream().map(nus.climods.model.module.Module::new).collect(
            Collectors.toList()));
    }
}

package nus.climods.storage.module.user;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import nus.climods.commons.exceptions.IllegalValueException;
import nus.climods.logic.commands.exceptions.CommandException;
import nus.climods.model.module.UniqueUserModuleList;
import nus.climods.model.module.UserModule;
import nus.climods.storage.exceptions.StorageException;

/**
 * An Immutable userModuleList that is serializable to JSON format.
 */
@JsonRootName(value = "userModules")
class JsonSerializableUserModuleList {
    public static final String MESSAGE_DUPLICATE_MODULE = "Modules list contains duplicate module(s).";

    private final List<JsonAdaptedUserModule> modules;
    /**
     * Constructs a {@code JsonSerializableUserModuleList} with the given modules and model
     */
    @JsonCreator
    public JsonSerializableUserModuleList(@JsonProperty("userModules") List<JsonAdaptedUserModule> modules) {
        this.modules = modules;
    }
    /**
     * Converts this user module list into the model's {@code UniqueUserModuleList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public UniqueUserModuleList toModelType() throws CommandException, StorageException {
        UniqueUserModuleList userModuleList = new UniqueUserModuleList();
        for (JsonAdaptedUserModule jsonAdaptedModule : modules) {
            UserModule module = jsonAdaptedModule.toModelType();
            if (userModuleList.contains(module)) {
                throw new StorageException(MESSAGE_DUPLICATE_MODULE);
            }
            userModuleList.add(module);
        }
        return userModuleList;
    }
}

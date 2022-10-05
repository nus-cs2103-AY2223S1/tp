package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleDescription;
import seedu.address.model.module.ModuleName;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Jackson-friendly version of {@link Module}
 */
public class JsonAdaptedModule {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Module's %s field is missing!";
    private final String moduleCode;
    private final String moduleDescription;
    private final String moduleName;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedPerson> students = new ArrayList<>();


    /**
     * Constructs a {@code JsonAdaptedModule} with the given details of the module.
     */
    @JsonCreator
    public JsonAdaptedModule(@JsonProperty("moduleCode") String moduleCode,
                             @JsonProperty("moduleDescription") String moduleDescription,
                             @JsonProperty("moduleName") String moduleName,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                             @JsonProperty("students") List<JsonAdaptedPerson> students) {
        this.moduleCode = moduleCode;
        this.moduleDescription = moduleDescription;
        this.moduleName = moduleName;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (students != null) {
            this.students.addAll(students);
        }
    }

    /**
     * Converts a given {@code Module} into this class for Jackson use.
     * @param source
     */
    public JsonAdaptedModule(Module source) {
        moduleCode = source.getCode().fullCode;
        moduleDescription = source.getDescription().fullDescription;
        moduleName = source.getName().fullName;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        students.addAll(source.getStudents().stream()
                .map(JsonAdaptedPerson::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Module} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Module toModelType() throws IllegalValueException {
        final List<Tag> moduleTags = new ArrayList<>();
        final List<Person> moduleStudents = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            moduleTags.add(tag.toModelType());
        }
        for (JsonAdaptedPerson person : students) {
            moduleStudents.add(person.toModelType());
        }

        if (moduleName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, ModuleName.class.getSimpleName()));
        }
        if (!ModuleName.isValidName(moduleName)) {
            throw new IllegalValueException(ModuleName.MESSAGE_CONSTRAINTS);
        }
        final ModuleName modelModuleName = new ModuleName(moduleName);

        if (moduleDescription == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, ModuleDescription.class.getSimpleName()));
        }
        if (!ModuleDescription.isValidDescription(moduleDescription)) {
            throw new IllegalValueException(ModuleDescription.MESSAGE_CONSTRAINTS);
        }
        final ModuleDescription modelModuleDescription = new ModuleDescription(moduleDescription);

        if (moduleCode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, ModuleCode.class.getSimpleName()));
        }
        if (!ModuleCode.isValidCode(moduleCode)) {
            throw new IllegalValueException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        final ModuleCode modelModuleCode = new ModuleCode(moduleCode);

        final Set<Tag> modelModuleTags = new HashSet<>(moduleTags);
        final ArrayList<Person> modelModuleStudents = new ArrayList<>(moduleStudents);
        return new Module(modelModuleName, modelModuleCode, modelModuleDescription, modelModuleTags, modelModuleStudents);
    }
}

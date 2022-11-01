package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleTitle;
import seedu.address.model.module.link.Link;
import seedu.address.model.module.task.Task;
import seedu.address.model.module.task.TaskList;
import seedu.address.model.person.Person;

/**
 * Jackson-friendly version of {@link Module}.
 */
class JsonAdaptedModule {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Module's %s field is missing!";

    private final String moduleCode;
    private final String moduleTitle;
    private final List<JsonAdaptedTask> moduleTasks;
    private final List<JsonAdaptedLink> moduleLinks;
    private final Set<JsonAdaptedPerson> modulePersons;

    /**
     * Constructs a {@code JsonAdaptedModule} with the given module details.
     */
    @JsonCreator
    public JsonAdaptedModule(@JsonProperty("moduleCode") String moduleCode,
                             @JsonProperty("moduleTitle") String moduleTitle,
                             @JsonProperty("tasks") List<JsonAdaptedTask> tasks,
                             @JsonProperty("links") List<JsonAdaptedLink> links,
                             @JsonProperty("persons") Set<JsonAdaptedPerson> persons) {
        this.moduleCode = moduleCode;
        this.moduleTitle = moduleTitle;
        this.moduleTasks = tasks;
        this.moduleLinks = links;
        this.modulePersons = persons;
    }

    /**
     * Converts a given {@code Module} into this class for Jackson use.
     */
    public JsonAdaptedModule(Module source) {
        moduleCode = source.getModuleCode().value;
        moduleTitle = source.getModuleTitle().value;
        moduleTasks = source.getTasks().stream()
                .map(JsonAdaptedTask::new)
                .collect(Collectors.toList());
        moduleLinks = source.getLinks().stream()
                .map(JsonAdaptedLink::new)
                .collect(Collectors.toList());
        modulePersons = source.getPersons().stream()
                .map(JsonAdaptedPerson::new)
                .collect(Collectors.toSet());
    }

    /**
     * Converts this Jackson-friendly adapted module object into the model's {@code Module} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted module.
     */
    public Module toModelType() throws IllegalValueException {

        if (moduleCode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ModuleCode.class.getSimpleName()));
        }
        if (!ModuleCode.isValidModuleCode(moduleCode)) {
            throw new IllegalValueException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        final ModuleCode modelModuleCode = new ModuleCode(moduleCode);

        if (moduleTitle == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ModuleTitle.class.getSimpleName()));
        }
        final ModuleTitle modelModuleTitle = new ModuleTitle(moduleTitle);

        if (moduleLinks == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Link.class.getSimpleName()));
        }
        final Set<Link> modelModuleLinks = new HashSet<>();
        for (JsonAdaptedLink links : moduleLinks) {
            modelModuleLinks.add(links.toModelType());
        }

        if (moduleTasks == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TaskList.class.getSimpleName()));
        }
        final List<Task> modelModuleTasks = new ArrayList<>();
        for (JsonAdaptedTask task : moduleTasks) {
            modelModuleTasks.add(task.toModelType());
        }

        if (modulePersons == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Person.class.getSimpleName()));
        }
        final Set<Person> modelModulePersons = new HashSet<>();
        for (JsonAdaptedPerson person : modulePersons) {
            modelModulePersons.add(person.toModelType());
        }

        return new Module(modelModuleCode, modelModuleTitle, modelModuleTasks,
                modelModuleLinks, modelModulePersons);
    }

}

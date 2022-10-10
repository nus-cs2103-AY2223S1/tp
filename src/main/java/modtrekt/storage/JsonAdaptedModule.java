package modtrekt.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import modtrekt.commons.exceptions.IllegalValueException;
import modtrekt.model.module.ModCode;
import modtrekt.model.module.ModCredit;
import modtrekt.model.module.ModName;
import modtrekt.model.module.Module;
import modtrekt.model.task.Task;

/**
 * Jackson-friendly version of {@link Module}.
 */
class JsonAdaptedModule {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Module's %s field is missing!";

    private final String name;
    private final String code;
    private final String credit;
    private final List<JsonAdaptedTask> tasks = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedModule} with the given module details.
     */
    @JsonCreator
    public JsonAdaptedModule(@JsonProperty("name") String name, @JsonProperty("code") String code,
                             @JsonProperty("credit") String credit,
                             @JsonProperty("tasks") List<JsonAdaptedTask> tasks) {
        this.name = name;
        this.credit = credit;
        this.code = code;
        if (tasks != null) {
            this.tasks.addAll(tasks);
        }
    }

    /**
     * Converts a given {@code Module} into this class for Jackson use.
     */
    public JsonAdaptedModule(Module source) {
        name = source.getName().fullName;
        code = source.getCode().value;
        credit = source.getCredits().value;
        tasks.addAll(source.getTasksList().stream()
                .map(JsonAdaptedTask::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Module} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Module toModelType() throws IllegalValueException {
        final List<Task> moduleTasks = new ArrayList<>();

        for (JsonAdaptedTask task : this.tasks) {
            moduleTasks.add(task.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, ModName.class.getSimpleName()));
        }
        if (!ModName.isValidName(name)) {
            throw new IllegalValueException(ModName.MESSAGE_CONSTRAINTS);
        }
        final ModName modelName = new ModName(name);

        if (credit == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, ModCredit.class.getSimpleName()));
        }
        if (!ModCredit.isValidCredit(credit)) {
            throw new IllegalValueException(ModCredit.MESSAGE_CONSTRAINTS);
        }
        final ModCredit modelCredit = new ModCredit(credit);

        if (code == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, ModCode.class.getSimpleName()));
        }
        if (!ModCode.isValidCode(code)) {
            throw new IllegalValueException(ModCode.MESSAGE_CONSTRAINTS);
        }
        final ModCode modelCode = new ModCode(code);

        Module created = new Module(modelCode, modelName, modelCredit);
        created.addTasks(moduleTasks);
        return created;
    }

}

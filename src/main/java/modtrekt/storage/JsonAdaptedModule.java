package modtrekt.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import modtrekt.commons.exceptions.IllegalValueException;
import modtrekt.model.module.ModCode;
import modtrekt.model.module.ModCredit;
import modtrekt.model.module.ModName;
import modtrekt.model.module.ModTaskCount;
import modtrekt.model.module.Module;

/**
 * Jackson-friendly version of {@link Module}.
 */
class JsonAdaptedModule {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Module's %s field is missing!";

    private final String name;
    private final String code;
    private final String credit;
    private final String taskCount;

    /**
     * Constructs a {@code JsonAdaptedModule} with the given module details.
     */
    @JsonCreator
    public JsonAdaptedModule(@JsonProperty("name") String name, @JsonProperty("code") String code,
                             @JsonProperty("credit") String credit,
                             @JsonProperty("taskCount") String taskCount) {
        this.name = name;
        this.credit = credit;
        this.code = code;
        this.taskCount = taskCount;
    }

    /**
     * Converts a given {@code Module} into this class for Jackson use.
     */
    public JsonAdaptedModule(Module source) {
        name = source.getName().getFullName();
        code = source.getCode().getValue();
        credit = source.getCredits().toString();
        taskCount = source.getTaskCountStr();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Module} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Module toModelType() throws IllegalValueException {
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

        if (!ModTaskCount.isValidCount(taskCount)) {
            throw new IllegalValueException(ModTaskCount.MESSAGE_CONSTRAINTS);
        }
        final ModTaskCount modelTaskCount = new ModTaskCount(taskCount);

        Module created = new Module(modelCode, modelName, modelCredit, modelTaskCount);
        return created;
    }

}

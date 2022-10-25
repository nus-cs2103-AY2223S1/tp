package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Task;

/**
 * Jackson-friendly version of a superclass for Deadlines and ToDos to inherit from.
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"

)
@JsonSubTypes({
    @JsonSubTypes.Type(value = JsonAdaptedToDo.class, name = "ToDo"),
    @JsonSubTypes.Type(value = JsonAdaptedDeadline.class, name = "Deadline"),
    @JsonSubTypes.Type(value = JsonAdaptedAssignment.class, name = "Assignment")
})
public abstract class JsonAdaptedTask {
    public abstract Task toModelType() throws IllegalValueException;
}

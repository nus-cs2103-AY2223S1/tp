package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.task.TaskList;

/**
 * Jackson-friendly version of {@link TaskList}.
 */
class JsonAdaptedTaskList {

    private Boolean isSortedByDeadline;

    public JsonAdaptedTaskList() {
        isSortedByDeadline = false;
    }

    /**
     * Constructs a {@code JsonAdaptedTaskList} with the given {@code isSortedByDeadline}.
     */
    @JsonCreator
    public JsonAdaptedTaskList(@JsonProperty("isSortedByDeadline") Boolean isSortedByDeadline) {
        this.isSortedByDeadline = isSortedByDeadline;
    }

    /**
     * Converts a given {@code TaskList} into this class for Jackson use.
     */
    public JsonAdaptedTaskList(TaskList source) {
        isSortedByDeadline = source.isSortedByDeadline();
    }

    public Boolean isSortedByDeadline() {
        return isSortedByDeadline;
    }

    public void setIsSortedByDeadline(Boolean isSortedByDeadline) {
        this.isSortedByDeadline = isSortedByDeadline;
    }
}

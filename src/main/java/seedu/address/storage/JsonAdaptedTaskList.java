package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.task.TaskList;

/**
 * Jackson-friendly version of {@link TaskList}.
 */
class JsonAdaptedTaskList {

    private Boolean isSortByDeadline;

    public JsonAdaptedTaskList() {
        isSortByDeadline = false;
    }

    /**
     * Constructs a {@code JsonAdaptedTaskList} with the given {@code isSortedByDeadline}.
     */
    @JsonCreator
    public JsonAdaptedTaskList(@JsonProperty("isSortedByDeadline") Boolean isSortByDeadline) {
        this.isSortByDeadline = isSortByDeadline;
    }

    /**
     * Converts a given {@code TaskList} into this class for Jackson use.
     */
    public JsonAdaptedTaskList(TaskList source) {
        isSortByDeadline = source.isSortByDeadline();
    }

    public Boolean isSortByDeadline() {
        return isSortByDeadline;
    }

    public void setSortByDeadline(Boolean isSortByDeadline) {
        this.isSortByDeadline = isSortByDeadline;
    }
}

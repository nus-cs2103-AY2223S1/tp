package hobbylist.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import hobbylist.commons.exceptions.IllegalValueException;
import hobbylist.model.HobbyList;
import hobbylist.model.ReadOnlyHobbyList;
import hobbylist.model.activity.Activity;

/**
 * An Immutable HobbyList that is serializable to JSON format.
 */
@JsonRootName(value = "hobbylist")
class JsonSerializableHobbyList {

    public static final String MESSAGE_DUPLICATE_ACTIVITY = "Activities list contains duplicate activities.";

    private final List<JsonAdaptedActivity> activities = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableHobbyList} with the given persons.
     */
    @JsonCreator
    public JsonSerializableHobbyList(@JsonProperty("activities") List<JsonAdaptedActivity> activities) {
        this.activities.addAll(activities);
    }

    /**
     * Converts a given {@code ReadOnlyHobbyList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableHobbyList}.
     */
    public JsonSerializableHobbyList(ReadOnlyHobbyList source) {
        activities.addAll(source.getActivityList().stream().map(JsonAdaptedActivity::new).collect(Collectors.toList()));
    }

    /**
     * Converts this HobbyList into the model's {@code HobbyList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public HobbyList toModelType() throws IllegalValueException {
        HobbyList hobbyList = new HobbyList();
        for (JsonAdaptedActivity jsonAdaptedActivity : activities) {
            Activity activity = jsonAdaptedActivity.toModelType();
            if (hobbyList.hasActivity(activity)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ACTIVITY);
            }
            hobbyList.addActivity(activity);
        }
        return hobbyList;
    }

}

package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.person.Person;

/**
 * Jackson-friendly version of {@link Group}.
 */
public class JsonAdaptedGroup {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String groupName;
    private final List<JsonAdaptedPerson> members = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedGroup} with the given group details.
     */
    @JsonCreator
    public JsonAdaptedGroup(@JsonProperty("groupname") String name, @JsonProperty("members") ArrayList<JsonAdaptedPerson> members) {
        this.groupName = name;
        this.members.addAll(members);
    }

    /**
     * Converts a given {@code Group} into this class for Jackson use.
     */
    public JsonAdaptedGroup(Group source) {
        groupName = source.getName().groupName;
        members.addAll(source.getMembers().stream()
                .map(person -> new JsonAdaptedPerson(person))
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted group object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    final Group toModelType() throws IllegalValueException {
        final Set<Person> membersToSet = new HashSet<>();
        for (JsonAdaptedPerson p : members) {
            membersToSet.add(p.toModelType());
        }

        if (groupName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, GroupName.class.getSimpleName()));
        }
        if (!GroupName.isValidName(groupName)) {
            throw new IllegalValueException(GroupName.MESSAGE_CONSTRAINTS);
        }
        final GroupName nameToSet = new GroupName(groupName);

        return new Group(nameToSet, membersToSet);
    }
}

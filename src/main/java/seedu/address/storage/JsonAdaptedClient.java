package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Client;

import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator= ObjectIdGenerators.UUIDGenerator.class, property="@UUID")
class JsonAdaptedClient {

//    private final List<Meeting> meetings;
    private final String name;

    public JsonAdaptedClient(Client client) {
        name = client.getName();
    }
}

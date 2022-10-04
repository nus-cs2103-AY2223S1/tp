package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;
import java.util.List;

@JsonRootName(value = "clientbook")
class JsonSerializableClientBook {

    private final List<JsonAdaptedClient> clients = new ArrayList<>();
    private final List<JsonAdaptedMeeting> meetings = new ArrayList<>();

    public JsonSerializableClientBook() {

    }

}

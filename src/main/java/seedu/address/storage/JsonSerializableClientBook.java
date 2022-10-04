package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

class JsonSerializableClientBook {

    private final List<JsonAdaptedClient> clients = new ArrayList<>();
    private final List<JsonAdaptedMeeting> meetings = new ArrayList<>();

    public JsonSerializableClientBook() {

    }

}

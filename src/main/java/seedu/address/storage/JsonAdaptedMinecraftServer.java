package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.server.Server;

public class JsonAdaptedMinecraftServer {

    private final String serverName;

    @JsonCreator
    public JsonAdaptedMinecraftServer(String serverName) {
        this.serverName = serverName;
    }

    public JsonAdaptedMinecraftServer(Server source) {
        serverName = source.getServerName();
    }

    @JsonValue
    public String getServerName() {
        return serverName;
    }

    public Server toModelType() throws IllegalValueException {
        if (!Server.isValidServerName(serverName)) {
            throw new IllegalValueException(Server.getServerConstraints());
        }
        return new Server(serverName);
    }
}

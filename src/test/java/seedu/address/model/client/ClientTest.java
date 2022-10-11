package seedu.address.model.client;

import seedu.address.model.Name;

import java.util.ArrayList;

public class ClientTest {
    private static Client clientStub = new Client(new Name("default"), new ClientPhone("98765432"),
            new ClientEmail("default@gmail.com"), new ArrayList<>(), new ClientId(1));

    public static Client getClientStub() {
        return clientStub;
    }
}

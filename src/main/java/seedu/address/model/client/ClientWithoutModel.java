package seedu.address.model.client;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import seedu.address.model.Model;
import seedu.address.model.Name;
import seedu.address.model.Pin;
import seedu.address.model.client.exceptions.ClientNotFoundException;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectId;

// @@author Dernbu
/**
 * This class represents a partial initialisation of client (without access to model).
 * The reason why this exists is to separate Parser classes, and to ensure they are not dependent on
 * Addressbook/Model classes.
 * <p>
 * This is inspired from a functional programming appraoch, where we store intermediate data by nesting functions.
 */
public class ClientWithoutModel implements Function<Model, Client> {

    private final Name name;
    private final ClientMobile mobile;
    private final ClientEmail email;
    private final List<ProjectId> projectIdList;
    private final Pin pin;

    /**
     * Partially initialise a client without access to a Model object.
     * @param name name of cline
     * @param mobile mobile number of client
     * @param email email of client
     * @param projectIdList the list of project ids the client is involved in.
     */

    public ClientWithoutModel(Name name, ClientMobile mobile, ClientEmail email,
                              List<ProjectId> projectIdList, Pin pin) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.projectIdList = projectIdList;
        this.pin = pin;
    }

    @Override
    public Client apply(Model model) {
        ArrayList<Project> projectList = new ArrayList<>();
        for (ProjectId pid : projectIdList) {
            projectList.add(model.getProjectById(pid.getIdInt()));
        }

        Client client = new Client(name, mobile, email, projectList, new ClientId(model.generateClientId()), pin);
        if (client.isEmpty()) {
            throw new ClientNotFoundException();
        }

        return client;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ClientWithoutModel)) {
            return false;
        }
        ClientWithoutModel c = (ClientWithoutModel) o;
        return c == this || (
                this.name.equals(c.name)
                        && this.mobile.equals(c.mobile)
                        && this.email.equals(c.email)
                        && this.projectIdList.equals(c.projectIdList)
                        && this.pin.equals(c.pin));
    }
}

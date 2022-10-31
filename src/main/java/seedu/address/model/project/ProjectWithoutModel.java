package seedu.address.model.project;

import java.util.List;
import java.util.function.Function;

import seedu.address.model.Deadline;
import seedu.address.model.Model;
import seedu.address.model.Name;
import seedu.address.model.Pin;
import seedu.address.model.client.Client;
import seedu.address.model.client.ClientId;
import seedu.address.model.issue.Issue;
import seedu.address.model.list.NotFoundException;

/**
 * This class represents a partial initialisation of project (without access to model).
 * The reason why this exists is to separate Parser classes, and to ensure they are not dependent on
 * Addressbook/Model classes.
 * <p>
 * This is inspired from a functional programming appraoch, where we store intermediate data by nesting functions.
 */
public class ProjectWithoutModel implements Function<Model, Project> {

    private final Name name;
    private final Repository repository;
    private final ClientId clientId;
    private final Deadline deadline;
    private final List<Issue> issueList;
    private final Pin pin;


    /**
     * Partially initialise a Project object without access to a Model class.
     * @param name project name
     * @param repository project repository
     * @param deadline project deadline
     * @param clientId client associated with project
     * @param issueList list of issues in project
     */
    public ProjectWithoutModel(Name name, Repository repository, Deadline deadline,
                               ClientId clientId, List<Issue> issueList, Pin pin) {
        this.name = name;
        this.repository = repository;
        this.deadline = deadline;
        this.clientId = clientId;
        this.issueList = issueList;
        this.pin = pin;
    }

    @Override
    public Project apply(Model model) {
        Client client = Client.EmptyClient.EMPTY_CLIENT;

        if (!clientId.isEmpty()) {
            try {
                client = model.getClientById(clientId.getIdInt());
            } catch (NotFoundException e) {
                client = null;
            }
        }

        return new Project(name, repository, deadline,
                client, issueList, new ProjectId(model.generateProjectId()), pin);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ProjectWithoutModel)) {
            return false;
        }
        ProjectWithoutModel p = (ProjectWithoutModel) o;
        return p == this || (
                this.name.equals(p.name)
                        && this.repository.equals(p.repository)
                        && this.deadline.equals(p.deadline)
                        && this.clientId.equals(p.clientId)
                        && this.issueList.equals(p.issueList)
                        && this.pin.equals(p.pin));
    }

}

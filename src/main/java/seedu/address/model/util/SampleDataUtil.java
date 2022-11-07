package seedu.address.model.util;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.Deadline;
import seedu.address.model.Name;
import seedu.address.model.Pin;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.client.Client;
import seedu.address.model.client.ClientEmail;
import seedu.address.model.client.ClientId;
import seedu.address.model.client.ClientMobile;
import seedu.address.model.issue.Issue;
import seedu.address.model.issue.IssueId;
import seedu.address.model.issue.Status;
import seedu.address.model.issue.Title;
import seedu.address.model.issue.Urgency;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectId;
import seedu.address.model.project.Repository;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static Project[] getSampleProjects() {
        return new Project[] {
            new Project(new Name("Individual Project"), new Repository("johndoe/ip"), new Deadline("2022-03-03"),
                    Client.EmptyClient.EMPTY_CLIENT, new ArrayList<>(), new ProjectId(1), new Pin(false)),
            new Project(new Name("Team Project"), new Repository("johndoe/tp"), new Deadline("2023-01-02"),
                    Client.EmptyClient.EMPTY_CLIENT, new ArrayList<>(), new ProjectId(2), new Pin(false)),
            new Project(new Name("Group Project"), new Repository("johndoe/gp"), new Deadline("2023-11-29"),
                    Client.EmptyClient.EMPTY_CLIENT, new ArrayList<>(), new ProjectId(3), new Pin(false)),
            new Project(new Name("Personal Project"), new Repository("johndoe/pp"), new Deadline("2022-04-11"),
                    Client.EmptyClient.EMPTY_CLIENT, new ArrayList<>(), new ProjectId(4), new Pin(false)),
            new Project(new Name("Random Project"), new Repository("johndoe/rp"), new Deadline("2022-05-27"),
                    Client.EmptyClient.EMPTY_CLIENT, new ArrayList<>(), new ProjectId(5), new Pin(false)),
            new Project(new Name("Final Year Project"), new Repository("johndoe/fyp"), new Deadline("2023-02-27"),
                    Client.EmptyClient.EMPTY_CLIENT, new ArrayList<>(), new ProjectId(6), new Pin(false)),
        };
    }

    public static Client[] getSampleClients(Project[] sampleProjects) {
        Client[] sampleClients = new Client[] {
            new Client(new Name("Alex Yeoh"),
                    new ClientMobile("87438807"),
                    new ClientEmail("alexyeoh@example.com"),
                    new ArrayList<>(List.of(sampleProjects[0], sampleProjects[1])),
                    new ClientId(1),
                    new Pin(false)),
            new Client(new Name("Bernice Yu"),
                    new ClientMobile("99272758"),
                    new ClientEmail("berniceyu@example.com"),
                    new ArrayList<>(List.of(sampleProjects[2])),
                    new ClientId(2),
                    new Pin(false)),
            new Client(new Name("Charlotte Oliveiro"),
                    new ClientMobile("93210283"),
                    new ClientEmail("charlotte@example.com"),
                    new ArrayList<>(List.of(sampleProjects[3])),
                    new ClientId(3),
                    new Pin(false)),
            new Client(new Name("David Li"),
                    new ClientMobile("91031282"),
                    new ClientEmail("lidavid@example.com"),
                    new ArrayList<>(List.of(sampleProjects[4])),
                    new ClientId(4),
                    new Pin(false)),
            new Client(new Name("Irfan Ibrahim"),
                    new ClientMobile("92492021"),
                    new ClientEmail("irfan@example.com"),
                    new ArrayList<>(List.of(sampleProjects[5])),
                    new ClientId(5),
                    new Pin(false)),
        };
        sampleProjects[0].setClient(sampleClients[0]);
        sampleProjects[1].setClient(sampleClients[0]);
        sampleProjects[2].setClient(sampleClients[1]);
        sampleProjects[3].setClient(sampleClients[2]);
        sampleProjects[4].setClient(sampleClients[3]);
        sampleProjects[5].setClient(sampleClients[4]);
        return sampleClients;
    }

    public static Issue[] getSampleIssues(Project[] sampleProjects) {
        Issue[] sampleIssues = new Issue[] {
            new Issue(new Title("Refactor UI Classes"),
                    new Deadline("2022-01-01"),
                    Urgency.LOW,
                    new Status(false),
                    sampleProjects[0],
                    new IssueId(1),
                    new Pin(false)),
            new Issue(new Title("Fix loading screen bug"),
                    new Deadline("2022-10-04"),
                    Urgency.MEDIUM,
                    new Status(true),
                    sampleProjects[1],
                    new IssueId(2),
                    new Pin(false)),
            new Issue(new Title("Implement filter command"),
                    new Deadline("2022-09-03"),
                    Urgency.LOW,
                    new Status(false),
                    sampleProjects[1],
                    new IssueId(3),
                    new Pin(false)),
            new Issue(new Title("Update collision logic"),
                    new Deadline("2023-07-14"),
                    Urgency.HIGH,
                    new Status(true),
                    sampleProjects[2],
                    new IssueId(4),
                    new Pin(false)),
            new Issue(new Title("Create Developer Guide"),
                    new Deadline("2022-02-04"),
                    Urgency.HIGH,
                    new Status(true),
                    sampleProjects[3],
                    new IssueId(5),
                    new Pin(false)),
            new Issue(new Title("Change default behaviour"),
                    new Deadline("2022-02-19"),
                    Urgency.MEDIUM,
                    new Status(false),
                    sampleProjects[4],
                    new IssueId(6),
                    new Pin(false)),
        };
        return sampleIssues;
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        Project[] sampleProjects = getSampleProjects();
        Client[] sampleClients = getSampleClients(sampleProjects);
        Issue[] sampleIssues = getSampleIssues(sampleProjects);

        for (Project sampleProject : sampleProjects) {
            sampleAb.addProject(sampleProject);
        }
        for (Client sampleClient : sampleClients) {
            sampleAb.addClient(sampleClient);
        }

        for (Issue sampleIssue : sampleIssues) {
            sampleAb.addIssue(sampleIssue);
        }
        return sampleAb;
    }

}

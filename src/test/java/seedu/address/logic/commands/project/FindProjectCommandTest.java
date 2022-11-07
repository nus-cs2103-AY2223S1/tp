package seedu.address.logic.commands.project;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.parser.predicates.ProjectContainsKeywordsPredicate;
import seedu.address.model.Deadline;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Name;
import seedu.address.model.Pin;
import seedu.address.model.client.Client;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectId;
import seedu.address.model.project.Repository;
import seedu.address.ui.StubUiManager;
import seedu.address.ui.Ui;


public class FindProjectCommandTest {
    private final Ui stubUi = new StubUiManager();

    @Test
    public void execute_findProjectName_success() {
        Model actualModel = new ModelManager();
        Project ab3Project = new Project(new Name("ab3 Beta"),
                new Repository("stub/stub"), new Deadline("2022-02-02"),
                new Client(new Name("Stub")), new ArrayList<>(), new ProjectId(1),
                new Pin(false));
        Project ab3FinalProject = new Project(new Name("AB3 FINAL"),
                new Repository("editStub/editStub"), new Deadline("2023-01-02"),
                new Client(new Name("Stub")), new ArrayList<>(), new ProjectId(2),
                new Pin(false));
        Project ab3DevProject = new Project(new Name("Dev Ab3"),
                new Repository("editab3/editStub"), new Deadline("2025-01-02"),
                new Client(new Name("Stub")), new ArrayList<>(), new ProjectId(2),
                new Pin(false));

        actualModel.addProject(ab3Project);
        actualModel.addProject(ab3FinalProject);
        actualModel.addProject(ab3DevProject);

        CommandResult expectedCommandResultName = new CommandResult(
                String.format(Messages.MESSAGE_PROJECTS_LISTED_OVERVIEW,
                        actualModel.getFilteredProjectList().size()));

        Model expectedModel = new ModelManager();
        expectedModel.addProject(ab3Project);
        expectedModel.addProject(ab3FinalProject);
        expectedModel.addProject(ab3DevProject);

        List<String> name = Arrays.asList("AB3");
        List<String> empty = new ArrayList<>();

        FindProjectCommand findName = new FindProjectCommand(new ProjectContainsKeywordsPredicate(name, empty, empty,
                empty, empty));

        assertCommandSuccess(findName, actualModel, expectedCommandResultName, expectedModel, stubUi);

    }

    @Test
    public void execute_findProjectRepository_success() {
        Model actualModel = new ModelManager();
        Project ab3Project = new Project(new Name("ab3 Beta"),
                new Repository("stub/stub"), new Deadline("2022-02-02"),
                new Client(new Name("Stub")), new ArrayList<>(), new ProjectId(1),
                new Pin(false));
        Project ab3FinalProject = new Project(new Name("AB3 FINAL"),
                new Repository("editStub/editStub"), new Deadline("2023-01-02"),
                new Client(new Name("Stub")), new ArrayList<>(), new ProjectId(2),
                new Pin(false));
        Project ab3DevProject = new Project(new Name("Dev Ab3"),
                new Repository("editStub/editStub"), new Deadline("2025-01-02"),
                new Client(new Name("Stub")), new ArrayList<>(), new ProjectId(2),
                new Pin(false));

        actualModel.addProject(ab3Project);
        actualModel.addProject(ab3FinalProject);
        actualModel.addProject(ab3DevProject);


        Model expectedModel = new ModelManager();
        expectedModel.addProject(ab3FinalProject);
        expectedModel.addProject(ab3DevProject);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(Messages.MESSAGE_PROJECTS_LISTED_OVERVIEW,
                        expectedModel.getFilteredProjectList().size()));

        List<String> repo = Arrays.asList("editStub/editStub");
        List<String> empty = new ArrayList<>();

        FindProjectCommand findName = new FindProjectCommand(new ProjectContainsKeywordsPredicate(empty, repo, empty,
                empty, empty));

        assertCommandSuccess(findName, actualModel, expectedCommandResult, expectedModel, stubUi);

    }

    @Test
    public void execute_findProjectClientName_success() {
        Model actualModel = new ModelManager();
        Project ab3Project = new Project(new Name("ab3 Beta"),
                new Repository("stub/stub"), new Deadline("2022-02-02"),
                new Client(new Name("NotStub")), new ArrayList<>(), new ProjectId(1),
                new Pin(false));
        Project ab3FinalProject = new Project(new Name("AB3 FINAL"),
                new Repository("editStub/editStub"), new Deadline("2023-01-02"),
                new Client(new Name("NotStub")), new ArrayList<>(), new ProjectId(2),
                new Pin(false));
        Project ab3DevProject = new Project(new Name("Dev Ab3"),
                new Repository("editStub/editStub"), new Deadline("2025-01-02"),
                new Client(new Name("IsAStub")), new ArrayList<>(), new ProjectId(2),
                new Pin(false));

        actualModel.addProject(ab3Project);
        actualModel.addProject(ab3FinalProject);
        actualModel.addProject(ab3DevProject);


        Model expectedModel = new ModelManager();
        expectedModel.addProject(ab3DevProject);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(Messages.MESSAGE_PROJECTS_LISTED_OVERVIEW,
                        expectedModel.getFilteredProjectList().size()));

        List<String> client = Arrays.asList("IsAStub");
        List<String> empty = new ArrayList<>();

        FindProjectCommand findName = new FindProjectCommand(new ProjectContainsKeywordsPredicate(empty, empty, client,
                empty, empty));

        assertCommandSuccess(findName, actualModel, expectedCommandResult, expectedModel, stubUi);

    }

    @Test
    public void testEquals() {

        List<String> projectName = Arrays.asList("DevEnable");
        List<String> repository = Arrays.asList("tp/F-13");
        List<String> projectId = Arrays.asList("3");
        List<String> clientLabel = Arrays.asList("Sally");
        List<String> clientId = Arrays.asList("1");
        List<String> empty = new ArrayList<>();

        FindProjectCommand findProjectCommandOne = new FindProjectCommand(
                new ProjectContainsKeywordsPredicate(projectName, repository, clientLabel, clientId, projectId));
        FindProjectCommand findIssueCommandOneCopy = new FindProjectCommand(
                new ProjectContainsKeywordsPredicate(projectName, repository, clientLabel, clientId, projectId));

        // same object -> returns true
        assertTrue(findProjectCommandOne.equals(findProjectCommandOne));

        // same values -> returns true
        assertTrue(findProjectCommandOne.equals(findIssueCommandOneCopy));

        // different types -> returns false
        assertFalse(findProjectCommandOne.equals(1));

        // null -> returns false
        assertFalse(findProjectCommandOne.equals(null));
    }
}

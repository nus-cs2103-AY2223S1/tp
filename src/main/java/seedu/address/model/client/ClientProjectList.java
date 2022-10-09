package seedu.address.model.client;

import seedu.address.model.project.Project;

import java.util.ArrayList;

import static java.util.Objects.requireNonNull;

/**
 * Represents the list of projects the client is associated with.
 */
public class ClientProjectList {

    ArrayList<Project> projects;

    /**
     * Constructs an empty project list.
     */
    public ClientProjectList() {
        this.projects = new ArrayList<Project>();
    }

    /**
     * Adds a project to the list.
     * @param project Project to be added.
     */
    public void addProject(Project project) {
        requireNonNull(project);
        this.projects.add(project);
    }

    /**
     * Removes a project from the list.
     * @param project Project to be removed.
     */
    public void removeProject(Project project) {
        this.projects.remove(project);
    }

    /**
     * Returns a list of all projects.
     * @return String representing all projects in the list
     */
    public String listAllProjects() {
        if(projects.size() > 0) {
            String list = this.projects.toArray().toString();
            return list;
        } else {
            return "none";
        }
    }

    /**
     * Returns the number of projects in the list
     * @return int representing number of projects in the list
     */
    public int getListLength() {
        return this.projects.size();
    }

    /**
     * Checks if list is empty.
     * @return true if list is empty
     */
    public boolean isEmpty() {
        return projects.size() == 0;
    }


}

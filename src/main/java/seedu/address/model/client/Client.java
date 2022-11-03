package seedu.address.model.client;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.Name;
import seedu.address.model.Pin;
import seedu.address.model.SortCategory;
import seedu.address.model.interfaces.ComparableByName;
import seedu.address.model.interfaces.HasIntegerIdentifier;
import seedu.address.model.project.Project;

/**
 * Represents a Client associated with a project. This is modelled after the AB3 Person.
 */
public class Client implements ComparableByName<Client>, HasIntegerIdentifier<Client> {

    public static final String MESSAGE_INVALID_NAME_SORT_KEY =
            "Enter either a 0 to sort by alphabetical order or a 1 to sort by reverse alphabetical order";

    public static final String MESSAGE_INVALID_CLIENT_ID_SORT_KEY =
            "Enter either a 0 to sort in ascending order or a 1 to sort in descending order";

    private static SortCategory sortCategory = SortCategory.ID;
    private static int sortOrder = 0;

    //Represents the Client's name
    private Name name;

    //Represents the Client's ClientEmail
    private ClientEmail email;

    //Represents the Client's ClientMobile
    private ClientMobile mobile;

    //Represents a Collection of projects that the client is responsible for
    private List<Project> projects;

    private ClientId clientId;
    private Pin pin;

    /**
     * Constructs a client with inputs given by the user.
     * @param name String representing name of the client
     * @param mobile String representing mobile number of the client
     * @param email String representing email address of the client
     */
    public Client(Name name, ClientMobile mobile, ClientEmail email, List<Project> projects,
                  ClientId clientId, Pin pin) {
        requireAllNonNull(name, mobile, email, clientId, pin);
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.projects = projects;
        this.clientId = clientId;
        this.pin = pin;
    }

    /**
     * Constructs a client with inputs given by the user.
     * @param name String representing name of the client
     */
    public Client(Name name) {
        requireAllNonNull(name);
        this.name = name;
        this.mobile = ClientMobile.EmptyClientMobile.EMPTY_MOBILE;
        this.email = ClientEmail.EmptyEmail.EMPTY_EMAIL;
        this.projects = new ArrayList<>();
        this.clientId = ClientId.EmptyClientId.EMPTY_CLIENT_ID;
        this.pin = new Pin(false);
    }

    /**
     * Sets the client name to be as user input.
     * @param name Name representing the new name of client
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * Sets the client email to be as user input.
     * @param email ClientEmail representing the new email of client
     */
    public void setEmail(ClientEmail email) {
        this.email = email;
    }

    /**
     * Sets the client mobile to be as user input.
     * @param mobile ClientMobile representing the new mobile of client
     */
    public void setMobile(ClientMobile mobile) {
        this.mobile = mobile;
    }

    public ClientId getClientId() {
        return this.clientId;
    }

    public int getClientIdInInt() {
        return getClientId().getIdInt();
    }

    /**
     * Checks if this Client is empty.
     * @return true if the Client is empty.
     */
    public boolean isEmpty() {
        return false;
    }

    public int getProjectListSize() {
        return this.projects.size();
    }

    @Override
    public int getId() {
        return this.getClientId().getIdInt();
    }

    /**
     * Checks if this client has a valid id.
     */
    public boolean hasValidId() {
        return this.clientId.isValid();
    }

    /**
     * Represents an Empty Client.
     */
    public static class EmptyClient extends Client {
        public static final Client EMPTY_CLIENT = new EmptyClient();
        private EmptyClient() {
            super(Name.EmptyName.EMPTY_NAME);
        }

        /**
         * Checks if this Client is empty.
         * @return true if the Client is empty.
         */
        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public String toString() {
            return "";
        }

    }


    /**
     * Checks if input is a valid client id sort key.
     *
     * 0 for ascending and 1 for descending order
     *
     * @param num input param to validate
     * @return true if input is a 0 or 1
     */
    public static boolean isValidClientIdSortKey(String num) {
        try {
            int number = Integer.parseInt(num);
            return number == 0 || number == 1;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if input is a valid name sort key.
     *
     * 0 for alphabetical order and 1 for reverse alphabetical order
     *
     * @param num input param to validate
     * @return true if input is a 0 or 1
     */
    public static boolean isValidNameSortKey(String num) {
        try {
            int number = Integer.parseInt(num);
            return number == 0 || number == 1;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns the client name as is represented in the Name object.
     * @return String representing client's name.
     */
    public seedu.address.model.Name getClientName() {
        return this.name;
    }

    /**
     * Returns the client email as is represented in the ClientEmail object.
     * @return String representing client's email.
     */
    public ClientEmail getClientEmail() {
        return this.email;
    }

    /**
     * Returns the client mobile as is represented in the ClientMobile object.
     * @return String representing client's mobile.
     */
    public ClientMobile getClientMobile() {
        return this.mobile;
    }

    /**
     * Returns the list of projects under the client.
     * @return String representing client's mobile.
     */
    public List<Project> getProjects() {
        return this.projects;
    }

    /**
     * Add A project to the client's project list.
     */
    public void addProjects(Project project) {
        projects.add(project);
    }

    /**
     * Returns the string for display in the UI
     *
     * @return String for display in the UI
     */
    public String uiRepresentation() {
        return this.name.toString() + " (" + this.mobile.toString() + ")";
    }

    public void removeProject(Project p) {
        this.projects.remove(p);
    }

    public void togglePin() {
        this.pin.togglePinned();
    }

    public boolean isPinned() {
        return this.pin.isPinned();
    }

    public static SortCategory getSortCategory() {
        return sortCategory;
    }

    public static int getSortOrder() {
        return sortOrder;
    }

    public static void setSortCategory(SortCategory newSortCategory) {
        sortCategory = newSortCategory;
    }

    public static void setSortOrder(int newSortOrder) {
        sortOrder = newSortOrder;
    }


    /**
     * Returns true if both clients have the same name.
     * This defines a weaker notion of equality between two clients.
     */
    @Override
    public boolean hasSameName(Client otherClient) {
        if (otherClient == this) {
            return true;
        }

        return otherClient != null
                && otherClient.getClientName().equals(getClientName());
    }

    /**
     * Returns true if client is valid and exists.
     */
    public static boolean isValidClient(Client client) {
        if (client == EmptyClient.EMPTY_CLIENT) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if all other attributes besides project list are the same.
     * @param otherClient
     * @return Boolean value representing whether the basic client details are equal.
     */
    public boolean hasSameDetails(Client otherClient) {
        return this.getClientId().equals(otherClient.getClientId())
                && this.getClientName().equals(otherClient.getClientName())
                && this.getClientEmail().equals(otherClient.getClientEmail())
                && this.getClientMobile().equals(otherClient.getClientMobile())
                && this.getPin().equals(otherClient.getPin());
    }

    @Override
    public String toString() {
        return this.name.getFullNameRepresentation();
    }

    /**
     * Checks if an object equals this.
     * @param other Object to be checked
     * @return boolean true if this is equal to other and false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof Client) {
            Client otherClient = (Client) other;
            boolean hasSameId = this.getClientId().equals(otherClient.getClientId());
            boolean hasSameName = this.getClientName().equals(otherClient.getClientName());
            boolean hasSameEmail = this.getClientEmail().equals(otherClient.getClientEmail());
            boolean hasSameMobile = this.getClientMobile().equals(otherClient.getClientMobile());
            boolean hasSamePin = this.getPin().equals(otherClient.getPin());
            return hasSameId && hasSameEmail && hasSameMobile && hasSameName && hasSamePin;
        } else {
            return false;
        }
    }

    private Pin getPin() {
        return this.pin;
    }
}

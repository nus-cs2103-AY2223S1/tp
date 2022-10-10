package longtimenosee.model.client;

import java.util.Objects;
import java.util.Set;

import longtimenosee.model.person.Address;
import longtimenosee.model.person.Email;
import longtimenosee.model.person.Name;
import longtimenosee.model.person.Person;
import longtimenosee.model.person.Phone;
import longtimenosee.model.tag.Tag;

//import statement for policies?



/**
 * Main client class handled by person
 */

public class Client extends Person {

    //private ArrayList<Policy> policies;
    private Birthday birthday; //TODO: Birthday class
    //private ArrayList<Event> events; // TODO: EventList class
    //private PersonNotes notes; //TODO: Just a string
    private Income income; //TODO: Just a float
    private RiskAppetite ra; //TODO Enum
    //The age is obtained from birthday


    /**
     * Encapsulation of a Client class
     * @param name
     * @param phone
     * @param email
     * @param address
     * @param tag
     * @param birthday
     * @param income
     * @param ra
     */
    public Client(Name name, Phone phone, Email email, Address address, Set<Tag> tag,
                  Birthday birthday, Income income, RiskAppetite ra) {
        super(name, phone, email, address, tag);
        this.birthday = birthday;
        this.income = income;
        this.ra = ra;
    }



    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Client)) {
            return false;
        }

        Client otherclient = (Client) other;
        return otherclient.getName().equals(getName())
                && otherclient.getPhone().equals(getPhone())
                && otherclient.getEmail().equals(getEmail())
                && otherclient.getAddress().equals(getAddress())
                && otherclient.getTags().equals(getTags())
                && otherclient.getBirthday().equals(getBirthday())
                && otherclient.getRiskAppetite().equals(getRiskAppetite())
                && otherclient.getIncome().equals(getIncome());
        //TODO: Add the remaining equals!
    }

    public Birthday getBirthday() {
        return this.birthday;
    }

    public RiskAppetite getRiskAppetite() {
        return this.ra;
    }

    public Income getIncome() {
        return this.income;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(super.toString()) //tag on previous stuff
                .append(", Birthday: ")
                .append(getBirthday())
                .append(", Income: ")
                .append(getIncome())
                .append(", RiskLevel: ")
                .append(getRiskAppetite());
        return sb.toString();
    }

    /**
     * Returns true if both clients have the same name.
     * This defines a weaker notion of equality between two clients.
     */
    public boolean isSameClient(Client otherClient) {
        if (otherClient == this) {
            return true;
        }

        return otherClient != null
                && otherClient.getName().equals(getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getPhone(), this.getEmail(), this.getAddress(), this.getTags(),
                this.getBirthday(), this.getIncome(), this.getRiskAppetite());
    }
}

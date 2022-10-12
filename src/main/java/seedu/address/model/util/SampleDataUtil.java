package seedu.address.model.util;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.order.*;
import seedu.address.model.person.*;
import seedu.address.model.pet.*;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Buyer[] getSampleBuyers() {
        return new Buyer[] {
            new Buyer(PersonCategory.BUYER, new Name("This Is BuyerList"), new Phone("87438807"),
                    new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
                    getTagSet("friends"), new ArrayList<Order>()),
            new Buyer(PersonCategory.BUYER, new Name("Bernice Yu"), new Phone("99272758"),
                    new Email("berniceyu@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getTagSet("colleagues", "friends"), new ArrayList<>()),
            new Buyer(PersonCategory.BUYER, new Name("Charlotte Oliveiro"), new Phone("93210283"),
                    new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getTagSet("neighbours"), new ArrayList<>()),
            new Buyer(PersonCategory.BUYER, new Name("David Li"), new Phone("91031282"),
                    new Email("lidavid@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getTagSet("family"), new ArrayList<>()),
            new Buyer(PersonCategory.BUYER, new Name("Irfan Ibrahim"), new Phone("92492021"),
                    new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                    getTagSet("classmates"), new ArrayList<>()),
            new Buyer(PersonCategory.BUYER, new Name("Roy Balakrishnan"), new Phone("92624417"),
                    new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getTagSet("colleagues"), new ArrayList<Order>())
        };
    }

    public static Supplier[] getSampleSuppliers() {
        return new Supplier[]{
            new Supplier(PersonCategory.SUPPLIER, new Name("This Is SupplierList"), new Phone("87438807"),
                    new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
                    getTagSet("friends"), null),
            new Supplier(PersonCategory.SUPPLIER, new Name("Bernice Yu"), new Phone("99272758"),
                    new Email("berniceyu@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getTagSet("colleagues", "friends"), null),
            new Supplier(PersonCategory.SUPPLIER, new Name("Charlotte Oliveiro"), new Phone("93210283"),
                    new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getTagSet("neighbours"), null),
            new Supplier(PersonCategory.SUPPLIER, new Name("David Li"), new Phone("91031282"),
                    new Email("lidavid@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getTagSet("family"), null),
            new Supplier(PersonCategory.SUPPLIER, new Name("Irfan Ibrahim"), new Phone("92492021"),
                    new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                    getTagSet("classmates"), null),
            new Supplier(PersonCategory.SUPPLIER, new Name("Roy Balakrishnan"), new Phone("92624417"),
                    new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getTagSet("colleagues"), null)
        };
    }

    public static Deliverer[] getSampleDeliverers() {
        return new Deliverer[] {
                new Deliverer(PersonCategory.DELIVERER, new Name("ThisIs DelivererList"), new Phone("87438807"),
                        new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
                        getTagSet("friends"), null),
                new Deliverer(PersonCategory.DELIVERER, new Name("Bernice Yu"), new Phone("99272758"),
                        new Email("berniceyu@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                        getTagSet("colleagues", "friends"), null),
                new Deliverer(PersonCategory.DELIVERER, new Name("Charlotte Oliveiro"), new Phone("93210283"),
                        new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                        getTagSet("neighbours"), null),
                new Deliverer(PersonCategory.DELIVERER, new Name("David Li"), new Phone("91031282"),
                        new Email("lidavid@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                        getTagSet("family"), null),
                new Deliverer(PersonCategory.DELIVERER, new Name("Irfan Ibrahim"), new Phone("92492021"),
                        new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                        getTagSet("classmates"), null),
                new Deliverer(PersonCategory.DELIVERER, new Name("Roy Balakrishnan"), new Phone("92624417"),
                        new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                        getTagSet("colleagues"), null)
        };
    }

    public static Order[] getSampleOrders() {
        return new Order[] {
                getOrder(10.0, 15.0,
                        getRequest(2, "white", "black", "pokemon"),
                        getAdditionalRequests("1", "2", "3"),
                        "2022-12-21", 12.5, OrderStatus.PENDING.toString()),
                getOrder(12.0, 15.0,
                        getRequest(3, "white", "black", "monster"),
                        getAdditionalRequests("1", "2", "3"),
                        "2022-12-21", 12.5, OrderStatus.PENDING.toString()),
                getOrder(10.2, 15.0,
                        getRequest(2, "white", "black", "pokemon"),
                        getAdditionalRequests("1", "2", "3"),
                        "2022-12-21", 12.5, OrderStatus.PENDING.toString()),
                getOrder(10.3, 15.0,
                        getRequest(2, "white", "black", "pokemon"),
                        getAdditionalRequests("1", "2", "3"),
                        "2022-12-21", 12.5, OrderStatus.PENDING.toString()),
                getOrder(10.1, 15.0,
                        getRequest(2, "white", "black", "pokemon"),
                        getAdditionalRequests("1", "2", "3"),
                        "2022-12-21", 12.5, OrderStatus.PENDING.toString()),
                getOrder(11110.0, 15.0,
                        getRequest(2, "white", "black", "pokemon"),
                        getAdditionalRequests("i need it now"),
                        "2022-12-21", 12.5, OrderStatus.PENDING.toString())

        };
    }

    public static Pet[] getSamplePets() {
        Supplier elizabeth = new Supplier(PersonCategory.SUPPLIER, new Name("Bernice Yu"), new Phone("99272758"),
                new Email("berniceyu@example.com"), new Address("somewhere in choa chu kang"),
                getTagSet("colleagues", "friends"), null);

        DateOfBirth standard;

        try {
            standard = DateOfBirth.parseString("2020-10-10");
        } catch (IllegalValueException e) {
            return null;
        }

        return new Pet[] {
                new Pet(new Name("This is sample Pet List"), elizabeth, new Color("white"), new ColorPattern("white and brown"),
                        standard, new Species("cat"), new Weight(10.05),
                        new Height(100.5), new VaccinationStatus(true), getTagSet("cat"),
                        new HashSet<>()),
                new Pet(new Name("Ashy"), elizabeth, new Color("white"), new ColorPattern("white and brown"),
                        standard, new Species("cat"), new Weight(10.05),
                        new Height(100.5), new VaccinationStatus(true), getTagSet("cat"),
                        new HashSet<>()),
                new Pet(new Name("Plum"), elizabeth, new Color("white"), new ColorPattern("white and brown"),
                        standard, new Species("cat"), new Weight(10.05),
                        new Height(100.5), new VaccinationStatus(true), getTagSet("cat"),
                        new HashSet<>())
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();

        Buyer[] buyers = getSampleBuyers();
        Order[] orders = getSampleOrders();

        for (int i = 0; i < buyers.length; i++) {
            buyers[i].addOrder(orders[i]);
            orders[i].setBuyer(buyers[i]);
        }

        for (Buyer sampleBuyer :buyers) {
            sampleAb.addBuyer(sampleBuyer);
        }
        for (Supplier sampleSupplier : getSampleSuppliers()) {
            sampleAb.addSupplier(sampleSupplier);
        }
        for (Deliverer sampleDeliverer : getSampleDeliverers()) {
            sampleAb.addDeliverer(sampleDeliverer);
        }
        for (Order sampleOrder : orders) {
            System.out.println("order " + sampleOrder);
            sampleAb.addOrder(sampleOrder);
        }

        for (Pet samplePet : getSamplePets()) {
            System.out.println("pet " + samplePet);
            sampleAb.addPet(samplePet);
        }

        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    public static Order getOrder(double upperBound, double lowerBound, Request request,
                                 AdditionalRequests additionalRequests, String byDate,
                                 double settledPrice, String status) {
        PriceRange priceRange = new PriceRange(new Price(upperBound), new Price(lowerBound));
        Request orderRequest = request;
        AdditionalRequests orderAdditionalRequests = additionalRequests;
        LocalDate orderDate = LocalDate.parse(byDate);
        Price orderSettledPrice = new Price(settledPrice);
        OrderStatus orderStatus = Arrays.stream(OrderStatus.class.getEnumConstants())
                .filter(x -> x.toString().equals(status))
                .findFirst()
                .orElse(OrderStatus.PENDING);
        return new Order(null, priceRange, orderRequest, orderAdditionalRequests, orderDate, orderSettledPrice,
                    orderStatus);
    }

    public static Request getRequest(int age, String color, String colorPattern, String species) {
        Age modelAge = new Age(age);
        Color modelColor = new Color(color);
        ColorPattern modelColorPattern = new ColorPattern(colorPattern);
        Species modelSpecies = new Species(species);
        return new Request(modelAge, modelColor, modelColorPattern, modelSpecies);
    }

    public static AdditionalRequests getAdditionalRequests(String... strings) {
        return new AdditionalRequests(strings);
    }

}

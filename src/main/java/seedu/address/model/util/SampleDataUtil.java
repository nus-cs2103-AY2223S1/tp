package seedu.address.model.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.index.UniqueId;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.order.AdditionalRequests;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderStatus;
import seedu.address.model.order.Price;
import seedu.address.model.order.PriceRange;
import seedu.address.model.order.Request;
import seedu.address.model.person.Address;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.Email;
import seedu.address.model.person.Location;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Supplier;
import seedu.address.model.pet.Age;
import seedu.address.model.pet.Color;
import seedu.address.model.pet.ColorPattern;
import seedu.address.model.pet.DateOfBirth;
import seedu.address.model.pet.Height;
import seedu.address.model.pet.Pet;
import seedu.address.model.pet.PetCertificate;
import seedu.address.model.pet.PetName;
import seedu.address.model.pet.Species;
import seedu.address.model.pet.VaccinationStatus;
import seedu.address.model.pet.Weight;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    //Buyers
    public static final Buyer ALEX = new Buyer(new Name("Alex"),
            new Phone("87438807"), new Email("alexyeoh@gmail.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
            new Location("Singapore"), new ArrayList<>());
    public static final Buyer BERNICE = new Buyer(new Name("Bernice Yu"), new Phone("99272758"),
            new Email("berniceyu@firefox.com"), new Address("4/6 Huangpu Ave, Guangzhou"),
            new Location("China"), new ArrayList<>());
    public static final Buyer CHARLOTTE = new Buyer(new Name("Charlotte Oliveiro"),
            new Phone("93210283"), new Email("111charlotte@outlook.com"),
            new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
            new Location("Singapore"), new ArrayList<>());
    public static final Buyer DAVID = new Buyer(new Name("David Li"), new Phone("91031282"),
            new Email("lidav@outlook.com"), new Address("No 3 Burke Street 26, Victoria"),
            new Location("Australia"), new ArrayList<>());
    public static final Buyer IRFAN = new Buyer(new Name("Irfan Ibrahim"), new Phone("92492021"),
            new Email("irfan1989@firefox.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
            new Location("Singapore"), new ArrayList<>());
    public static final Buyer ROY = new Buyer(new Name("Roy Balakrishnan"), new Phone("92624417"),
            new Email("royb@yahoo.com"), new Address("Blk 45 Aljunied Street 85, #11-31"),
            new Location("Singapore"), new ArrayList<>());

    //Suppliers
    public static final Supplier SHIN_CHAN = new Supplier(new Name("ShinChan"),
            new Phone("09594177555"), new Email("Shin-chan@crayon.com"),
            new Address("The Nohara family household, Kasukabe"),
            new Location("Singapore"), new ArrayList<>());
    public static final Supplier CONG = new Supplier(new Name("Cong Xu"),
            new Phone("99272758"), new Email("cx973@gmail.com"),
            new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
            new Location("Singapore"), new ArrayList<>());
    public static final Supplier EMMA = new Supplier(new Name("Emma Ang"),
            new Phone("93210283"), new Email("e03389218@u.nus.edu"),
            new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
            new Location("Singapore"), new ArrayList<>());
    public static final Supplier NGUYEN = new Supplier(new Name("Nguyen Duc Hong"),
            new Phone("91031282"), new Email("ng4398@yandex.com"),
            new Address("998 Nguyen Puh Duc Street, Ho Chi Minh City"),
            new Location("Vietnam"), new ArrayList<>());
    public static final Supplier RAJ = new Supplier(new Name("Raj Pakashmala"),
            new Phone("92492021"), new Email("pak0raj@gmail.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
            new Location("Singapore"), new ArrayList<>());
    public static final Supplier SMITH = new Supplier(new Name("Jack Smith"),
            new Phone("92624417"),
            new Email("jsmith@gmail.com"), new Address("10 Westminster Rd, Whitehall, West London"),
            new Location("UK"), new ArrayList<>());

    //Deliverers
    public static final Deliverer ADI = new Deliverer(new Name("Adi Karma"),
            new Phone("87438807"),
            new Email("adiiiii@gmail.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
            new Location("Singapore"), new ArrayList<>());
    public static final Deliverer MINGMING = new Deliverer(new Name("Mingming Sun"),
            new Phone("99272758"),
            new Email("mmmmsun@outlook.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
            new Location("Singapore"), new ArrayList<>());
    public static final Deliverer HAOLING = new Deliverer(new Name("Haoling Wang"),
            new Phone("93210283"),
            new Email("wang000@outlook.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
            new Location("Singapore"), new ArrayList<>());
    public static final Deliverer NOBEL = new Deliverer(new Name("Nobel Ong"),
            new Phone("91031282"),
            new Email("nobongong@u.nus.edu"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
            new Location("Singapore"), new ArrayList<>());
    public static final Deliverer YUSOF = new Deliverer(new Name("Yusof Ibrahim"),
            new Phone("92492021"),
            new Email("yusofff9066@outlook.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
            new Location("Singapore"), new ArrayList<>());
    public static final Deliverer FRANK = new Deliverer(new Name("Frank Appleby"),
            new Phone("92624417"),
            new Email("ffapp@outlook.com"), new Address("Blk 45 Aljunied Street 85, #11-31"),
            new Location("Singapore"), new ArrayList<>());

    //Orders
    public static final Order O1 = getOrder(ALEX, 50.0, 70.5,
            getRequest(2, "white", "black", "pokemon"),
            getAdditionalRequests("1", "2", "3"),
            "2022-12-21", 80, OrderStatus.PENDING.toString());
    public static final Order O2 = getOrder(BERNICE, 10.2, 15.0,
            getRequest(2, "white", "black", "pokemon"),
            getAdditionalRequests("1", "2", "3"),
            "2022-12-21", 12.5, OrderStatus.PENDING.toString());
    public static final Order O3 = getOrder(CHARLOTTE, 10.3, 15.0,
            getRequest(2, "white", "black", "pokemon"),
            getAdditionalRequests("1", "2", "3"),
            "2022-12-21", 12.5, OrderStatus.PENDING.toString());
    public static final Order O4 = getOrder(DAVID, 10.1, 15.0,
            getRequest(2, "white", "black", "pokemon"),
            getAdditionalRequests("1", "2", "3"),
            "2022-12-21", 12.5, OrderStatus.PENDING.toString());
    public static final Order O5 = getOrder(IRFAN, 11110.0, 15.0,
            getRequest(2, "white", "black", "pokemon"),
            getAdditionalRequests("i need it now"),
            "2022-12-21", 12.5, OrderStatus.PENDING.toString());
    public static final Order O6 = getOrder(ROY, 11111.0, 15.0,
            getRequest(2, "white", "black", "pokemon"),
            getAdditionalRequests("i need it now"),
            "2022-12-21", 12.5, OrderStatus.PENDING.toString());

    //Pets
    private static final DateOfBirth standard = getStandard();
    public static final Pet P1 = new Pet(new PetName("Shiro"), SHIN_CHAN, new Color("white"),
            new ColorPattern("white"),
            standard, new Species("dog"), new Weight(10.05),
            new Height(100.5), new VaccinationStatus(true),
            new Price(9999.99), new HashSet<>());
    public static final Pet P2 = new Pet(new PetName("Ashy"), CONG, new Color("white"),
            new ColorPattern("white and brown"),
            standard, new Species("cat"), new Weight(10.05),
            new Height(100.5), new VaccinationStatus(true),
            new Price(8888.88), new HashSet<>());
    public static final Pet P3 = new Pet(new PetName("Plum"), EMMA, new Color("white"),
            new ColorPattern("white and brown"),
            standard, new Species("cat"), new Weight(10.05),
            new Height(100.5), new VaccinationStatus(true),
            new Price(7777.77), new HashSet<>());
    public static final Pet P4 = new Pet(new PetName("Page"), NGUYEN, new Color("white"),
            new ColorPattern("white and brown"),
            standard, new Species("cat"), new Weight(10.05),
            new Height(100.5), new VaccinationStatus(true),
            new Price(7777.77), new HashSet<>());
    public static final Pet P5 = new Pet(new PetName("Snowy"), EMMA, new Color("white"),
            new ColorPattern("white and brown"),
            standard, new Species("cat"), new Weight(10.05),
            new Height(100.5), new VaccinationStatus(true),
            new Price(7777.77), new HashSet<>());
    public static final Pet P6 = new Pet(new PetName("Buddy"), EMMA, new Color("white"),
            new ColorPattern("white and brown"),
            standard, new Species("cat"), new Weight(10.05),
            new Height(100.5), new VaccinationStatus(true),
            new Price(7777.77), new HashSet<>());


    public static Buyer[] getSampleBuyers() {
        return new Buyer[] {ALEX, BERNICE, CHARLOTTE, DAVID, IRFAN, ROY};
    }

    public static Supplier[] getSampleSuppliers() {
        return new Supplier[]{SHIN_CHAN, CONG, EMMA, NGUYEN, RAJ, SMITH};
    }

    public static Deliverer[] getSampleDeliverers() {
        return new Deliverer[] {ADI, MINGMING, HAOLING, NOBEL, YUSOF, FRANK};
    }

    public static Order[] getSampleOrders() {
        return new Order[] {O1, O2, O3, O4, O5, O6};
    }

    public static Pet[] getSamplePets() {
        return new Pet[] {P1, P2, P3, P4, P5, P6};
    }

    private static DateOfBirth getStandard() {
        try {
            return DateOfBirth.parseString("2020-10-10");
        } catch (IllegalValueException e) {
            throw new RuntimeException(e);
        }
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();

        Buyer[] buyers = getSampleBuyers();
        Supplier[] suppliers = getSampleSuppliers();
        Order[] orders = getSampleOrders();
        Pet[] pets = getSamplePets();

        for (int i = 0; i < buyers.length; i++) {
            List<UniqueId> tmp = new ArrayList<>();
            tmp.add(orders[i].getId());
            buyers[i].addOrders(tmp);
        }

        for (int i = 0; i < suppliers.length; i++) {
            List<UniqueId> tmp = new ArrayList<>();
            tmp.add(pets[i].getId());
            suppliers[i].addPets(tmp);
        }

        for (Buyer sampleBuyer :buyers) {
            sampleAb.addBuyer(sampleBuyer);
        }
        for (Supplier sampleSupplier : suppliers) {
            sampleAb.addSupplier(sampleSupplier);
        }
        for (Deliverer sampleDeliverer : getSampleDeliverers()) {
            sampleAb.addDeliverer(sampleDeliverer);
        }
        for (Order sampleOrder : orders) {
            sampleAb.addOrder(sampleOrder);
        }

        for (Pet samplePet : getSamplePets()) {
            sampleAb.addPet(samplePet);
        }

        return sampleAb;
    }

    /**
     * Returns a certificate set containing the list of strings given.
     */
    public static Set<PetCertificate> getCertificateSet(String... strings) {
        return Arrays.stream(strings)
                .map(PetCertificate::new)
                .collect(Collectors.toSet());
    }

    public static Order getOrder(Buyer buyer, double upperBound, double lowerBound, Request request,
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
        return new Order(buyer, priceRange, orderRequest, orderAdditionalRequests, orderDate, orderSettledPrice,
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

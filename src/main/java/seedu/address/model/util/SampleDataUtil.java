package seedu.address.model.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;

import seedu.address.MainApp;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.commission.Commission;
import seedu.address.model.commission.CompletionStatus;
import seedu.address.model.commission.Deadline;
import seedu.address.model.commission.Fee;
import seedu.address.model.commission.Title;
import seedu.address.model.customer.Address;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.Email;
import seedu.address.model.customer.Name;
import seedu.address.model.customer.Phone;
import seedu.address.model.iteration.Date;
import seedu.address.model.iteration.Feedback;
import seedu.address.model.iteration.Iteration;
import seedu.address.model.iteration.IterationDescription;
import seedu.address.model.tag.Tag;
import seedu.address.storage.Storage;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    private static final Random random = new Random();
    private static final String PLACEHOLDER_IMAGE_PATH = "/images/placeholderart.png";

    private static final double DEFAULT_FEE_LIMIT = 20;
    private static final int DEFAULT_COMMISSIONS_PER_CUSTOMER = 3;
    private static final int DEFAULT_ITERATIONS_PER_COMMISSION = 3;

    public static Customer[] getSampleCustomers(Storage storage) {
        Customer[] customers = new Customer[] {
            new Customer.CustomerBuilder(new Name("Alex Yeoh"), new Phone("87438807"),
                    new Email("alexyeoh@example.com"), getTagSet("friends"))
                    .setAddress(new Address("Blk 30 Geylang Street 29, #06-40")).build(),
            new Customer.CustomerBuilder(new Name("Bernice Yu"), new Phone("99272758"),
                    new Email("berniceyu@example.com"), getTagSet("colleagues", "friends"))
                    .setAddress(new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18")).build(),
            new Customer.CustomerBuilder(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                    new Email("charlotte@example.com"), getTagSet("neighbours"))
                    .setAddress(new Address("Blk 11 Ang Mo Kio Street 74, #11-04")).build(),
            new Customer.CustomerBuilder(new Name("David Li"), new Phone("91031282"),
                    new Email("lidavid@example.com"), getTagSet("family"))
                    .setAddress(new Address("Blk 436 Serangoon Gardens Street 26, #16-43")).build(),
            new Customer.CustomerBuilder(new Name("Irfan Ibrahim"), new Phone("92492021"),
                    new Email("irfan@example.com"), getTagSet("classmates"))
                    .setAddress(new Address("Blk 47 Tampines Street 20, #17-35")).build(),
            new Customer.CustomerBuilder(new Name("Roy Balakrishnan"), new Phone("92624417"),
                    new Email("royb@example.com"), getTagSet("colleagues"))
                    .setAddress(new Address("Blk 45 Aljunied Street 85, #11-31")).build()
        };


        try {
            for (Customer customer: customers) {
                for (int i = 1; i <= DEFAULT_COMMISSIONS_PER_CUSTOMER; i++) {
                    addCommissionToCustomer(customer, storage, i);
                }
            }
        } catch (IOException e) {
            // shouldn't happen
            System.out.println("Failed to load placeholder image.");
            e.printStackTrace();
        }

        return customers;
    }

    /**
     * Adds a sample commission with iterations to a customer. Number of iterations are defined in
     * DEFAULT_ITERATIONS_PER_COMMISSION.
     * @param customer Customer to add commissions to.
     * @param storage Storage to save placeholder images to for a commission's iterations.
     * @param index Index of the commission.
     * @throws IOException If the placeholder image cannot be found.
     */
    public static void addCommissionToCustomer(Customer customer, Storage storage, int index) throws IOException {
        Commission commission = new Commission.CommissionBuilder(
                new Title(customer.getName().fullName + " Commission " + index),
                new Fee(random.nextDouble() * DEFAULT_FEE_LIMIT),
                new Deadline(LocalDate.now()),
                new CompletionStatus(random.nextBoolean()),
                new HashSet<>()).build(customer);

        BufferedImage placeholderImage = ImageIO.read(MainApp.class.getResourceAsStream(PLACEHOLDER_IMAGE_PATH));
        for (int j = 1; j <= DEFAULT_ITERATIONS_PER_COMMISSION; j++) {
            Path imageCopyPath = storage.getRandomImagePath();
            storage.saveImage(placeholderImage, imageCopyPath);
            Iteration iteration = new Iteration(
                    new Date(LocalDate.now()),
                    new IterationDescription("iteration description " + j),
                    imageCopyPath,
                    new Feedback("feedback " + j)
            );
            commission.addIteration(iteration);
        }
        customer.addCommission(commission);
    }

    public static ReadOnlyAddressBook getSampleAddressBook(Storage storage) {
        AddressBook sampleAb = new AddressBook();
        for (Customer sampleCustomer : getSampleCustomers(storage)) {
            sampleAb.addCustomer(sampleCustomer);
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
}

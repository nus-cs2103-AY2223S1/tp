package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.index.Index.MESSAGE_USAGE;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_ADDITIONAL_REQUESTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_COLOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_COLOR_PATTERN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_PRICE_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_REQUESTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_SPECIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_CERTIFICATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_COLOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_COLOR_PATTERN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_DATE_OF_BIRTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_SPECIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_VACCINATION_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PET_WEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.model.ModelManager.ACCEPTABLE_DATE_FORMATS;
import static seedu.address.model.ModelManager.PREFERRED_DATE_FORMAT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.addcommands.AddBuyerCommand;
import seedu.address.logic.commands.addcommands.AddDelivererCommand;
import seedu.address.logic.commands.addcommands.AddOrderCommand;
import seedu.address.logic.commands.addcommands.AddPersonCommand;
import seedu.address.logic.commands.addcommands.AddPetCommand;
import seedu.address.logic.commands.addcommands.AddSupplierCommand;
import seedu.address.logic.parser.exceptions.ParseException;
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
import seedu.address.model.person.PersonCategory;
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
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    /*
     * The first character of the additional request must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    private static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private static final Logger logger = LogsCenter.getLogger(ParserUtil.class);
    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        logger.fine("Parsing index: " + oneBasedIndex);
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_USAGE);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        logger.fine("Parsing name: " + name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        logger.fine("Parsing phone: " + phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        logger.fine("Parsing address: " + address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        logger.fine("Parsing email: " + email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String location} into an {@code Location}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code location} is invalid.
     */
    public static Location parseLocation(String location) throws ParseException {
        requireNonNull(location);
        logger.fine("Parsing location: " + location);
        String trimmedLocation = location.trim();
        if (!isInputAlphanumericAndNotBlank(trimmedLocation)) {
            throw new ParseException(Location.MESSAGE_CONSTRAINTS);
        }
        return new Location(trimmedLocation);
    }

    /**
     * Parses a {@code String args} into an {@code Person (Buyer, Deliverer, or Supplier}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code orderString} is invalid.
     */
    public static AddPersonCommand parseAddPersonCommand(String args, PersonCategory category) throws ParseException {
        requireAllNonNull(args, category);

        logger.fine("Parsing person: " + args + " category: " + category);

        ArgumentMultimap argMultimap = getPersonAttributes(args, category);

        Name name = parseName(argMultimap.getValue(PREFIX_NAME).orElse(""));
        Phone phone = parsePhone(argMultimap.getValue(PREFIX_PHONE).orElse(""));
        Email email = parseEmail(argMultimap.getValue(PREFIX_EMAIL).orElse(""));
        Address address = parseAddress(argMultimap.getValue(PREFIX_ADDRESS).orElse(""));
        Location location = parseLocation(argMultimap.getValue(PREFIX_LOCATION).orElse(""));

        switch (category) {
        case BUYER:
            Buyer buyer = new Buyer(name, phone, email, address, location, null);
            List<Order> orders = parseOrders(argMultimap.getAllValues(PREFIX_ORDER), false);
            return new AddBuyerCommand(buyer, orders);

        case DELIVERER:
            Deliverer deliverer = new Deliverer(name, phone, email, address, location, null);
            return new AddDelivererCommand(deliverer);

        case SUPPLIER:
            Supplier supplier = new Supplier(name, phone, email, address, location, null);
            List<Pet> pets = parsePets(argMultimap.getAllValues(PREFIX_PET), false);
            return new AddSupplierCommand(supplier, pets);

        default:
            // There are only three enum constants
            break;
        }
        return null;
    }

    /**
     * Parses a {@code String orderString} into an {@code Order}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code orderString} is invalid.
     */
    public static Order parseOrder(String orderString, boolean isBuyerExisting) throws ParseException {
        requireNonNull(orderString);
        logger.fine("Parsing order: " + orderString);
        String trimmedOrderString = orderString.trim();
        ArgumentMultimap argMultimap;

        if (isBuyerExisting) {
            argMultimap =
                    ArgumentTokenizer.tokenize(trimmedOrderString,
                            PREFIX_INDEX, // The difference is here.
                            PREFIX_ORDER_STATUS,
                            PREFIX_ORDER_REQUESTS,
                            PREFIX_ORDER_PRICE,
                            PREFIX_ORDER_PRICE_RANGE,
                            PREFIX_ORDER_ADDITIONAL_REQUESTS,
                            PREFIX_ORDER_DATE);
            if (!arePrefixesPresent(argMultimap,
                    PREFIX_INDEX, // The difference is here.
                    PREFIX_ORDER_STATUS,
                    PREFIX_ORDER_REQUESTS,
                    PREFIX_ORDER_PRICE,
                    PREFIX_ORDER_PRICE_RANGE,
                    PREFIX_ORDER_DATE)) {
                throw new ParseException(AddOrderCommand.MESSAGE_USAGE_EXISTING_BUYER);
            }
        } else {
            argMultimap =
                    ArgumentTokenizer.tokenize(trimmedOrderString,
                            PREFIX_ORDER_STATUS,
                            PREFIX_ORDER_REQUESTS,
                            PREFIX_ORDER_PRICE,
                            PREFIX_ORDER_PRICE_RANGE,
                            PREFIX_ORDER_ADDITIONAL_REQUESTS,
                            PREFIX_ORDER_DATE);
            if (!arePrefixesPresent(argMultimap,
                    PREFIX_ORDER_STATUS,
                    PREFIX_ORDER_REQUESTS,
                    PREFIX_ORDER_PRICE,
                    PREFIX_ORDER_PRICE_RANGE,
                    PREFIX_ORDER_DATE)) {
                throw new ParseException(AddOrderCommand.MESSAGE_USAGE_NEW_BUYER);
            }
        }

        PriceRange priceRange = parsePriceRange(argMultimap.getValue(PREFIX_ORDER_PRICE_RANGE).orElse(""));
        Request request = parseRequest(argMultimap.getValue(PREFIX_ORDER_REQUESTS).orElse(""));
        AdditionalRequests additionalRequests =
                parseAdditionalRequests(argMultimap.getAllValues(PREFIX_ORDER_ADDITIONAL_REQUESTS));
        LocalDate byDate = parseDate(argMultimap.getValue(PREFIX_ORDER_DATE).orElse(""));
        Price price = parsePrice(argMultimap.getValue(PREFIX_ORDER_PRICE).orElse(""));
        OrderStatus orderStatus = parseOrderStatus(argMultimap.getValue(PREFIX_ORDER_STATUS).orElse(""));

        return new Order(null, priceRange, request, additionalRequests, byDate, price, orderStatus);
    }

    /**
     * Parses {@code Collection<String> orders} into a {@code List<Order>}.
     */
    public static List<Order> parseOrders(Collection<String> orders, boolean isBuyerExisting) throws ParseException {
        requireNonNull(orders);
        final List<Order> orderList = new ArrayList<>();
        for (String order : orders) {
            orderList.add(parseOrder(order, isBuyerExisting));
        }
        return orderList;
    }

    /**
     * Parses {@code Collection<String> pets} into a {@code List<Pet>}.
     */
    public static List<Pet> parsePets(Collection<String> pets, boolean isSupplierExisting) throws ParseException {
        requireNonNull(pets);
        final List<Pet> petList = new ArrayList<>();
        for (String pet : pets) {
            petList.add(parsePet(pet, isSupplierExisting));
        }
        return petList;
    }

    /**
     * Parses a {@code String name} into a {@code PetName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static PetName parsePetName(String name) throws ParseException {
        requireNonNull(name);
        logger.fine("Parsing pet name: " + name);
        String trimmedName = name.trim();
        if (!PetName.isValidName(trimmedName)) {
            throw new ParseException(PetName.MESSAGE_CONSTRAINTS);
        }
        return new PetName(trimmedName);
    }

    /**
     * Parses a {@code String orderStatus} into an {@code OrderStatus}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code orderStatus} is invalid.
     */
    public static OrderStatus parseOrderStatus(String orderStatus) throws ParseException {
        requireNonNull(orderStatus);
        logger.fine("Parsing order status: " + orderStatus);
        String trimmedOrderStatus = orderStatus.trim();
        if (!OrderStatus.isValidOrderStatus(trimmedOrderStatus)) {
            throw new ParseException(OrderStatus.MESSAGE_CONSTRAINTS);
        }
        return Arrays
                .stream(OrderStatus.class.getEnumConstants())
                .filter(x -> x.toString().equals(trimmedOrderStatus))
                .findFirst()
                .orElse(OrderStatus.PENDING);
    }

    /**
     * Parses a {@code String request} into an {@code Request}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code request} is invalid.
     */
    public static Request parseRequest(String request) throws ParseException {
        requireNonNull(request);
        logger.fine("Parsing request: " + request);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(request,
                        PREFIX_ORDER_SPECIES,
                        PREFIX_ORDER_AGE,
                        PREFIX_ORDER_COLOR,
                        PREFIX_ORDER_COLOR_PATTERN);
        if (!arePrefixesPresent(argMultimap,
                PREFIX_ORDER_SPECIES,
                PREFIX_ORDER_AGE,
                PREFIX_ORDER_COLOR,
                PREFIX_ORDER_COLOR_PATTERN)) {
            throw new ParseException(Request.MESSAGE_USAGE);
        }

        Age age = parseAge(argMultimap.getValue(PREFIX_ORDER_AGE).orElse(""));
        Color color = parseColor(argMultimap.getValue(PREFIX_ORDER_COLOR).orElse(""));
        ColorPattern colorPattern = parseColorPattern(argMultimap.getValue(PREFIX_ORDER_COLOR_PATTERN).orElse(""));
        Species species = parseSpecies(argMultimap.getValue(PREFIX_ORDER_SPECIES).orElse(""));
        return new Request(age, color, colorPattern, species);
    }

    /**
     * Parses a {@code String petString} into an {@code Pet}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code petString} is invalid.
     */
    public static Pet parsePet(String petString, boolean isSupplierExisting) throws ParseException {

        requireNonNull(petString);

        logger.fine("Parsing pet: " + petString);

        ArgumentMultimap argMultimap;

        if (isSupplierExisting) {
            argMultimap =
                    ArgumentTokenizer.tokenize(petString,
                            PREFIX_INDEX, // The difference is here
                            PREFIX_PET_NAME,
                            PREFIX_PET_DATE_OF_BIRTH,
                            PREFIX_PET_COLOR,
                            PREFIX_PET_COLOR_PATTERN,
                            PREFIX_PET_HEIGHT,
                            PREFIX_PET_CERTIFICATE,
                            PREFIX_PET_SPECIES,
                            PREFIX_PET_VACCINATION_STATUS,
                            PREFIX_PET_PRICE,
                            PREFIX_PET_WEIGHT);
            if (!arePrefixesPresent(argMultimap,
                    PREFIX_INDEX, // The difference is here
                    PREFIX_PET_NAME,
                    PREFIX_PET_DATE_OF_BIRTH,
                    PREFIX_PET_COLOR,
                    PREFIX_PET_COLOR_PATTERN,
                    PREFIX_PET_HEIGHT,
                    PREFIX_PET_SPECIES,
                    PREFIX_PET_PRICE,
                    PREFIX_PET_WEIGHT)) {
                throw new ParseException(AddPetCommand.MESSAGE_USAGE_EXISTING_SUPPLIER);
            }
        } else {
            argMultimap =
                    ArgumentTokenizer.tokenize(petString,
                            PREFIX_PET_NAME,
                            PREFIX_PET_DATE_OF_BIRTH,
                            PREFIX_PET_COLOR,
                            PREFIX_PET_COLOR_PATTERN,
                            PREFIX_PET_HEIGHT,
                            PREFIX_PET_CERTIFICATE,
                            PREFIX_PET_SPECIES,
                            PREFIX_PET_VACCINATION_STATUS,
                            PREFIX_PET_PRICE,
                            PREFIX_PET_WEIGHT);
            if (!arePrefixesPresent(argMultimap,
                    PREFIX_PET_NAME,
                    PREFIX_PET_DATE_OF_BIRTH,
                    PREFIX_PET_COLOR,
                    PREFIX_PET_COLOR_PATTERN,
                    PREFIX_PET_HEIGHT,
                    PREFIX_PET_SPECIES,
                    PREFIX_PET_PRICE,
                    PREFIX_PET_WEIGHT)) {
                throw new ParseException(AddPetCommand.MESSAGE_USAGE_NEW_SUPPLIER);
            }
        }

        PetName name = parsePetName(argMultimap.getValue(PREFIX_PET_NAME).orElse(""));
        DateOfBirth dateOfBirth = parseDateOfBirth(argMultimap.getValue(PREFIX_PET_DATE_OF_BIRTH).orElse(""));
        Color color = parseColor(argMultimap.getValue(PREFIX_PET_COLOR).orElse(""));
        ColorPattern colorPattern = parseColorPattern(argMultimap.getValue(PREFIX_PET_COLOR_PATTERN).orElse(""));
        Height height = parseHeight(argMultimap.getValue(PREFIX_PET_HEIGHT).orElse(""));
        Set<PetCertificate> certificates = parseCertificates(argMultimap.getAllValues(PREFIX_PET_CERTIFICATE));
        Species species = parseSpecies(argMultimap.getValue(PREFIX_PET_SPECIES).orElse(""));
        Weight weight = parseWeight(argMultimap.getValue(PREFIX_PET_WEIGHT).orElse(""));
        Price price = parsePrice(argMultimap.getValue(PREFIX_PET_PRICE).orElse(""));
        VaccinationStatus vaccinationStatus =
                parseVaccinationStatus(argMultimap.getValue(PREFIX_PET_VACCINATION_STATUS).orElse("false"));

        return new Pet(name,
                null,
                color,
                colorPattern,
                dateOfBirth,
                species,
                weight,
                height,
                vaccinationStatus,
                price,
                certificates);
    }

    /**
     * Parses a {@code String price} into an {@code Price}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code price} is invalid.
     */
    public static Price parsePrice(String price) throws ParseException {
        requireNonNull(price);
        logger.fine("Parsing price: " + price);
        String trimmedPrice = price.trim();

        double doublePrice;
        try {
            doublePrice = Double.parseDouble(trimmedPrice);
        } catch (NumberFormatException ex) {
            throw new ParseException(Price.MESSAGE_USAGE);
        }

        Price toReturn = new Price(doublePrice);
        if ((!toReturn.isNotApplicablePrice()) && toReturn.compareTo(new Price(0)) < 0) {
            throw new ParseException(Price.MESSAGE_USAGE);
        }
        return toReturn;
    }

    /**
     * Parses a {@code String priceRange} into an {@code PriceRange}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code priceRange} is invalid.
     */
    public static PriceRange parsePriceRange(String priceRange) throws ParseException {
        requireNonNull(priceRange);
        logger.fine("Parsing price range: " + priceRange);
        String[] splitPrices = priceRange.split(PriceRange.DELIMITER);
        if (splitPrices.length != 2) {
            throw new ParseException(PriceRange.MESSAGE_USAGE);
        }

        Price lower = parsePrice(splitPrices[0]);
        Price upper = parsePrice(splitPrices[1]);

        if ((!upper.isNotApplicablePrice()) && upper.compareTo(lower) < 0) {
            throw new ParseException(PriceRange.MESSAGE_USAGE);
        }

        return new PriceRange(lower, upper);
    }

    /**
     * Parses {@code Collection<String> requests} into a {@code AdditionalRequests}.
     *
     * @throws ParseException if the given {@code request} is invalid.
     */
    public static AdditionalRequests parseAdditionalRequests(Collection<String> requests) throws ParseException {
        requireNonNull(requests);
        String[] parameters = new String[requests.size()];
        int index = 0;
        for (String request: requests) {
            logger.fine("Parsing additional request: " + request);
            String trimmedRequest = request.trim();
            if (!isInputAlphanumericAndNotBlank(trimmedRequest)) {
                throw new ParseException(AdditionalRequests.MESSAGE_CONSTRAINTS);
            }
            parameters[index] = trimmedRequest;
            index++;
        }
        return new AdditionalRequests(parameters);
    }

    /**
     * Parses a {@code String date} into an {@code LocalDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} cannot be parsed in all acceptable formats.
     */
    public static LocalDate parseDate(String date) throws ParseException {
        logger.fine("Parsing date: " + date);
        LocalDate output;
        for (String format: ACCEPTABLE_DATE_FORMATS) {
            try {
                output = LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
                return output;
            } catch (DateTimeParseException exception) {
                //Do nothing because it will eventually throw an exception if no formats match
            }
        }
        throw new ParseException("The date should be in this format: " + PREFERRED_DATE_FORMAT);
    }

    /**
     * Parses a {@code String age} into an {@code Age}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code age} is invalid.
     */
    public static Age parseAge(String age) throws ParseException {
        requireNonNull(age);
        logger.fine("Parsing age: " + age);
        int intAge;
        try {
            intAge = Integer.parseInt(age);
        } catch (NumberFormatException ex) {
            throw new ParseException(Age.MESSAGE_USAGE);
        }
        if (intAge < 0) {
            throw new ParseException(Age.MESSAGE_USAGE);
        }
        return new Age(intAge);
    }

    /**
     * Parses a {@code String color} into an {@code Color}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code color} is invalid.
     */
    public static Color parseColor(String color) throws ParseException {
        requireNonNull(color);
        logger.fine("Parsing color: " + color);
        String trimmedColor = color.trim();
        if (!isInputAlphanumericAndNotBlank(trimmedColor)) {
            throw new ParseException(Color.MESSAGE_CONSTRAINTS);
        }
        return new Color(trimmedColor);
    }

    /**
     * Parses a {@code String colorPattern} into an {@code colorPattern}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code colorPattern} is invalid.
     */
    public static ColorPattern parseColorPattern(String colorPattern) throws ParseException {
        requireNonNull(colorPattern);
        logger.fine("Parsing color pattern: " + colorPattern);
        String trimmedColorPattern = colorPattern.trim();
        if (!isInputAlphanumericAndNotBlank(trimmedColorPattern)) {
            throw new ParseException(ColorPattern.MESSAGE_CONSTRAINTS);
        }
        return new ColorPattern(trimmedColorPattern);
    }

    /**
     * Parses a {@code String species} into an {@code species}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code species} is invalid.
     */
    public static Species parseSpecies(String species) throws ParseException {
        requireNonNull(species);
        logger.fine("Parsing species: " + species);
        String trimmedSpecies = species.trim();
        if (!isInputAlphanumericAndNotBlank(trimmedSpecies)) {
            throw new ParseException(Species.MESSAGE_CONSTRAINTS);
        }
        return new Species(trimmedSpecies);
    }

    /**
     * Parses a {@code String birthday} into an {@code DateOfBirth}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code birthday} cannot be parsed in all acceptable formats.
     */
    public static DateOfBirth parseDateOfBirth(String date) throws ParseException {
        LocalDate output;
        logger.fine("Parsing date of birth: " + date);
        for (String format: ACCEPTABLE_DATE_FORMATS) {
            try {
                output = LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
                return new DateOfBirth(output);
            } catch (DateTimeParseException exception) {
                //Do nothing because it will eventually throw an exception if no formats match
            }
        }
        throw new ParseException(DateOfBirth.MESSAGE_USAGE);
    }

    /**
     * Parses a {@code String height} into an {@code Height}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code height} is invalid.
     */
    public static Height parseHeight(String height) throws ParseException {
        requireNonNull(height);
        logger.fine("Parsing height: " + height);
        double doubleHeight;
        try {
            doubleHeight = Double.parseDouble(height);
        } catch (NumberFormatException ex) {
            throw new ParseException(Height.MESSAGE_USAGE);
        }
        if (doubleHeight < 0) {
            throw new ParseException(Height.MESSAGE_USAGE);
        }

        return new Height(doubleHeight);
    }

    /**
     * Parses {@code Collection<String> certificates} into a {@code Set<PetCertificate>}.
     *
     * @throws ParseException if some of the given {@code certificates} are invalid.
     */
    public static Set<PetCertificate> parseCertificates(Collection<String> certificates) throws ParseException {
        requireNonNull(certificates);
        final Set<PetCertificate> certificateSet = new HashSet<>();
        for (String cert : certificates) {
            certificateSet.add(parsePetCertificate(cert));
        }
        return certificateSet;
    }

    /**
     * Parses a {@code String petCertificate} into an {@code PetCertificate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code certificate} is invalid.
     */
    public static PetCertificate parsePetCertificate(String certificate) throws ParseException {
        requireNonNull(certificate);
        String trimmedCertificate = certificate.trim();
        if (!isInputAlphanumericAndNotBlank(trimmedCertificate)) {
            throw new ParseException(PetCertificate.MESSAGE_CONSTRAINTS);
        }
        return new PetCertificate(trimmedCertificate);
    }

    /**
     * Parses a {@code String vaccinationStatus} into an {@code VaccinationStatus}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code weight} is invalid.
     */
    public static VaccinationStatus parseVaccinationStatus(String vaccinationStatus) throws ParseException {
        requireNonNull(vaccinationStatus);
        logger.fine("Parsing vac status: " + vaccinationStatus);
        if ("true".equals(vaccinationStatus)) {
            return new VaccinationStatus(true);
        } else if ("false".equals(vaccinationStatus)) {
            return new VaccinationStatus(false);
        } else {
            throw new ParseException(VaccinationStatus.MESSAGE_USAGE);
        }
    }

    /**
     * Parses a {@code String weight} into an {@code Weight}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code weight} is invalid.
     */
    public static Weight parseWeight(String weight) throws ParseException {
        requireNonNull(weight);
        logger.fine("Parsing weight: " + weight);
        double doubleWeight;
        try {
            doubleWeight = Double.parseDouble(weight);
        } catch (NumberFormatException ex) {
            throw new ParseException(Weight.MESSAGE_USAGE);
        }
        if (doubleWeight < 0) {
            throw new ParseException(Weight.MESSAGE_USAGE);
        }

        return new Weight(doubleWeight);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private static boolean isInputAlphanumericAndNotBlank(String test) {
        return (!test.isBlank()) && test.matches(VALIDATION_REGEX);
    }

    private static ArgumentMultimap getPersonAttributes(String args, PersonCategory category) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_NAME,
                        PREFIX_PHONE,
                        PREFIX_EMAIL,
                        PREFIX_ADDRESS,
                        PREFIX_LOCATION,
                        PREFIX_ORDER,
                        PREFIX_PET);

        if (!arePrefixesPresent(argMultimap,
                PREFIX_NAME,
                PREFIX_ADDRESS,
                PREFIX_PHONE,
                PREFIX_EMAIL,
                PREFIX_LOCATION) || !argMultimap.getPreamble().isEmpty()) {
            switch (category) {
            case BUYER:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddBuyerCommand.MESSAGE_USAGE));

            case DELIVERER:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddDelivererCommand.MESSAGE_USAGE));

            case SUPPLIER:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddSupplierCommand.MESSAGE_USAGE));

            default:
                // There are only three enum constants
                break;
            }
        }
        return argMultimap;
    }
}

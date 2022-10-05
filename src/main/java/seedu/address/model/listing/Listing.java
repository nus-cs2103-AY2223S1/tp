package seedu.address.model.listing;

import seedu.address.model.offer.Offer;
import seedu.address.model.person.Address;
import seedu.address.model.person.Person;

import java.util.ArrayList;
import java.util.List;

public class Listing {

	private final Address address;
	private final Person owner;
	private final int askingPrice;
	private final List<Person> interestedClients;
	private final List<Offer> currentOffers;

	public Listing(Address address, Person owner, int askingPrice) {
		this.address = address;
		this.owner = owner;
		this.askingPrice = askingPrice;
		interestedClients = new ArrayList<>();
		currentOffers = new ArrayList<>();
	}

	public Address getAddress() {
		return address;
	}

	public Person getOwner() {
		return owner;
	}

	public int getAskingPrice() {
		return askingPrice;
	}

	public List<Person> getInterestedClients() {
		return interestedClients;
	}

	public List<Offer> getCurrentOffers() {
		return currentOffers;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("; Adress: ")
				.append(getAddress())
				.append("; Owner: ")
				.append(getOwner())
				.append("; Asking Price: ")
				.append(getAskingPrice());

		List<Person> interstedClients = getInterestedClients();
		if (!interstedClients.isEmpty()) {
			builder.append("; Interested Clients: ");
			interstedClients.forEach(builder::append);
		}

		List<Offer> currentOffers = getCurrentOffers();
		if (!currentOffers.isEmpty()) {
			builder.append("; Current Offers: ");
			currentOffers.forEach(builder::append);
		}

		return builder.toString();
	}
}

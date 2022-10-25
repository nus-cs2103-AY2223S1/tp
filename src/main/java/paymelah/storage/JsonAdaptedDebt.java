package paymelah.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import paymelah.commons.exceptions.IllegalValueException;
import paymelah.model.debt.Debt;
import paymelah.model.debt.DebtDate;
import paymelah.model.debt.DebtTime;
import paymelah.model.debt.Description;
import paymelah.model.debt.Money;

/**
 * Jackson-friendly version of {@link Debt}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class JsonAdaptedDebt {

    private final String description;
    private final String money;
    private final boolean isPaid;
    private final String date;
    private final String time;

    /**
     * Constructs a {@code JsonAdaptedDebt} with the given debt details.
     */
    @JsonCreator
    public JsonAdaptedDebt(@JsonProperty("description") String description, @JsonProperty("money") String money, @JsonProperty("date") String date,
                           @JsonProperty("time") String time, @JsonProperty("isPaid") boolean isPaid) {
        this.description = description;
        this.money = money;
        this.date = date;
        this.time = time;
        this.isPaid = isPaid;
    }

    /**
     * Converts a given {@code Debt} into this class for Jackson use.
     */
    public JsonAdaptedDebt(Debt source) {
        description = source.getDescription().toString();
        money = source.getMoney().toString();
        date = source.getDate().toString();
        time = source.getTime().toString();
        isPaid = source.isPaid();
    }

    public String getDebtDescription() {
        return description;
    }

    public String getDebtMoney() {
        return money;
    }

    public String getDebtDate() {
        return date;
    }

    public String getDebtTime() {
        return time;
    }

    public boolean isPaid() {
        return isPaid;
    }

    /**
     * Converts this Jackson-friendly adapted debt object into the model's {@code Debt} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted debt.
     */
    public Debt toModelType() throws IllegalValueException {
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        if (!Money.isValidMoney(money)) {
            throw new IllegalValueException(Money.MESSAGE_CONSTRAINTS);
        }
        if (!DebtDate.isValidDate(date)) {
            throw new IllegalValueException(DebtDate.MESSAGE_CONSTRAINTS);
        }
        if (!DebtTime.isValidTime(time)) {
            throw new IllegalValueException(DebtTime.MESSAGE_CONSTRAINTS);
        }

        return new Debt(new Description(description), new Money(money), new DebtDate(date), new DebtTime(time), isPaid);
    }
}

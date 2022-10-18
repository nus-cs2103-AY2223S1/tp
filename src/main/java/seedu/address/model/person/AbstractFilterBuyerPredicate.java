package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * An abstract class for predicates within the FilterBuyers method.
 * Since there are three possible ways that a {@code FilterBuyersCommand}  can be executed,
 * we need to create an abstract class that can be passed into the {@code FilterBuyersCommand} constructor,
 * after which each individual predicate's behaviour is determined through polymorphism.
 */
public abstract class AbstractFilterBuyerPredicate implements Predicate<Person> {

    @Override
    public abstract boolean test(Person person);
}

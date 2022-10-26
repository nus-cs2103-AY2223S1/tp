package seedu.address.model.pricerange.property;

import java.util.function.Predicate;

/**
 * An abstract class for predicates within the FilterProps method.
 * Since there are three possible ways that a {@code FilterPropsCommand} can be executed,
 * we need to create an abstract class that can be passed into the {@code FilterPropsCommand} constructor,
 * after which each individual predicate's behaviour is determined through polymorphism.
 */
public abstract class AbstractFilterPropsPredicate implements Predicate<Property> {
    @Override
    public abstract boolean test(Property property);
}

package bookface.model.loan;

import java.util.ArrayList;

import bookface.model.person.Person;

public class Loan {

    protected boolean isLoaned = false;

    protected ArrayList<Person> loanedList = new ArrayList<>();


    public Loan() {
        this.isLoaned = false;
    }

}


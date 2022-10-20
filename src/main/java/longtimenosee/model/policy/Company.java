package longtimenosee.model.policy;

import static longtimenosee.commons.util.AppUtil.checkArgument;

/**
 * Encapsulation for the company a policy belongs to
 */
public class Company {

    public static final String MESSAGE_FORMAT_CONSTRAINTS = "Error: Company must be one "
            + "of the following companies: "
            + "{MNF, PRU, AXA, GEL, NTU, ETQ, TML, AIA, AVI, FWD}";

    public static final String CONSTRAINTS = "MNF, PRU, AXA, GEL, NTU, ETQ, TML, AIA, AVI, FWD";

    public final String value;

    private final CompanyName cn;

    /**
     * Enumerator representing CompanyNames.
     * Encapsulates 10 different companies.
     * Tagged with messages to be represented in String format.
     */
    public enum CompanyName {
        MNF("Manulife Financial Private Limited"),
        PRU("Prudential Assurance Company"),
        AXA("AXA Insurance Private Limited"),
        GEL("Great Eastern Life"),
        NTU("NTUC Income Insurance"),
        ETQ("Etiqa Insurance"),
        TML("Tokio Marine Life Insurance"),
        AIA("AIA Singapore Private Limited"),
        AVI("Aviva Limited"),
        FWD("Singapore Private Limited");

        private String message;

        CompanyName(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return message;
        }
    }

    /**
     * Constructs a {@code Company}.
     *
     * @param company
     */
    public Company(String company) {
        checkArgument(isValidCompany(company), MESSAGE_FORMAT_CONSTRAINTS);
        value = company;
        cn = parseCompanyName(company);
    }

    public CompanyName getCompanyName() {
        return this.cn;
    }

    public static boolean isValidCompany(String company) {
        return CONSTRAINTS.contains(company);
    }

    /**
     * Utility function to parse companyName from string
     * @param indicator to parseCompanyName from Json
     * @return the appropriate CompanyName wrapped in an enum
     */
    public static CompanyName parseCompanyName(String indicator) {
        switch (indicator) {

        case "MNF":
            return CompanyName.MNF;
        case "PRU":
            return CompanyName.PRU;
        case "AXA":
            return CompanyName.AXA;
        case "GEL":
            return CompanyName.GEL;
        case "NTU":
            return CompanyName.NTU;
        case "ETQ":
            return CompanyName.ETQ;
        case "TML":
            return CompanyName.TML;
        case "AIA":
            return CompanyName.AIA;
        case "AVI":
            return CompanyName.AVI;
        case "FWD":
            return CompanyName.FWD;

        default:
            //Should never reach this
            return CompanyName.PRU;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Company)) {
            return false;
        }

        Company otherCompany = (Company) other;
        boolean isSameCompany = this.getCompanyName() == otherCompany.getCompanyName();
        return isSameCompany;
    }

    @Override
    public String toString() {
        return cn.toString();
    }

}


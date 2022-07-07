package Contract;

public class TaxCalculator {
    // Javadoc of the calculateTax method describing its contract
    // ...
    public double calculateTax(double value) {
        if(value < 0) {
            throw new RuntimeException("Value has to be positive");
        }

        double taxValue = 0;

        // some complex business rule here...
        // final value goes to 'taxValue'
        if(taxValue < 0) {
            throw new RuntimeException("Calculated tax cannot be negative");
        }
        return taxValue;
    }
}

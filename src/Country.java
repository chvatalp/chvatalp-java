public class Country {
    private String name;
    private String code;
    private double standardRate;

    public Country(String name, String code, double standardRate) {
        this.name = name;
        this.code = code;
        this.standardRate = standardRate;
    }

    public Country() {
        this.name = "";
        this.code = "";
        this.standardRate = 0.0;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public double getStandardRate() {
        return standardRate;
    }

    @Override
    public String toString() {
        return  name + ", code: " + code + ", standard interest rate = " + standardRate;
    }
}

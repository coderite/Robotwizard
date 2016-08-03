package AlliedAutomotive;

/**
 * Created by zenbox on 2/28/2016.
 */
public class Part {
    private String make;
    private String model;
    private String year;
    private String seriesChassis;
    private String engine;
    private String vehicleDetails;
    private String SKU;
    private String partDescription;
    private String price;
    private String supplier;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSeriesChassis() {
        return seriesChassis;
    }

    public void setSeriesChassis(String seriesChassis) {
        this.seriesChassis = seriesChassis;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getVehicleDetails() {
        return vehicleDetails;
    }

    public void setVehicleDetails(String vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public String getPartDescription() {
        return partDescription;
    }

    public void setPartDescription(String partDescription) {
        this.partDescription = partDescription;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getPrintableString() {
        return make + "\t" +
        model + "\t" +
        year + "\t" +
        seriesChassis + "\t" +
        engine + "\t" +
        vehicleDetails + "\t" +
        SKU + "\t" +
        price + "\t" +
        partDescription + "\t" +
        supplier;
    }
}

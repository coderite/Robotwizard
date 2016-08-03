package VehicleEnquiry;

/**
 * Created by zenbox on 3/2/2016.
 */
public class Record {
    private String taxDueOn;
    private String motEpxiresOn;
    private String vehicleMake;
    private String dateOfFirstRegistration;
    private String yearOfManufacture;
    private String cylinderCapacity;
    private String co2Emissions;
    private String fuelType;
    private String vehicleStatus;
    private String vehicleColour;
    private String vehicleTypeApproval;
    private String wheelplan;
    private String revenueWeight;

    public String getTaxDueOn() {
        return taxDueOn;
    }

    public void setTaxDueOn(String taxDueOn) {
        this.taxDueOn = taxDueOn;
    }

    public String getMotEpxiresOn() {
        return motEpxiresOn;
    }

    public void setMotEpxiresOn(String motEpxiresOn) {
        this.motEpxiresOn = motEpxiresOn;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public String getDateOfFirstRegistration() {
        return dateOfFirstRegistration;
    }

    public void setDateOfFirstRegistration(String dateOfFirstRegistration) {
        this.dateOfFirstRegistration = dateOfFirstRegistration;
    }

    public String getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(String yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public String getCylinderCapacity() {
        return cylinderCapacity;
    }

    public void setCylinderCapacity(String cylinderCapacity) {
        this.cylinderCapacity = cylinderCapacity;
    }

    public String getCo2Emissions() {
        return co2Emissions;
    }

    public void setCo2Emissions(String co2Emissions) {
        this.co2Emissions = co2Emissions;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(String vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public String getVehicleColour() {
        return vehicleColour;
    }

    public void setVehicleColour(String vehicleColour) {
        this.vehicleColour = vehicleColour;
    }

    public String getVehicleTypeApproval() {
        return vehicleTypeApproval;
    }

    public void setVehicleTypeApproval(String vehicleTypeApproval) {
        this.vehicleTypeApproval = vehicleTypeApproval;
    }

    public String getWheelplan() {
        return wheelplan;
    }

    public void setWheelplan(String wheelplan) {
        this.wheelplan = wheelplan;
    }

    public String getRevenueWeight() {
        return revenueWeight;
    }

    public void setRevenueWeight(String revenueWeight) {
        this.revenueWeight = revenueWeight;
    }

    @Override
    public String toString() {
        return "Record{" +
                "taxDueOn='" + taxDueOn + '\'' +
                ", motEpxiresOn='" + motEpxiresOn + '\'' +
                ", vehicleMake='" + vehicleMake + '\'' +
                ", dateOfFirstRegistration='" + dateOfFirstRegistration + '\'' +
                ", yearOfManufacture='" + yearOfManufacture + '\'' +
                ", cylinderCapacity='" + cylinderCapacity + '\'' +
                ", co2Emissions='" + co2Emissions + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", vehicleStatus='" + vehicleStatus + '\'' +
                ", vehicleColour='" + vehicleColour + '\'' +
                ", vehicleTypeApproval='" + vehicleTypeApproval + '\'' +
                ", wheelplan='" + wheelplan + '\'' +
                ", revenueWeight='" + revenueWeight + '\'' +
                '}';
    }
}

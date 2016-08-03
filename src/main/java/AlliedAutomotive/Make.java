package AlliedAutomotive;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zenbox on 3/1/2016.
 */
public class Make {
    private String make;
    private List<String> makes;
    private List<String> models;
    private List<String> years;
    private List<String> seriesChassis;
    private List<String> engines;
    private List<String> vehicleDetails;

    public List<String> getMakes() {
        return makes;
    }

    public void setMakes(List<String> makes) {
        this.makes = makes;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public List<String> getModels() {
        return models;
    }

    public void setModels(List<String> models) {
        this.models = models;
    }

    public List<String> getYears() {
        return years;
    }

    public void setYears(List<String> years) {
        this.years = years;
    }

    public List<String> getSeriesChassis() {
        return seriesChassis;
    }

    public void setSeriesChassis(List<String> seriesChassis) {
        this.seriesChassis = seriesChassis;
    }

    public List<String> getEngines() {
        return engines;
    }

    public void setEngines(List<String> engines) {
        this.engines = engines;
    }

    public List<String> getVehicleDetails() {
        return vehicleDetails;
    }

    public void setVehicleDetails(List<String> vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }
}

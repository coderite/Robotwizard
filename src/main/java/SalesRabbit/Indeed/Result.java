package SalesRabbit.Indeed;

/**
 * Created by zenbox on 2/3/2016.
 */
public class Result {
    private String jobtitle;
    private String company;
    private String city;
    private String state;
    private String country;
    private String formattedLocation;
    private String source;
    private String data;
    private String snippet;
    private String url;
    private String latitude;
    private String longitude;
    private String jobkey;
    private String sponsored;
    private String expired;
    private String formattedLocaitonFull;
    private String formattedRelativeTime;

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFormattedLocation() {
        return formattedLocation;
    }

    public void setFormattedLocation(String formattedLocation) {
        this.formattedLocation = formattedLocation;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getJobkey() {
        return jobkey;
    }

    public void setJobkey(String jobkey) {
        this.jobkey = jobkey;
    }

    public String getSponsored() {
        return sponsored;
    }

    public void setSponsored(String sponsored) {
        this.sponsored = sponsored;
    }

    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }

    public String getFormattedLocaitonFull() {
        return formattedLocaitonFull;
    }

    public void setFormattedLocaitonFull(String formattedLocaitonFull) {
        this.formattedLocaitonFull = formattedLocaitonFull;
    }

    public String getFormattedRelativeTime() {
        return formattedRelativeTime;
    }

    public void setFormattedRelativeTime(String formattedRelativeTime) {
        this.formattedRelativeTime = formattedRelativeTime;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobtitle='" + jobtitle + '\'' +
                ", company='" + company + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", formattedLocation='" + formattedLocation + '\'' +
                ", source='" + source + '\'' +
                ", data='" + data + '\'' +
                ", snippet='" + snippet + '\'' +
                ", url='" + url + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", jobkey='" + jobkey + '\'' +
                ", sponsored='" + sponsored + '\'' +
                ", expired='" + expired + '\'' +
                ", formattedLocaitonFull='" + formattedLocaitonFull + '\'' +
                ", formattedRelativeTime='" + formattedRelativeTime + '\'' +
                '}';
    }
}

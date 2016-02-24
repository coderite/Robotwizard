package EatWellGuide;

import java.util.List;

/**
 * Created by zenbox on 2/16/2016.
 */
public class Record {
    private String name;
    private String subtitle;
    private String address;
    private String phone;
    private String website;
    private String facebook;
    private String twitter;
    private String email;
    private String description;
    private List<String> details;
    private List<String> hours;
    private List<String> category;
    private List<String> relationship;

    public List<String> getRelationship() {
        return relationship;
    }

    public void setRelationship(List<String> relationship) {
        this.relationship = relationship;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    public List<String> getHours() {
        return hours;
    }

    public void setHours(List<String> hours) {
        this.hours = hours;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Record{" +
                "name='" + name + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                ", facebook='" + facebook + '\'' +
                ", twitter='" + twitter + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", details='" + details + '\'' +
                ", hours=" + hours +
                ", category=" + category +
                ", relationship=" + relationship +
                '}';
    }

    public String printable() {
        StringBuilder ds = new StringBuilder();
        if(details.size() < 1)
            ds.append("N/A");
        else
            for(String d : details)
                ds.append(d);

        StringBuilder hr = new StringBuilder();
        if(hours.size() < 1)
            hr.append("N/A");
        else
            for (String d : hours)
                hr.append(d);

        StringBuilder cy = new StringBuilder();
        int count = 1;
        if(category.size() < 1)
            cy.append("N/A");
        else
            for(String d : category) {
                if(count++ < category.size())
                    d = d + ", ";
                cy.append(d);
            }

        StringBuilder rs = new StringBuilder();
        if(relationship.size() < 1)
            rs.append("N/A");
        else
            for(String d : relationship)
                rs.append(d);

        return name + "\t" + subtitle + "\t" + address + "\t" + phone + "\t" +website + "\t" + facebook + "\t" + twitter + "\t" + email + "\t" +description
                + "\t" + ds.toString() + "\t" + hr.toString() + "\t" + cy.toString() + "\t" + rs.toString();
    }
}

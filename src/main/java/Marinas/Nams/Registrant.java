package Marinas.Nams;

/**
 * Created by zenbox on 4/10/2016.
 */
public class Registrant {
    private String memberStatus;
    private String dateElected;
    private String certifiedIn;
    private String acceptsAssignmentsIn;
    private String companyName;
    private String email;
    private String website;
    private String workPhone;
    private String tollFreePhone;
    private String cellPhone;
    private String faxNo;
    private String name;

    public String getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }

    public String getDateElected() {
        return dateElected;
    }

    public void setDateElected(String dateElected) {
        this.dateElected = dateElected;
    }

    public String getCertifiedIn() {
        return certifiedIn;
    }

    public void setCertifiedIn(String certifiedIn) {
        this.certifiedIn = certifiedIn;
    }

    public String getAcceptsAssignmentsIn() {
        return acceptsAssignmentsIn;
    }

    public void setAcceptsAssignmentsIn(String acceptsAssignmentsIn) {
        this.acceptsAssignmentsIn = acceptsAssignmentsIn;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getTollFreePhone() {
        return tollFreePhone;
    }

    public void setTollFreePhone(String tollFreePhone) {
        this.tollFreePhone = tollFreePhone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getFaxNo() {
        return faxNo;
    }

    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Registrant{" +
                "memberStatus='" + memberStatus + '\'' +
                ", dateElected='" + dateElected + '\'' +
                ", certifiedIn='" + certifiedIn + '\'' +
                ", acceptsAssignmentsIn='" + acceptsAssignmentsIn + '\'' +
                ", companyName='" + companyName + '\'' +
                ", email='" + email + '\'' +
                ", website='" + website + '\'' +
                ", workPhone='" + workPhone + '\'' +
                ", tollFreePhone='" + tollFreePhone + '\'' +
                ", cellPhone='" + cellPhone + '\'' +
                ", faxNo='" + faxNo + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String toPrint() {
        return memberStatus + '\t' + dateElected + '\t' + certifiedIn + '\t' + acceptsAssignmentsIn + '\t' + companyName + '\t' + email + '\t' + website + '\t' + workPhone + '\t' + tollFreePhone + '\t' + cellPhone + '\t' + faxNo + '\t' + name;
    }
}

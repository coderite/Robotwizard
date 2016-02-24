package KSBLaw;

/**
 * Created by zenbox on 2/9/2016.
 */
public class Report {
    private String docketNumber;
    private String name;
    private String bookingDateTime;
    private String releasedOn;

    public String getDocketNumber() {
        return docketNumber;
    }

    public void setDocketNumber(String docketNumber) {
        this.docketNumber = docketNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBookingDateTime() {
        return bookingDateTime;
    }

    public void setBookingDateTime(String bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }

    public String getReleasedOn() {
        return releasedOn;
    }

    public void setReleasedOn(String releasedOn) {
        this.releasedOn = releasedOn;
    }

    @Override
    public String toString() {
        return "Report{" +
                "docketNumber='" + docketNumber + '\'' +
                ", name='" + name + '\'' +
                ", bookingDateTime='" + bookingDateTime + '\'' +
                ", releasedOn='" + releasedOn + '\'' +
                '}';
    }
}

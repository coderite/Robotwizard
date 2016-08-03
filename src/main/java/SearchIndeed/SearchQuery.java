package SearchIndeed;

/**
 * Created by zenbox on 2/3/2016.
 */
public class SearchQuery {
    private String publisher;
    private String query; // q
    private String location; // l
    private String country;
    private String sort;
    private String radius;
    private int start;
    private int limit;
    private String userip;
    private String useragent;
    private int version;
    private String baseUri;
    private String format;
    private String url;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getBaseUri() {
        return baseUri;
    }

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getUserip() {
        return userip;
    }

    public void setUserip(String userip) {
        this.userip = userip;
    }

    public String getUseragent() {
        return useragent;
    }

    public void setUseragent(String useragent) {
        this.useragent = useragent;
    }

    public String getUrl() {
        if(query != null) {
            return
                    (baseUri + "&" +
                            "publisher=" + publisher +
                            "&q=" + query +
                            "&l=" + location +
                            "&sort=" + sort +
                            "&radius=" + radius +
                            "&format=" + format +
                            "&start=" + start +
                            "&limit=" + limit +
                            "&userip="+ userip +
                            "&useragent" + useragent +
                            "&v=" + version).replaceAll("null", "");
        }
        return null;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

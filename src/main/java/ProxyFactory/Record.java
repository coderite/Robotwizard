package ProxyFactory;

/**
 * Created by zenbox on 3/2/2016.
 */
public class Record {
    private String ip;
    private String host;
    private String type;
    private String location;
    private String port;

    public void setPort(String port) {
        this.port = port;
    }

    public String getPort() {
        return port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Record{" +
                "ip='" + ip + '\'' +
                ", host='" + host + '\'' +
                ", type='" + type + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}

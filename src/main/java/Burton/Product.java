package Burton;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zenbox on 2/9/2016.
 */
public class Product {
    private String name;
    private String description;
    private String productNumber;
    private String upc;
    private String category;
    private String productImage;
    private LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductCode() {
        return productNumber;
    }

    public void setProductCode(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(LinkedHashMap<String, String> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", upc='" + upc + '\'' +
                ", category='" + category + '\'' +
                ", productImage='" + productImage + '\'' +
                ", map=" + map +
                '}';
    }

    public String getPrintItem() {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey() + "\t" + entry.getValue() + "\t");
        }
        String mapString = sb.toString();

        return name + "\t" + description + "\t" + productNumber + "\t" + upc + "\t" + category + "\t" + productImage + "\t" + mapString;
    }
}

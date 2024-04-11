package model;
import java.math.BigDecimal;

public class Service {
    private int service_id;
    private String name;
    private String description;
    private BigDecimal price;

    // Constructor
    public Service(int service_id, String name, String description, BigDecimal price) {
        this.service_id = service_id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // Getters and Setters
    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return "Service{" +
                "service_id=" + service_id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}

package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Vaccine {
//name (String): Stores the name of the vaccine. 
// code (String): Stores a unique code to identify the vaccine. 
// quantity (int): Stores the quantity of the vaccine in stock. 
// expirationDate (Date): Stores the expiration date of the vaccine. 
// price (double): Stores the price of the vaccine. 
// lastInjectedDate (Date): Stores the most recent date the vaccine was injected
    private String name;
    private String code;
    private int quantity;
    private Date expirationDate;
    private double price;
    private Date lastInjecDate;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    public Vaccine() {
    }

 
    public Vaccine(String name, String code, int quantity, Date expirationDate, double price, Date lastInjecDate) {
        this.name = name;
        this.code = code;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.price = price;
        this.lastInjecDate = lastInjecDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getLastInjecDate() {
        return lastInjecDate;
    }

    public void setLastInjecDate(Date lastInjecDate) {
        this.lastInjecDate = lastInjecDate;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.code);
        hash = 37 * hash + this.quantity;
        hash = 37 * hash + Objects.hashCode(this.expirationDate);
        hash = 37 * hash + Objects.hashCode(this.lastInjecDate);
        return hash;
    }

    @Override
    public String toString() {
        return "Vaccine{" + "name=" + name + ", code=" + code + ", quantity=" + quantity + ", expirationDate=" + dateFormat.format(expirationDate) + ", price=" + price + ", lastInjecDate=" + dateFormat.format(lastInjecDate) + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vaccine other = (Vaccine) obj;
        if (this.quantity != other.quantity) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        if (!Objects.equals(this.expirationDate, other.expirationDate)) {
            return false;
        }
        return Objects.equals(this.lastInjecDate, other.lastInjecDate);
    }

    
}

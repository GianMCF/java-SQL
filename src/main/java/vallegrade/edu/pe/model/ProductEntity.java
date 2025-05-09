package vallegrade.edu.pe.model;

import java.sql.Timestamp;

public class ProductEntity {
    private int id;
    private String name;
    private String surname;
    private String email;
    private boolean estate;
    private boolean isDeleted;

    // Getters y setters
    public int getProductId() {
        return id;
    }

    public void setProductId(int productId) {
        this.id = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEstate() {
        return estate;
    }

    public void setEstate(boolean estate) {
        this.estate = estate;
    }

    public boolean isDeleted() { return isDeleted; }

    public void setDeleted(boolean isDeleted) { this.isDeleted = isDeleted; }
}


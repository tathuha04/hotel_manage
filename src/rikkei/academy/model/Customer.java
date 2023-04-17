package rikkei.academy.model;

import java.io.Serializable;
import java.util.Set;

public class Customer implements Serializable {
    private String citizenIdentification;
    public String getCitizenIdentification() {
        return citizenIdentification;
    }

    public void setCitizenIdentification(String citizenIdentification) {
        this.citizenIdentification = citizenIdentification;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "citizenIdentification='" + citizenIdentification + '\'' +
                '}';
    }
}

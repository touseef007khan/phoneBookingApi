package com.example.bookings.componenet;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class Phone {

    private int id;
    private String model;
    private String availability;
    private String bookedBy;
    private LocalDateTime bookedAt;

    public Phone() {
    }

    public Phone(int id, String model, String availability, String bookedBy, LocalDateTime bookedAt) {
        this.id = id;
        this.model = model;
        this.availability = availability;
        this.bookedBy = bookedBy;
        this.bookedAt = bookedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(String bookedBy) {
        this.bookedBy = bookedBy;
    }

    public LocalDateTime getBookedAt() {
        return bookedAt;
    }

    public void setBookedAt(LocalDateTime bookedAt) {
        this.bookedAt = bookedAt;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", availability='" + availability + '\'' +
                ", bookedBy='" + bookedBy + '\'' +
                ", bookedAt=" + bookedAt +
                '}';
    }
}

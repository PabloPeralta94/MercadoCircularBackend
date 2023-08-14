package com.mercado.circular.model;

import com.mercado.circular.security.entity.Usuario;

import javax.persistence.*;

@Entity
public class FishingGear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private boolean isReel; // Indicates if it's a reel (true) or fishing rod (false)

    @Column(nullable = false)
    private String model; // Model information

    @Column(nullable = false)
    private String timeOfUsage; // New or used

    // Other attributes specific to fishing gear

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario user; // The user who is selling this gear

    // Constructors

    public FishingGear() {
    }

    public FishingGear(String name, String description, double price, boolean isReel, String model, String timeOfUsage) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.isReel = isReel;
        this.model = model;
        this.timeOfUsage = timeOfUsage;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isReel() {
        return isReel;
    }

    public void setReel(boolean reel) {
        isReel = reel;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTimeOfUsage() {
        return timeOfUsage;
    }

    public void setTimeOfUsage(String timeOfUsage) {
        this.timeOfUsage = timeOfUsage;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    // Other getters and setters for other attributes

}

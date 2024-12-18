package com.framja.gymmanagement.model;

public class MembershipCard {
    private long id; // Membership card ID

    private String plan; // Plan type (e.g., Basic, Premium)
    private int durationInMonths; // Duration of the membership (e.g., 6 months)
    private double price; // Cost of the membership

    // Constructor
    public MembershipCard(Long id, String plan, int durationInMonths) {
        this.id = id;
        this.plan = plan;
        this.durationInMonths = durationInMonths;
    }

    public long getId() {
        return id;
    }
    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public int getDurationInMonths() {
        return durationInMonths;
    }

    public void setDurationInMonths(int durationInMonths) {
        this.durationInMonths = durationInMonths;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

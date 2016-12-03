package uy.edu.ort.batch.model;

import java.lang.*;
import java.util.List;

public class Batch {
    private List<Van> vans;
    private List<Driver> drivers;
    private List<Client> clients;
    private List<Package> packages;
    private List<Agreement> agreements;
    private List<Delivery> deliveries;

    public List<Van> getVans() {
        return vans;
    }

    public void setVans(List<Van> vans) {
        this.vans = vans;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Package> getPackages() {
        return packages;
    }

    public void setPackages(List<Package> packages) {
        this.packages = packages;
    }

    public List<Agreement> getAgreements() {
        return agreements;
    }

    public void setAgreements(List<Agreement> agreements) {
        this.agreements = agreements;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{\n");
        sb.append(" \"vans\" : ").append(vans);
        sb.append("\",\n \"drivers\" : ").append(drivers);
        sb.append("\",\n \"clients\" : ").append(clients);
        sb.append("\",\n \"packages\" : ").append(packages);
        sb.append("\",\n \"agreements\" : ").append(agreements);
        sb.append("\",\n \"deliveries\" : ").append(deliveries);
        sb.append("\n}");
        return sb.toString();
    }
}

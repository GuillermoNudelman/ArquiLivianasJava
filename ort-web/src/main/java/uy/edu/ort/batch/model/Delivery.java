package uy.edu.ort.batch.model;

import java.util.List;

public class Delivery {
    private String code;
    private String date;
    private String vanCode;
    private String driverCode;
    private List<String> packageCodes;
    private Double travelDistance;

    public Delivery() {
    }

    public Delivery(String code, String date, String vanCode, String driverCode, List<String> packageCodes,
            Double travelDistance) {
        this.code = code;
        this.date = date;
        this.vanCode = vanCode;
        this.driverCode = driverCode;
        this.packageCodes = packageCodes;
        this.travelDistance = travelDistance;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVanCode() {
        return vanCode;
    }

    public void setVanCode(String vanCode) {
        this.vanCode = vanCode;
    }

    public String getDriverCode() {
        return driverCode;
    }

    public void setDriverCode(String driverCode) {
        this.driverCode = driverCode;
    }

    public List<String> getPackageCodes() {
        return packageCodes;
    }

    public void setPackageCodes(List<String> packageCodes) {
        this.packageCodes = packageCodes;
    }

    public Double getTravelDistance() {
        return travelDistance;
    }

    public void setTravelDistance(Double travelDistance) {
        this.travelDistance = travelDistance;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Delivery{");
        sb.append("\"code\" : \"").append(code);
        sb.append("\", \"date\" : \"").append(date);
        sb.append("\", \"vanCode\" : \"").append(vanCode);
        sb.append("\", \"driverCode\" : \"").append(driverCode);
        sb.append("\", \"packageCodes\" : ").append(packageCodes);
        sb.append("\", \"travelDistance\" : ").append(travelDistance);
        sb.append("}");
        return sb.toString();
    }
}

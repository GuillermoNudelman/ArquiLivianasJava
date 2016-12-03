package uy.edu.ort.batch.model;

public class Van {
    private String code;
    private String plate;
    private Double transportCapacity;
    private Double traveledKm;
    private Double nextServiceKm;

    public Van() {
    }

    public Van(String code, String plate, Double transportCapacity, Double traveledKm, Double nextServiceKm) {
        this.code = code;
        this.plate = plate;
        this.transportCapacity = transportCapacity;
        this.traveledKm = traveledKm;
        this.nextServiceKm = nextServiceKm;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Double getTransportCapacity() {
        return transportCapacity;
    }

    public void setTransportCapacity(Double transportCapacity) {
        this.transportCapacity = transportCapacity;
    }

    public Double getTraveledKm() {
        return traveledKm;
    }

    public void setTraveledKm(Double traveledKm) {
        this.traveledKm = traveledKm;
    }

    public Double getNextServiceKm() {
        return nextServiceKm;
    }

    public void setNextServiceKm(Double nextServiceKm) {
        this.nextServiceKm = nextServiceKm;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Van{");
        sb.append("\"code\" : \"").append(code);
        sb.append("\", \"plate\" : \"").append(plate);
        sb.append("\", \"transportCapacity\" : ").append(transportCapacity);
        sb.append("\", \"traveledKm\" : ").append(traveledKm);
        sb.append("\", \"nextServiceKm\" : ").append(nextServiceKm);
        sb.append("}");
        return sb.toString();
    }
}

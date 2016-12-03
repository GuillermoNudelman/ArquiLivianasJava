package uy.edu.ort.batch.model;

public class Driver {
    private String code;
    private String name;
    private String drivingLicense;

    public Driver() {
    }

    public Driver(String code, String name, String drivingLicense) {
        this.code = code;
        this.name = name;
        this.drivingLicense = drivingLicense;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Driver{");
        sb.append("\"code\" : \"").append(code);
        sb.append("\", \"name\" : \"").append(name);
        sb.append("\", \"drivingLicense\" : \"").append(drivingLicense);
        sb.append("}");
        return sb.toString();
    }
}

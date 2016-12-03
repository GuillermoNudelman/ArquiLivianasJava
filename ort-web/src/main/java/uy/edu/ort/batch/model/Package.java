package uy.edu.ort.batch.model;

public class Package {
    private String code;
    private String creationDate;
    private String companyName;
    private Double cost;
    private Double weight;
    private String description;

    public Package() {
    }

    public Package(String code, String creationDate, String companyName, Double cost, Double weight,
            String description) {
        this.code = code;
        this.creationDate = creationDate;
        this.companyName = companyName;
        this.cost = cost;
        this.weight = weight;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Package{");
        sb.append("\"code\" : \"").append(code);
        sb.append("\", \"creationDate\" : \"").append(creationDate);
        sb.append("\", \"companyName\" : \"").append(companyName);
        sb.append("\", \"cost\" : ").append(cost);
        sb.append("\", \"weight\" : ").append(weight);
        sb.append("\", \"description\" : \"").append(description);
        sb.append("}");
        return sb.toString();
    }
}

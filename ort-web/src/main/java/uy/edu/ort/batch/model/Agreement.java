package uy.edu.ort.batch.model;

public class Agreement {
    private String code;
    private String creationDate;
    private String companyName;
    private Double initialValue;
    private Double currentValue;

    public Agreement() {
    }

    public Agreement(String code, String creationDate, String companyName, Double initialValue, Double currentValue) {
        this.code = code;
        this.creationDate = creationDate;
        this.companyName = companyName;
        this.initialValue = initialValue;
        this.currentValue = currentValue;
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

    public Double getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(Double initialValue) {
        this.initialValue = initialValue;
    }

    public Double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Double currentValue) {
        this.currentValue = currentValue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Agreement{");
        sb.append("\"code\" : \"").append(code);
        sb.append("\", \"creationDate\" : \"").append(creationDate);
        sb.append("\", \"companyName\" : \"").append(companyName);
        sb.append("\", \"initialValue\" : ").append(initialValue);
        sb.append("\", \"currentValue\" : ").append(currentValue);
        sb.append("}");
        return sb.toString();
    }
}

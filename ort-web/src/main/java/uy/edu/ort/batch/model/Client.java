package uy.edu.ort.batch.model;

public class Client {
    private String companyName;
    private String adrress;
    private Long phone;
    private String contactName;

    public Client() {
    }

    public Client(String companyName, String adrress, Long phone, String contactName) {
        this.companyName = companyName;
        this.adrress = adrress;
        this.phone = phone;
        this.contactName = contactName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAdrress() {
        return adrress;
    }

    public void setAdrress(String adrress) {
        this.adrress = adrress;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Client{");
        sb.append("\"companyName\" : \"").append(companyName);
        sb.append("\", \"adrress\" : \"").append(adrress);
        sb.append("\", \"phone\" : ").append(phone);
        sb.append("\", \"contactName\" : \"").append(contactName);
        sb.append("}");
        return sb.toString();
    }
}

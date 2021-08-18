package mx.edu.utez.model.type;

public class BeanType {
    private int idType;
    private String type;
    private int status;

    public BeanType() {
    }

    public BeanType(int idType, String type, int status) {
        this.idType = idType;
        this.type = type;
        this.status = status;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

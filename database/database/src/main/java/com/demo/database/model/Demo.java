package model;

/**
 * Table: demo
 */
public class Demo {
    /**
     * Column: id
     * Type: INT
     */
    private Integer id;

    /**
     * Column: message
     * Type: VARCHAR(20)
     */
    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }
}
package org.dargor.model;

public class Computer {

    private String id;
    private String model;
    private String description;

    public Computer(String id, String model, String description) {
        this.id = id;
        this.model = model;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package ru.job4j.generic;

/**
 * Base model.
 */
public abstract class Base {
    /**
     * id of the element.
     */
    private String id;

    /**
     * id setter.
     * @param id id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * id getter.
     * @return id of the element.
     */
    public String getId() {
        return id;
    }

}

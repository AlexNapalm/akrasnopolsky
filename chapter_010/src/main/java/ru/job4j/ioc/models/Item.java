package ru.job4j.ioc.models;

import java.sql.Timestamp;

public class Item {
    private int id;
    private String desc;
    private Timestamp created;
    private boolean done;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Item{");
        sb.append("id=").append(id);
        sb.append(", desc='").append(desc).append('\'');
        sb.append(", created=").append(created);
        sb.append(", done=").append(done);
        sb.append('}');
        return sb.toString();
    }
}

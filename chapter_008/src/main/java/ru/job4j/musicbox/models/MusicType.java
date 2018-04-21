package ru.job4j.musicbox.models;

public class MusicType {
    private int id;
    private String musicType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMusicType() {
        return musicType;
    }

    public void setMusicType(String musicType) {
        this.musicType = musicType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MusicType{");
        sb.append("id=").append(id);
        sb.append(", musicType='").append(musicType).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

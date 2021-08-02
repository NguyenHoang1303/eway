package eway.bai3;

public class Position {
    private String mmsi;
    private int longitude;
    private int latitude;
    private String time;

    public Position() {
    }

    public Position(String mmsi, int longitude, int latitude, String time) {
        this.mmsi = mmsi;
        this.longitude = longitude;
        this.latitude = latitude;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Position{" +
                "mmsi='" + mmsi + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", time='" + time + '\'' +
                '}';
    }

    public String getMmsi() {
        return mmsi;
    }

    public void setMmsi(String mmsi) {
        this.mmsi = mmsi;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

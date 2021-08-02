package eway.bai3;

import java.util.LinkedList;
import java.util.List;

public class Warning {
    private String mmsi;
    private String warning;
    private String areName;
    private int longitude;
    private int latitude;
    private String time;

    public Warning() {
    }

    public Warning getWarning(List<Area> areaList, Position position){
        Warning warning = new Warning();
        LinkedList<Position> list = new LinkedList<>();
        for (Area area: areaList) {
            boolean checkLongtiude = (area.getLongitudeLeft() <= position.getLongitude()) && (position.getLongitude() <= area.getLongitudeRight());
            boolean checkLatitude = (area.getLatitudeBottom() <= position.getLatitude()) && (position.getLatitude() <= area.getLatitudeTop());
            boolean chekLongtiude1 = area.getLongitudeLeft() >= position.getLongitude();
            if (checkLatitude && checkLongtiude){
                warning.setMmsi(position.getMmsi());
                warning.setWarning("canh bao ra khoi vung");
                warning.setAreName(area.getNameArea());
                warning.setLongitude(position.getLongitude());
                warning.setLatitude(position.getLatitude());
                warning.setTime(position.getTime());
                System.out.println(warning.toString());
                return warning;
            }
        }
        warning.setMmsi(position.getMmsi());
        warning.setWarning("canh bao xam nhap  vung");
        warning.setAreName("vung 2");
        warning.setLongitude(position.getLongitude());
        warning.setLatitude(position.getLatitude());
        warning.setTime(position.getTime());
        System.out.println(warning.toString());
        return warning;
    }

    public Warning(String mmsi, String warning, String areName, int longitude, int latitude, String date) {
        this.mmsi = mmsi;
        this.warning = warning;
        this.areName = areName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.time = date;
    }

    @Override
    public String toString() {
        return "Warning{" +
                "mmsi='" + mmsi + '\'' +
                ", warning='" + warning + '\'' +
                ", areName='" + areName + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", date=" + time +
                '}';
    }

    public String getMmsi() {
        return mmsi;
    }

    public void setMmsi(String mmsi) {
        this.mmsi = mmsi;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public String getAreName() {
        return areName;
    }

    public void setAreName(String areName) {
        this.areName = areName;
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

    public String setTime() {
        return time;
    }

    public void setTime(String date) {
        this.time = date;
    }
}

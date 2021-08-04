package eway.bai3;

import java.util.List;

public class Warning {
    private String mmsi;
    private String warningMessage;
    private String areName;
    private int longitude;
    private int latitude;
    private String time;
    public Warning() {
    }



    public String getTime() {
        return time;
    }

    public static Warning getWarningInform(List<Area> areaList, Position position) {
       Warning warning = new Warning();
        for (Area area : areaList) {
            boolean checkLongtiude = (area.getLongitudeLeft() <= position.getLongitude()) && (position.getLongitude() <= area.getLongitudeRight());
            boolean checkLatitude = (area.getLatitudeBottom() <= position.getLatitude()) && (position.getLatitude() <= area.getLatitudeTop());
            if (checkLatitude && checkLongtiude) {
                warning.setMmsi(position.getMmsi());
                warning.setWarningMessage("canh bao ra khoi vung");
                warning.setAreName(area.getNameArea());
                warning.setLongitude(position.getLongitude());
                warning.setLatitude(position.getLatitude());
                warning.setTime(position.getTime());
                System.out.println(warning);
                return warning;
            }
        }

        warning.setMmsi(position.getMmsi());
        warning.setWarningMessage("canh bao xam nhap vung");
        warning.setAreName("vung 2");
        warning.setLongitude(position.getLongitude());
        warning.setLatitude(position.getLatitude());
        warning.setTime(position.getTime());
        System.out.println(warning);
        return warning;
    }

    public Warning(String mmsi, String warningMessage, String areName, int longitude, int latitude, String date) {
        this.mmsi = mmsi;
        this.warningMessage = warningMessage;
        this.areName = areName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.time = date;
    }

    @Override
    public String toString() {
        return mmsi + "|" + warningMessage + "|" + areName + "|" + longitude + "|" + latitude + "|" + time;
    }

    public String getMmsi() {
        return mmsi;
    }

    public void setMmsi(String mmsi) {
        this.mmsi = mmsi;
    }

    public void setWarning(String warningMessage) {
        this.warningMessage = warningMessage;
    }

    public String getWarningMessage() {
        return warningMessage;
    }

    public void setWarningMessage(String warningMessage) {
        this.warningMessage = warningMessage;
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

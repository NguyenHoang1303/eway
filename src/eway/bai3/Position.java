package eway.bai3;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Position extends Thread {
    private String mmsi;
    private int longitude;
    private int latitude;
    private String time;
    private  String informationPosition;
    private  Position position;

    @Override
    public void run() {
        getPositionToFile();
    }

     void getPositionToFile() {
        position = new Position();
        if (informationPosition != null){
            position.setMmsi(getInformationPosition(informationPosition, 1));
            position.setLongitude(Integer.parseInt(getInformationPosition(informationPosition, 2)));
            position.setLatitude(Integer.parseInt(getInformationPosition(informationPosition, 3)));
            position.setTime(getInformationPosition(informationPosition, 4));
        }
    }

    static String getInformationPosition(String position, int choice) {
        Pattern pattern = Pattern.compile("(.+)\\|(.+)\\|(.+)\\|(.+)");
        Matcher matcher = pattern.matcher(position);
        String inforSearch = null;
        if (matcher.find()) {
            switch (choice) {
                case 1:
                    //Mmsi
                    inforSearch = matcher.group(1);
                    break;
                case 2:
                    //longitude
                    inforSearch = matcher.group(2);
                    break;
                case 3:
                    //latitude
                    inforSearch = matcher.group(3);
                    break;
                case 4:
                    //thời gian (dd/MM/yyyy HH:mm:ss)
                    inforSearch = matcher.group(4);
                    break;
                default:
                    System.out.println("chon sai!");
                    break;
            }
        }
        return inforSearch;
    }

    public String getPathFile() {
        return informationPosition;
    }

    public void setPathFile(String informationPosition) {
        this.informationPosition = informationPosition;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position(String informationPosition) {
        this.informationPosition = informationPosition;
    }

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

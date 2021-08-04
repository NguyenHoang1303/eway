package eway.bai3;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Area {
    private String nameArea;
    private int longitudeLeft;
    private int longitudeRight;
    private int latitudeTop;
    private int latitudeBottom;


    public static Area getAreaToContent(String text) {
        Area area = new Area();
        String name = getInformationArea(text, 1);
        area.setNameArea(name);
        area.setLongitudeLeft(Integer.parseInt(getInformationArea(text, 2)));
        area.setLongitudeRight(Integer.parseInt(getInformationArea(text, 3)));
        area.setLatitudeTop(Integer.parseInt(getInformationArea(text, 4)));
        area.setLatitudeBottom(Integer.parseInt(getInformationArea(text, 5)));
        return area;
    }

    public static String getInformationArea(String area, int choice) {
        Pattern pattern = Pattern.compile("(.+)\\|(.+)\\|(.+)\\|(.+)\\|(.+)");
        Matcher matcher = pattern.matcher(area);
        String inforSearch = null;
        if (matcher.find()) {
            switch (choice) {
                case 1:
                    //Ten vung
                    inforSearch = matcher.group(1);
                    break;
                case 2:
                    //longitude_left
                    inforSearch = matcher.group(2);
                    break;
                case 3:
                    //longitude_right
                    inforSearch = matcher.group(3);
                    break;
                case 4:
                    //latitude_top
                    inforSearch = matcher.group(4);
                    break;
                case 5:
                    //latitude_bottom
                    inforSearch = matcher.group(5);
                    break;
                default:
                    System.out.println("chon sai!");
                    break;
            }
        }
        return inforSearch;
    }

    public Area() {
    }

    public Area(String nameArea, int longitudeLeft, int longitudeRight, int latitudeTop, int latitudeBottom) {
        this.nameArea = nameArea;
        this.longitudeLeft = longitudeLeft;
        this.longitudeRight = longitudeRight;
        this.latitudeTop = latitudeTop;
        this.latitudeBottom = latitudeBottom;
    }

    @Override
    public String toString() {
        return "Area{" +
                "nameArea='" + nameArea + '\'' +
                ", longitudeLeft=" + longitudeLeft +
                ", longitudeRight=" + longitudeRight +
                ", latitudeTop=" + latitudeTop +
                ", latitudeBottom=" + latitudeBottom +
                '}';
    }

    public String getNameArea() {
        return nameArea;
    }

    public void setNameArea(String nameArea) {
        this.nameArea = nameArea;
    }

    public int getLongitudeLeft() {
        return longitudeLeft;
    }

    public void setLongitudeLeft(int longitudeLeft) {
        this.longitudeLeft = longitudeLeft;
    }

    public int getLongitudeRight() {
        return longitudeRight;
    }

    public void setLongitudeRight(int longitudeRight) {
        this.longitudeRight = longitudeRight;
    }

    public int getLatitudeTop() {
        return latitudeTop;
    }

    public void setLatitudeTop(int latitudeTop) {
        this.latitudeTop = latitudeTop;
    }

    public int getLatitudeBottom() {
        return latitudeBottom;
    }

    public void setLatitudeBottom(int latitudeBottom) {
        this.latitudeBottom = latitudeBottom;
    }
}

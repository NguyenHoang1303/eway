package eway.bai3;

public class Area {
    private String nameArea;
    public int longitudeLeft;
    public int longitudeRight;
    public int latitudeTop;
    public int latitudeBottom;

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

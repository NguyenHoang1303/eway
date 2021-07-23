package bai1;

public class CongNhan extends CanBo{
    private int bac;


    @Override
    public String toString() {
        return "CongNhan{" +
                "bac=" + bac +
                '}';
    }

    public CongNhan() {

    }

    public CongNhan(String hoTen, int tuoi, int gender, String diaChi, int bac) {
        super(hoTen, tuoi, gender, diaChi);
        this.bac = bac;
    }

    public int getBac() {
        return bac;
    }

    public void setBac(int bac) {
        this.bac = bac;
    }
}

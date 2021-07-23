package bai1;

public class CanBo {
    private String hoTen;
    private int tuoi;
    private int gender;
    private String diaChi;

    @Override
    public String toString() {
        return "CanBo{" +
                "hoTen='" + hoTen + '\'' +
                ", tuoi=" + tuoi +
                ", gender=" + gender +
                ", diaChi='" + diaChi + '\'' +
                '}';
    }

    public CanBo() {
    }

    public CanBo(String hoTen, int tuoi, int gender, String diaChi) {
        this.hoTen = hoTen;
        this.tuoi = tuoi;
        this.gender = gender;
        this.diaChi = diaChi;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tdt.edu.vn;

/**
 * @author HP
 */
public class TimeTable {
    private String Id;
    private String FirstName;
    private String LastName;
    private String ThuHai;
    private String ThuBa;
    private String ThuTu;
    private String ThuNam;
    private String ThuSau;
    private String ThuBay;

    public TimeTable(String Id, String FirstName, String LastName, String ThuHai, String ThuBa, String ThuTu, String ThuNam, String ThuSau, String ThuBay) {
        this.Id = Id;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.ThuHai = ThuHai;
        this.ThuBa = ThuBa;
        this.ThuTu = ThuTu;
        this.ThuNam = ThuNam;
        this.ThuSau = ThuSau;
        this.ThuBay = ThuBay;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getThuHai() {
        return ThuHai;
    }

    public void setThuHai(String ThuHai) {
        this.ThuHai = ThuHai;
    }

    public String getThuBa() {
        return ThuBa;
    }

    public void setThuBa(String ThuBa) {
        this.ThuBa = ThuBa;
    }

    public String getThuTu() {
        return ThuTu;
    }

    public void setThuTu(String ThuTu) {
        this.ThuTu = ThuTu;
    }

    public String getThuNam() {
        return ThuNam;
    }

    public void setThuNam(String ThuNam) {
        this.ThuNam = ThuNam;
    }

    public String getThuSau() {
        return ThuSau;
    }

    public void setThuSau(String ThuSau) {
        this.ThuSau = ThuSau;
    }

    public String getThuBay() {
        return ThuBay;
    }

    public void setThuBay(String ThuBay) {
        this.ThuBay = ThuBay;
    }


}

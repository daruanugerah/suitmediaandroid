package com.example.daru.tessuitmedia;

/**
 * Created by user on 15/07/2016.
 */
public class ResponGuest {
    private String nama;
    private String birthday;

    public ResponGuest(String nama, String birthday) {
        this.nama = nama;
        this.birthday = birthday;
    }
    public String getNama() {
        return nama;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public void setBirthday(String tanggal) {
        this.birthday = tanggal;
    }
}

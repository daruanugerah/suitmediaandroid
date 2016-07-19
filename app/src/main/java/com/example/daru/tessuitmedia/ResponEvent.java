package com.example.daru.tessuitmedia;

/**
 * Created by user on 15/07/2016.
 */
public class ResponEvent {
    private String nama;
    private String tanggal;

    public ResponEvent(String nama, String tanggal) {
        this.nama = nama;
        this.tanggal = tanggal;
    }
    public String getNama() {
        return nama;
    }
    public String getTanggal() {
        return tanggal;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}

package com.example.pendataanlaptop.database.entitas;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "merek_laptop")
    public String merekLaptop;

    @ColumnInfo(name = "tipe_laptop")
    public String tipeLaptop;

    public String processor;
    public int harga;

    // Constructor
    public User(String merekLaptop, String tipeLaptop, String processor, int harga) {
        this.merekLaptop = merekLaptop;
        this.tipeLaptop = tipeLaptop;
        this.processor = processor;
        this.harga = harga;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMerekLaptop() {
        return merekLaptop;
    }

    public void setMerekLaptop(String merekLaptop) {
        this.merekLaptop = merekLaptop;
    }

    public String getTipeLaptop() {
        return tipeLaptop;
    }

    public void setTipeLaptop(String tipeLaptop) {
        this.tipeLaptop = tipeLaptop;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
}

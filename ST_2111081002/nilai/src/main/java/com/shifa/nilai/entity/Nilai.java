/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shifa.nilai.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author WIN - 10
 */
@Entity
@Table
public class Nilai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id ;
    private long mahasiswa_id;
    private long matakuliah_id;
    private double nilai;

    public Nilai() {
    }

    public Nilai(Long Id, Long mahasiswa_id, Long matakuliah_id, double nilai) {
        this.Id = Id;
        this.mahasiswa_id = mahasiswa_id;
        this.matakuliah_id = matakuliah_id;
        this.nilai = nilai;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public Long getMahasiswa_id() {
        return mahasiswa_id;
    }

    public void setMahasiswa_id(Long mahasiswaId) {
        this.mahasiswa_id = mahasiswaId;
    }

    public Long getMatakuliah_id() {
        return matakuliah_id;
    }

    public void setMatakuliah_id(Long matakuliahId) {
        this.matakuliah_id = matakuliahId;
    }

    public double getNilai() {
        return nilai;
    }

    public void setNilai(double nilai) {
        this.nilai = nilai;
    }

    @Override
    public String toString() {
        return "Nilai{" + "Id=" + Id + ", mahasiswa_id=" + mahasiswa_id + ", matakuliahId=" + matakuliah_id + ", nilai=" + nilai + '}';
    }
}

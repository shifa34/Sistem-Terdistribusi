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
    
    private Long Id;
    private Long mahasiswaId;
    private Long matakuliahId;
    private double nilai;

    public Nilai(Long Id, Long mahasiswaId, Long matakuliahId, double nilai) {
        this.Id = Id;
        this.mahasiswaId = mahasiswaId;
        this.matakuliahId = matakuliahId;
        this.nilai = nilai;
    }
    
    public Nilai() {
    }

    public Long getId() {
        return Id;
    }

    public Long getMahasiswaId() {
        return mahasiswaId;
    }

    public Long getMatakuliahId() {
        return matakuliahId;
    }

    public double getNilai() {
        return nilai;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public void setMahasiswaId(Long mahasiswaId) {
        this.mahasiswaId = mahasiswaId;
    }

    public void setMatakuliahId(Long matakuliahId) {
        this.matakuliahId = matakuliahId;
    }

    public void setNilai(double nilai) {
        this.nilai = nilai;
    }
    
    @Override
    public String toString() {
        return "Nilai{" + "Id=" + Id + ", mahasiswaId=" + mahasiswaId + ", matakuliahId=" + matakuliahId + ", nilai=" + nilai + '}';
    }
}

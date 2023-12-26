/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arshifa.mahasiswa.entity;

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
public class Mahasiswa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    private String nama;
    private String email;

    public Mahasiswa(Long id, String nama, String email) {
        this.id = id;
        this.nama = nama;
        this.email = email;
    }

    public Mahasiswa() {
    }

    public Long getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "Mahasiswa{" + "id=" + id + ", nama=" + nama + ", email=" + email + '}';
    }
    
    
}

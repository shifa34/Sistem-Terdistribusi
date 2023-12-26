/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.arshifa.matakuliah.repository;

import com.arshifa.matakuliah.entity.Matakuliah;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author WIN - 10
 */
@Repository
public interface MatakuliahRepository extends JpaRepository<Matakuliah, Long>{
//    public Optional<Matakuliah> findMatakuliahByNama(String nama);
    public Optional<Matakuliah> findMatakuliahByKode(String kode);
//    public Optional<Matakuliah> findMatakuliahBySks(int sks);
}

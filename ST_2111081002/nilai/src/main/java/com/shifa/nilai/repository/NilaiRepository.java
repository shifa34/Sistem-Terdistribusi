/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.shifa.nilai.repository;

import com.shifa.nilai.entity.Nilai;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 *
 * @author WIN - 10
 */
@Repository
public interface NilaiRepository extends JpaRepository<Nilai, Long>{
    
    @Query(value = "SELECT * FROM nilai WHERE mahasiswa_id=?1", nativeQuery = true)
    public List<Nilai> findByIdMahasiswa(Long nilaiId);
    
}

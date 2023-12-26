/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arshifa.matakuliah.service;

import com.arshifa.matakuliah.entity.Matakuliah;
import com.arshifa.matakuliah.repository.MatakuliahRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author WIN - 10
 */
@Service
public class MatakuliahService {
    @Autowired
    private  MatakuliahRepository matakuliahRepository;
    
    public List<Matakuliah>getAll(){
        return matakuliahRepository.findAll();
    }
    
    public Matakuliah getMatakuliah(Long id){
        Optional<Matakuliah> matakuliahOptional = matakuliahRepository.findById(id);
        return matakuliahOptional.get();
    }
    
    public void insert(Matakuliah matakuliah){
        Optional<Matakuliah> matakuliahOptional = matakuliahRepository.findMatakuliahByKode(matakuliah.getKode());
        if (matakuliahOptional.isPresent()) {
            throw new IllegalStateException("nama mata kuliah ini sudah ada");
        }
        matakuliahRepository.save(matakuliah);
    }
    
//    public void delete (Long id){
//        boolean ada = matakuliahRepository.existsById(id);
//        
//        if (!ada) {
//            throw new IllegalStateException("mata kuliah ini tidak ada");
//        }
//        matakuliahRepository.deleteById(id);
//    }
    
}

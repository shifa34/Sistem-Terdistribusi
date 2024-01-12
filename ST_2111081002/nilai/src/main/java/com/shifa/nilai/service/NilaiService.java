/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shifa.nilai.service;

import com.shifa.nilai.entity.Nilai;
import com.shifa.nilai.repository.NilaiRepository;
import com.shifa.nilai.vo.Mahasiswa;
import com.shifa.nilai.vo.Matakuliah;
import com.shifa.nilai.vo.ResponseTemplate;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author WIN - 10
 */
@Service
public class NilaiService {
    @Autowired
    private NilaiRepository nilaiRepository;
    
    @Autowired
    private RestTemplate restTemplate;
    
    public List<Nilai> getAll(){
        return nilaiRepository.findAll();
    } 
    
    public List<Nilai> getNilai(){
        return nilaiRepository.findAll();
    }
    
    /*public void insert(Nilai nilai){
        Optional<Nilai> nilaiOptional = nilaiRepository.findById(nilai.getId());
        
        
        if (nilaiOptional.isPresent()) {
            throw new IllegalStateException("Nilai Mahasiswa Sudah Ada");
        }
        
       nilaiRepository.save(nilai);
    }*/
    
    public void insert(Nilai nilai){
    Optional<Nilai> nilaiOptional = nilaiRepository.findById(nilai.getId());

    if (nilaiOptional.isPresent()) {
        throw new IllegalStateException("Nilai Mahasiswa Sudah Ada");
    }

    nilaiRepository.save(nilai);
}

     
     public void delete(long id){
        boolean ada = nilaiRepository.existsById(id);
        if (!ada) {
            throw new IllegalStateException("Nilai ini tidak ada");
        }
        nilaiRepository.deleteById(id);
    }
     
     @Transactional
    public void update(Long id, Long mahasiswa_id, Long matakuliah_id, Double nilai) {
        Nilai nilaiUpdate = nilaiRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Nilai tidak ada"));

        if (mahasiswa_id != null && !Objects.equals(nilaiUpdate.getMahasiswa_id(), mahasiswa_id)) {
            nilaiUpdate.setMahasiswa_id(mahasiswa_id);
        }

        if (matakuliah_id != null && !Objects.equals(nilaiUpdate.getMatakuliah_id(), matakuliah_id)) {
            nilaiUpdate.setMatakuliah_id(matakuliah_id);
        }

        if (nilai != null && !Objects.equals(nilaiUpdate.getNilai(), nilai)) {
            nilaiUpdate.setNilai(nilai);
        }
    }

    
    public List<ResponseTemplate> getNilai(Long nilaiId) {
        List <ResponseTemplate> responseList = new ArrayList<>();

        List<Nilai> nilaiList = nilaiRepository.findByIdMahasiswa(nilaiId);

        for (Nilai nilai : nilaiList) {
            Mahasiswa mahasiswa
                    = restTemplate.getForObject("http://localhost:9001/api/v1/mahasiswa/"
                            + nilai.getMahasiswa_id(), Mahasiswa.class);
            Matakuliah matakuliah
                    = restTemplate.getForObject("http://localhost:9002/api/v1/matakuliah/"
                            + nilai.getMatakuliah_id(), Matakuliah.class);
            ResponseTemplate vo = new ResponseTemplate();

            vo.setNilai(nilai);
            vo.setMahasiswa(mahasiswa);
            vo.setMatakuliah(matakuliah);
            responseList.add(vo);

        }

        return responseList;
    }
    
   /* public ResponseTemplate getNilai(Long nilaiId){
        ResponseTemplate vo = new ResponseTemplate();
        Nilai nilai = nilaiRepository.findById(nilaiId)
                .orElseThrow(() -> new IllegalStateException("Nilai tidak ada"));
        Mahasiswa mahasiswa = 
                restTemplate.getForObject("http://localhost:9001/api/v1/mahasiswa/"
                        + nilai.getMahasiswaId(), Mahasiswa.class);
        Matakuliah matakuliah = 
                restTemplate.getForObject("http://localhost:9002/api/v1/matakuliah/"
                        + nilai.getMatakuliahId(), Matakuliah.class);
        vo.setNilai(nilai); 
        vo.setMahasiswa(mahasiswa);
        vo.setMatakuliah(matakuliah);
        return vo;
    }*/
}

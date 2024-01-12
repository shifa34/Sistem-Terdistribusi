/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shifa.nilai.controller;

import com.shifa.nilai.entity.Nilai;
import com.shifa.nilai.service.NilaiService;
import com.shifa.nilai.vo.ResponseTemplate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author WIN - 10
 */

@RestController
@RequestMapping("api/v1/nilai")
public class NilaiController {
    @Autowired
    private NilaiService nilaiService;
    
    @PostMapping
    public void insert(@RequestBody Nilai nilai){
        nilaiService.insert(nilai); 
    }
    
    @GetMapping
    public List<Nilai> getAll(){
    return nilaiService.getAll();}
    
   @GetMapping(path = "{id}")
    public List<ResponseTemplate> getNilai(@PathVariable("id") Long nilaiId){
        return nilaiService.getNilai(nilaiId);
    }
    
    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable("id") Long id){
        nilaiService.delete(id);
    }
    
    @PutMapping(path = "{id}")
    public void update(@PathVariable("id") Long id,
            @RequestParam(required = false) Long idMahasiswa,
            @RequestParam(required = false) Long idMatakuliah,
            @RequestParam(required = false) double nilai){
        nilaiService.update(id, idMahasiswa, idMatakuliah, nilai);
    }
    
}
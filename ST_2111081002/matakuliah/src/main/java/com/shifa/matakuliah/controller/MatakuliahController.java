/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shifa.matakuliah.controller;

import com.shifa.matakuliah.entity.Matakuliah;
import com.shifa.matakuliah.service.MatakuliahService;
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
 * @author asus
 */
@RestController
@RequestMapping("api/v1/matakuliah")
public class MatakuliahController {

    @Autowired
    private MatakuliahService matakuliahservice;

    @GetMapping
    public List<Matakuliah> getAll() {
        return matakuliahservice.getAll();
    }
    
    @GetMapping(path = "{id}")
    public Matakuliah getMatakuliah(@PathVariable("id") Long id) {
        return matakuliahservice.getMatakuliah(id);
    }

    @PostMapping
    public void insert(@RequestBody Matakuliah matkul) {
        matakuliahservice.insert(matkul);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable("id") Long id) {
        matakuliahservice.delete(id);
    }

   @PutMapping(path = "{id}")
    public void update(@PathVariable("id") Long id,
            @RequestParam(required = false) String kode,
            @RequestParam(required = false) String nama,
            @RequestParam(required = false) int sks) {
        matakuliahservice.update(id, kode, nama, sks);
    }

}

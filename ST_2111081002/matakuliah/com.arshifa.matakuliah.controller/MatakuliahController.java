/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arshifa.matakuliah.controller;

import com.arshifa.matakuliah.entity.Matakuliah;
import com.arshifa.matakuliah.service.MatakuliahService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author WIN - 10
 */

@RestController
@RequestMapping("api/v1/matakuliah")
public class MatakuliahController {
    @Autowired
    private MatakuliahService matakuliahService;

    @GetMapping
    public List<Matakuliah> getAll() {
        return matakuliahService.getAll();
    }
    
    @GetMapping(path = "(id)")
    public Matakuliah getMatakuliah(@PathVariable("id") Long id){
        return matakuliahService.getMatakuliah(id);
//        Optional<Matakuliah> matakuliahOptional = matakuliahRepository.findById(id);
    }

    public void insert(@RequestBody Matakuliah matakuliah) {
        matakuliahService.insert(matakuliah);
    }

//    @DeleteMapping(path = "{id}")
//    public void delete(@PathVariable("id") Long id) {
//        matakuliahService.delete(id);
//    }
}

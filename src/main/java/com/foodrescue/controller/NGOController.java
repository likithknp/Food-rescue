package com.foodrescue.controller;

import com.foodrescue.entity.NGO;
import com.foodrescue.service.NGOService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ngos")
public class NGOController {

    private final NGOService service;

    public NGOController(NGOService service) {
        this.service = service;
    }

    @PostMapping
    public NGO createNgo(@RequestBody NGO ngo) {
        return service.saveNgo(ngo);
    }

    @GetMapping
    public List<NGO> getAllNgos() {
        return service.getAllNgos();
    }
}
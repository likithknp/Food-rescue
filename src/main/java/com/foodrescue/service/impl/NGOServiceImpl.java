package com.foodrescue.service.impl;

import com.foodrescue.entity.NGO;
import com.foodrescue.repository.NGORepository;
import com.foodrescue.service.NGOService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NGOServiceImpl implements NGOService {

    private final NGORepository repository;

    public NGOServiceImpl(NGORepository repository) {
        this.repository = repository;
    }

    @Override
    public NGO saveNgo(NGO ngo) {
        return repository.save(ngo);
    }

    @Override
    public List<NGO> getAllNgos() {
        return repository.findAll();
    }
}
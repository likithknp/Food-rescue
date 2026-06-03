package com.foodrescue.service;

import com.foodrescue.entity.NGO;

import java.util.List;

public interface NGOService {

    NGO saveNgo(NGO ngo);

    List<NGO> getAllNgos();
}
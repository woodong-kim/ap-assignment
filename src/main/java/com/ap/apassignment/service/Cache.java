package com.ap.apassignment.service;


import com.ap.apassignment.domain.entity.Category;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;

@Service
public class Cache {

    public static LinkedList<HashMap<String, Category>> cache = new LinkedList<>();


}

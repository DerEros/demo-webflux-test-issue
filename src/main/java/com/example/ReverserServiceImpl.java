package com.example;

import com.example.model.ReverserResult;
import org.springframework.stereotype.Service;

@Service
public class ReverserServiceImpl implements ReverserService {
    @Override
    public ReverserResult reverse(String s) {
        return new ReverserResult(s, new StringBuilder(s).reverse().toString());
    }
}

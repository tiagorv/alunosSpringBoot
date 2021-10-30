package com.bethaCode.alunos.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BigDecimalConverter {

    public BigDecimal converter(String valorDoDTO) {

        valorDoDTO = valorDoDTO
                .replace(".", "")
                .replace(",", ".");

        return new BigDecimal(valorDoDTO);
    }
}
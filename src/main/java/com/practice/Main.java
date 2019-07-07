package com.practice;

import com.practice.service.EvaluationService;
import com.practice.service.EvaluationServiceImpl;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Main {

    public static void main(String[] args) {

        EvaluationService inputService = new EvaluationServiceImpl("instructions.txt");
        log.info("Result = {}", Double.toString(inputService.getResult()));

    }
}

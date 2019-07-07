package com.practice.service;


import com.practice.util.Instructions;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

@Slf4j
public class EvaluationServiceImpl implements EvaluationService {


    @Getter
    @Setter
    private String filename;

    public EvaluationServiceImpl(String filename) {
        this.filename = filename;
    }

    private double processInstructions(Queue<String> instructionsQueue, double appliedValue) {

        double tmpResult = appliedValue;
        String instruction = "";

        for (Iterator iterator = instructionsQueue.iterator(); iterator.hasNext(); ) {
            instruction = iterator.next().toString();
            if ( instruction.contains(Instructions.ADD) ) {
                tmpResult += Double.valueOf(instruction.substring(4));
            } else if ( instruction.contains(Instructions.MULTIPLY) ) {
                tmpResult *= Double.valueOf(instruction.substring(9));
            } else if ( instruction.contains(Instructions.DIVIDE)) {
                tmpResult /= Double.valueOf(instruction.substring(7));
            } else if ( instruction.contains(Instructions.SUBTRACT) ) {
                tmpResult -= Double.valueOf(instruction.substring(9));
            }
        }

        return tmpResult;
    }

    public double getResult() {

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    this.getClass().getResourceAsStream("/" + filename)));


            Queue<String> instructionsQueue = new LinkedList<String>();

            String line;
            while ((line = br.readLine()) != null) {
                instructionsQueue.add(line);

                if ( line.contains(Instructions.APPLY) ) {
//                    log.info("Applied value = {}", Double.valueOf(line.substring(6)).toString());
                    return processInstructions(instructionsQueue, Double.valueOf(line.substring(6)));
                }
            }

        } catch (Exception e) {
            log.info("File not found");
        }


        return 0;
    }
}

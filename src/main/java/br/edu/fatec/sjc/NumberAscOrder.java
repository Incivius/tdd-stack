package br.edu.fatec.sjc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumberAscOrder {

    private CustomStack<Integer> stack;

    public NumberAscOrder(CustomStack<Integer> stack) {
        this.stack = stack;
    }

    public List<Integer> sort() throws StackEmptyException {
        List<Integer> sortedNumbers = new ArrayList<>();
        
        while (!stack.isEmpty()) {
            sortedNumbers.add(stack.pop());
        }
        
        Collections.sort(sortedNumbers);
        
        return sortedNumbers;
    }
}

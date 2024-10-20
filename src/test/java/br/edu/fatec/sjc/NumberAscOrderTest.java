package br.edu.fatec.sjc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import java.util.List;

public class NumberAscOrderTest {

    private CustomStack<Integer> stackFull;
    private CustomStack<Integer> stackEmpty;
    private CustomStack<Integer> stackWithDuplicates;
    private CustomStack<Integer> stackPartiallyFull;
    private CalculableStrategy<Integer> strategy;

    @BeforeEach
    public void setup() {
        strategy = value -> value;        

        stackFull = new CustomStack<>(6, strategy);
        try {
            stackFull.push(10);
            stackFull.push(5);
            stackFull.push(30);
            stackFull.push(15);
            stackFull.push(25);
            stackFull.push(20);
        } catch (StackFullException e) {
            e.printStackTrace();
        }

        stackEmpty = new CustomStack<>(6, strategy);

        stackWithDuplicates = new CustomStack<>(6, strategy);
        try {
            stackWithDuplicates.push(10);
            stackWithDuplicates.push(5);
            stackWithDuplicates.push(10);
            stackWithDuplicates.push(5);
            stackWithDuplicates.push(30);
            stackWithDuplicates.push(30);
        } catch (StackFullException e) {
            e.printStackTrace();
        }

        stackPartiallyFull = new CustomStack<>(6, strategy);
        try {
            stackPartiallyFull.push(20);
            stackPartiallyFull.push(10);
            stackPartiallyFull.push(15);
        } catch (StackFullException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSortWithFullStack() throws StackEmptyException {
        NumberAscOrder numberAscOrder = new NumberAscOrder(stackFull);
        List<Integer> sortedNumbers = numberAscOrder.sort();

        Assertions.assertEquals(List.of(5, 10, 15, 20, 25, 30), sortedNumbers);
    }

    @Test
    public void testSortWithEmptyStack() throws StackEmptyException {
        NumberAscOrder numberAscOrder = new NumberAscOrder(stackEmpty);
        List<Integer> sortedNumbers = numberAscOrder.sort();

        Assertions.assertTrue(sortedNumbers.isEmpty());
    }

    @Test
    public void testSortWithDuplicates() throws StackEmptyException {
        NumberAscOrder numberAscOrder = new NumberAscOrder(stackWithDuplicates);
        List<Integer> sortedNumbers = numberAscOrder.sort();

        Assertions.assertEquals(List.of(5, 5, 10, 10, 30, 30), sortedNumbers);
    }

    @Test
    public void testSortWithPartiallyFullStack() throws StackEmptyException {
        NumberAscOrder numberAscOrder = new NumberAscOrder(stackPartiallyFull);
        List<Integer> sortedNumbers = numberAscOrder.sort();

        Assertions.assertEquals(List.of(10, 15, 20), sortedNumbers);
    }

    @Test
    public void testStackIsEmptyAfterSort() throws StackEmptyException {
        NumberAscOrder numberAscOrder = new NumberAscOrder(stackFull);
        numberAscOrder.sort();

        Assertions.assertTrue(stackFull.isEmpty());
    }

    @Test
    public void testSortWithSingleElement() throws StackEmptyException, StackFullException {
        CustomStack<Integer> singleElementStack = new CustomStack<>(6, strategy);
        singleElementStack.push(42);
        
        NumberAscOrder numberAscOrder = new NumberAscOrder(singleElementStack);
        List<Integer> sortedNumbers = numberAscOrder.sort();

        Assertions.assertEquals(List.of(42), sortedNumbers);
        Assertions.assertTrue(singleElementStack.isEmpty());
    }
}

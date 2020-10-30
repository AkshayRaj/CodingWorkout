package ark.coding.books.interview_kickstart;

import ark.coding.books.interview_kickstart.recursion.NoOfBSTsWithNNodes;

public class Runner {

    public static void main(String[] args) {
        System.out.println("One:   " +
                NoOfBSTsWithNNodes.how_many_BSTs(1)
        );
        System.out.println("Two:   " +
                NoOfBSTsWithNNodes.how_many_BSTs(2)
        );
        System.out.println("Three: " +
                NoOfBSTsWithNNodes.how_many_BSTs(3)
        );
        System.out.println("Four:  " +
                NoOfBSTsWithNNodes.how_many_BSTs(4)
        );
        System.out.println("Five:  " +
                NoOfBSTsWithNNodes.how_many_BSTs(5)
        );
        System.out.println("Six:   " +
                NoOfBSTsWithNNodes.how_many_BSTs(6)
        );
        System.out.println("Seven: " +
                NoOfBSTsWithNNodes.how_many_BSTs(7)
        );
        System.out.println("Eight: " +
                NoOfBSTsWithNNodes.how_many_BSTs(8)
        );
        System.out.println("Nine:  " +
                NoOfBSTsWithNNodes.how_many_BSTs(9)
        );
        System.out.println("Ten:   " +
                NoOfBSTsWithNNodes.how_many_BSTs(10)
        );
        System.out.println("Eleven:" +
                NoOfBSTsWithNNodes.how_many_BSTs(11)
        );
        System.out.println("Twelve:" +
                NoOfBSTsWithNNodes.how_many_BSTs(12)
        );
    }
}
/**
 One:   1
 Two:   2
 Three: 5
 Four:  14
 Five:  42
 Six:   132
 Seven: 429
 Eight: 1430
 Nine:  4862
 Ten:   16796
 Eleven:58786
 Twelve:208012
 */

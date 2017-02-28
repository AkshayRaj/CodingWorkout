package ark.coding.exercise.datascience;

/**
 * Created by Akshayraj on 4/14/16.
 */
public class FriendCircle {

    static int friendCircles(String[] friends) {
        char[][] friendMatrix = convertToCharArray(friends);
        int noOfFriendCircles = 0;
        for(int studentIndex = 0 ; studentIndex < friendMatrix.length; studentIndex++){
            //if student not in any friendcircle yet
            if(friendMatrix[studentIndex][studentIndex] == 'Y') {
                noOfFriendCircles++;
                friendMatrix[studentIndex][studentIndex] = 'N';
                findMyFriendCircle(friendMatrix, studentIndex);
            }
        }
        return noOfFriendCircles;
    }

    static void findMyFriendCircle(char[][] friendMatrix, int studentNumber){
        for (int friendIndex = 0; friendIndex < friendMatrix[studentNumber].length; friendIndex++) {
            if(friendMatrix[studentNumber][friendIndex] == 'Y'){
                friendMatrix[studentNumber][friendIndex] = 'N';
                friendMatrix[friendIndex][studentNumber] = 'N';
                friendMatrix[friendIndex][friendIndex] = 'N';
                findMyFriendCircle(friendMatrix, friendIndex);
            }
        }
    }

    static char[][] convertToCharArray(String[] stringArray) {
        char[][] a1 = new char[stringArray.length][];

        for (int i = 0; i < stringArray.length; i++) {
            a1[i] = stringArray[i].toCharArray();
        }

        int total = 0;
        for (int i = 0; i < stringArray.length; i++) {
            total = total + a1[i].length;
        }

        char[] allchar = new char[total];

        // Copying the contents of the 2d array to a new 1d array
        int counter = 0; // Counter as the index of your allChar array
        for (int i = 0; i < a1.length; i++) {
            for (int j = 0; j < a1[i].length; j++) { // nested for loop - typical 2d array format
                allchar[counter++] = a1[i][j]; // copying it to the new array
            }
        }
        return a1;
    }

}

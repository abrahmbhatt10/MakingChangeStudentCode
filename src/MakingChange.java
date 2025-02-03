import java.util.Arrays;

/**
 * The class Making Change solves a classic problem:
 * given a set of coins, how many ways can you make change for a target amount?
 *
 * @author Zach Blick
 * @author Agastya Brahmbhatt
 */

public class MakingChange {
    /*
     * countWays() returns the number of ways to make change
     *  for any given total with any given set of coins.
     */
    public static long countWays(int target, int[] coins) {
        /*
            Using tabulation method, col 0 will have the coins value
            Target will be from col 1 to target.
         */
        long[][] countTable = new long[coins.length][target + 1];
        /*
            Sorting the coins from lowest to highest value.
            I got it from: https://www.geeksforgeeks.org/arrays-sort-in-java-with-examples/#
         */
        Arrays.sort(coins);
        /*
            Assigning to column 0 the values of the coins.
         */
        for(int i = 0; i < coins.length; i++){
            countTable[i][0] = coins[i];
        }
        /*
            Below is the main loop that iterates and fills in the table through tabulation method
         */
        for(int i = 0; i < coins.length; i++) {
            for(int j= 1; j < target+1; j++) {
                    countTable[i][j] = getMyCount(target,countTable,i,j);
                    /*
                        Below is print code for debugging purposes.
                     */
                    //System.out.println("coin "+countTable[i][0]+" pos "+i+":"+j+" value "+countTable[i][j]);
            }
        }
        /*
            Below returns the final entry in the table
         */
        return countTable[coins.length-1][target];
    }
    public static long getMyCount(int target, long[][] countTable, int row, int col) {
        if(row < 0 || col < 0)
        {
            return 0;
        }
        if(row >= countTable.length){
            return 0;
        }
        if(col >= countTable[0].length){
            return 0;
        }
        if(col == 0)
        {
            //target is 0
            return 1;
        }
        long myCount = 0, excludeCount = 0, includeCount = 0;
        int coin = (int) countTable[row][0];
        int myTarget = col;
        if(coin > myTarget){
            myCount = 0;
        }
        else{
            if((myTarget % coin) == 0){
                myCount = 1;
            }
        }
        if(col - coin > 0) {
            excludeCount = countTable[row][col-coin];
            if(col % coin == 0) {
                myCount = 0;
            }
        }
        if(row-1 >= 0) {
            includeCount = countTable[row-1][col];
        }
        long myTotalCount = myCount+excludeCount+includeCount;
        return myTotalCount;
    }
}

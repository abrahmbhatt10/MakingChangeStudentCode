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
         * countWays() returns the number of ways to make change
         *  for any given total with any given set of coins.
         */
        //memoisation
        //return countWays_memoisation(target, coins);
        //tabulation
        return countWays_tabulation(target, coins);
    }

    //bottom up approach
    public static long countWays_memoisation(int target, int[] coins) {
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
            Initializes every cell to -1.
         */
        for(int i = 0; i < coins.length; i++){
            for(int j=1; j < target+1; j++) {
                countTable[i][j] = -1;
            }
        }
        /*
            Memoization goes from bottom up.
            passing the last cell parameters.
         */
        return countMemo(target,countTable,coins.length-1,target);
    }
    /*
        countMemo is a recursive function that returns the count ways to add up to the target.
     */
    public static long countMemo(int target, long[][] countTable, int row, int col) {
        /*
            Below are boundary conditions
         */
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
        /*
            If we have calculated the value previously, retrieve from the array and return.
         */
        if(countTable[row][col] != -1) {
            return countTable[row][col];
        }
        /*
            myCount is 1 when coin is a factor of target
            excludeCount is the variable that represents value returned by exclude function
            includeCount is the variable that stores valued returned by include function
         */
        long myCount = 0, excludeCount = 0, includeCount = 0;
        /*
            Below, coin value is retrieved.
         */
        int coin = (int) countTable[row][0];
        /*
            Target is retrieved.
         */
        int myTarget = col;
        if(coin > myTarget){
            myCount = 0;
        }
        else{
            if((myTarget % coin) == 0){
                myCount = 1;
            }
        }
        /*
            Below executes include function
         */
        if(col - coin > 0) {
            /*
                includeCount value is keeping the coin the same, while reducing target value.
             */
            if(countTable[row][col-coin] != -1) {
                /*
                    includeCount already calculated.
                 */
                includeCount = countTable[row][col-coin];
            } else {
                /*
                    includeCount not calculated, so calling the function.
                 */
                includeCount = countMemo(target,countTable,row, col-coin);
            }

            /*
                Below prevents double counting for any integer and its multiples
                If the target is a multiple of a coin, then myCount already counts for that arrangement, which is 1.
                However, excludeCount ends up counting for that arrangement (or way of reaching target) as well, so we have to make myCount 0 again.
             */
            if(col % coin == 0) {
                myCount = 0;
            }
        }
        /*
            Below retrieves exclude function value
         */
        if(row-1 >= 0) {
            /*
                excludeCount keeps target the same, while looking at the previous coin.
             */
            if(countTable[row-1][col] != -1) {
                /*
                    excludeCount previously calculated.
                 */
                excludeCount = countTable[row-1][col];
            }
            else {
                /*
                    excludeCount not calculated, so keeping the target same while going to the previous coin.
                 */
                excludeCount = countMemo(target, countTable,row-1, col);
            }
        }
        /*
            adds up all the ways and returns.
         */
        long myTotalCount = myCount+excludeCount+includeCount;
        /*
            We have calculated the count, storing it in table.
         */
        countTable[row][col] = myTotalCount;
        return myTotalCount;
    }

    //top down approach
    public static long countWays_tabulation(int target, int[] coins) {
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
        /*
            Below are boundary conditions
         */
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
        /*
            myCount is 1 when coin is a factor of target
            excludeCount is the variable that represents value returned by exclude function
            includeCount is the variable that stores valued returned by include function
         */
        long myCount = 0, excludeCount = 0, includeCount = 0;
        /*
            Below, coin value is retrieved.
         */
        int coin = (int) countTable[row][0];
        /*
            Target is retrieved.
         */
        int myTarget = col;
        if(coin > myTarget){
            myCount = 0;
        }
        else{
            if((myTarget % coin) == 0){
                myCount = 1;
            }
        }
        /*
            Below executes include function
         */
        if(col - coin > 0) {
            /*
                includeCount value is keeping the coin the same, while reducing target value.
             */
            includeCount = countTable[row][col-coin];
            /*
                Below prevents double counting for any integer and its multiples
                If the target is a multiple of a coin, then myCount already counts for that arrangement, which is 1.
                However, excludeCount ends up counting for that arrangement (or way of reaching target) as well, so we have to make myCount 0 again.
             */
            if(col % coin == 0) {
                myCount = 0;
            }
        }
        /*
            Below retrieves exclude function value
         */
        if(row-1 >= 0) {
            /*
                excludeCount keeps target the same, while looking at the previous coin.
             */
            excludeCount = countTable[row-1][col];
        }
        /*
            adds up all the ways and returns.
         */
        long myTotalCount = myCount+excludeCount+includeCount;
        return myTotalCount;
    }
}

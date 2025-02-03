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
        long[][] countTable = new long[coins.length + 1][target + 2];
        for(int i = 0; i < countTable.length; i++){
            for(int j= 0; j < countTable[0].length; j++)
            {
                countTable[i][j] = -1;
            }
        }
        for(int i = 1; i < coins.length + 1; i++){
            countTable[i][0] = coins[i - 1];
        }
        return count(target, countTable, coins.length, target + 1);
    }

    public static long count(int target, long[][] countTable, int row, int col){
        if((row < 0) || (row >= countTable.length)){
            return 0;
        }
        if((col < 0) || (col >= countTable[0].length)){
            return 0;
        }
        if(col == 0)
        {
            return 0;
        }
        if(col == 1){
            //target is 0
            return 0;
        }
        if(countTable[row][col] != -1){
            return countTable[row][col];
        }
        long myCount = 0;
        int coin = (int) countTable[row][0];
        int myTarget = col - 1;
        if(coin > myTarget){
            myCount = 0;
        }
        else{
            if((myTarget % coin) == 0){
                myCount = 1;
            }
        }
        long myTotalCount =  myCount + count(target, countTable, row, col - coin) + count(target, countTable, row - 1, col);
        countTable[row][col] = myTotalCount;
        return myTotalCount;
    }
}

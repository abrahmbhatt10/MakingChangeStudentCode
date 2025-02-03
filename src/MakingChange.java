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
        long[][] countTable = new long[coins.length][target + 1];
        Arrays.sort(coins);
        for(int i = 0; i < coins.length; i++){
            countTable[i][0] = coins[i];
        }
        int coin = 0;
        int myTarget = 0;
        int myCount = 0;
        for(int i = 0; i < coins.length; i++) {
            for(int j= 1; j < target+1; j++) {
                    countTable[i][j] = getMyCount(target,countTable,i,j);
                    //System.out.println("coin "+countTable[i][0]+" pos "+i+":"+j+" value "+countTable[i][j]);
            }
        }
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

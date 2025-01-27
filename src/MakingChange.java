/**
 * The class Making Change solves a classic problem:
 * given a set of coins, how many ways can you make change for a target amount?
 *
 * @author Zach Blick
 * @author Agastya Brahmbhatt
 */

public class MakingChange {
    /**
     * TODO: Complete this function, countWays(), to return the number of ways to make change
     *  for any given total with any given set of coins.
     */
    public static long countWays(int target, int[] coins) {
        int numWays = 0;
        for(int i = 0; i < coins.length; i++){
            while((target % coins[i]) != target){
                if((target % coins[i]) == 0){
                    numWays++;
                }
                else{
                    coins[i] = 0;
                    return countWays(target, coins);
                }
            }
        }
        return numWays;
    }
}

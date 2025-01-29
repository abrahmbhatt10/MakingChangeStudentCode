import java.util.ArrayList;
import java.util.HashSet;

/**
 * The class Making Change solves a classic problem:
 * given a set of coins, how many ways can you make change for a target amount?
 *
 * @author Zach Blick
 * @author Agastya Brahmbhatt
 */

public class MakingChange {
    private static long retCount = 0;
    /**
     * countWays() returnS the number of ways to make change
     *  for any given total with any given set of coins.
     */
    public static long countWays(int target, int[] coins) {
          multiplier(coins,0,0, target, 0);
          return retCount;
    }

    public static void multiplier(int[] coins, int currentIterator, int number, long currentTarget, long pSum) {
        if(currentIterator >= coins.length)
        {
            return;
        }
        if (number > 2500)
        {
            return;
        }
        int coin = coins[currentIterator];
        int currentMultiplier = 0;
        long mypartialSum = 0;
        int mynumber = 0;
        currentMultiplier = (coin * number);
        if((pSum+currentMultiplier) > currentTarget) {
            return;
        }
        if((pSum+currentMultiplier) == currentTarget)
        {
            retCount++;
        } else {
            multiplier(coins,currentIterator+1,0,currentTarget, pSum+currentMultiplier);
        }
        multiplier(coins,currentIterator, number+1,currentTarget, pSum);
    }


    /*
    Returns 1, or True, if coin is a factor.
    Below is one way by using a single coin.
     */
    public static long countOne(int target, int coin){
        int ret = 0;
        if((target % coin) == 0){
            ret = 1;
        }
        return ret;
    }

    public static long countTwo(int target, int firstCoin, int secondCoin){
        int ret = 0;
        for(int i = 0; i < 2500; i++){
            for(int j = 0; j < 2500; j++){
                if((i * firstCoin) + (j * secondCoin) == target){
                    ret++;
                }
            }
        }
        return ret;
    }

    public static long countThree(int target, int firstCoin, int secondCoin, int thirdCoin){
        int ret = 0;
        for(int i = 0; i < 2500; i++){
            for(int j = 0; j < 2500; j++){
                for(int k = 0; k < 2500; k++){
                    if((i * firstCoin) + (j * secondCoin) + (k * thirdCoin) == target){
                        ret++;
                    }
                }
            }
        }
        return ret;
    }

}

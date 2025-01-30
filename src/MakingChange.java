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
          retCount = 0;
          multiplier_coin(coins,target,0, 0);
          return retCount;
    }

    public static void multiplier_coin(int[] coins, long currentTarget, int currentIterator,long partialSum) {
        if (currentIterator >= coins.length) {
            return;
        }
        if(partialSum > currentTarget){
            return;
        }
        multiplier_qty(coins, currentTarget, currentIterator, partialSum, 0);
        multiplier_coin(coins, currentTarget, currentIterator + 1, partialSum);
    }
    public static void multiplier_qty(int[] coins, long currentTarget, int currentIterator,long partialSum, int qty) {
        if (qty > 2500)
        {
            return;
        }
        if(currentIterator >= coins.length){
            return;
        }
        if(partialSum >= currentTarget){
            return;
        }
        int coin = coins[currentIterator];
        int currentMultiplier = coin * qty;
        if(partialSum+currentMultiplier == currentTarget){
            retCount++;
            return;
        }
        if(partialSum > currentTarget || partialSum+currentMultiplier > currentTarget){
            return;
        }
        multiplier_qty(coins,currentTarget,currentIterator,partialSum,qty+1);
        if((partialSum+currentMultiplier) > 0) {
            multiplier_prefix(coins,currentTarget,currentIterator+1,partialSum+currentMultiplier,0);
        }
    }
    public static void multiplier_prefix(int[] coins, long currentTarget, int currentIterator,long partialSum, int qty) {
        if (qty > 2500)
        {
            return;
        }
        if(currentIterator >= coins.length){
            return;
        }
        if(partialSum >= currentTarget){
            return;
        }
        multiplier_qty(coins,currentTarget,currentIterator,partialSum,0);
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

/**
 * The class Making Change solves a classic problem:
 * given a set of coins, how many ways can you make change for a target amount?
 *
 * @author Zach Blick
 * @author Agastya Brahmbhatt
 */

public class OldMakingChange {
    private static long retCount = 0;
    /**
     * countWays() returns the number of ways to make change
     *  for any given total with any given set of coins.
     */
    public static long countWays(int target, int[] coins) {
          retCount = 0;
          multiplier_coin(coins,target,0, 0);
          return retCount;
    }

    /**
     * multiplier_coin is a recursive function that returns void
     * It for-loops through the coins list
     * It calls the nested loop function multiplier_qty
     * I reviewed the code at: https://stackoverflow.com/questions/44397474/convert-nested-loop-to-recursion
     * I did this to do the nested for loop recursion.
     * The multiplier_coin is the outer-most for loop in recursive form
     */
    public static void multiplier_coin(int[] coins, long currentTarget, int currentIterator,long partialSum) {
        /**
         * Base case: After all coins checked, it returns.
         */
        if (currentIterator >= coins.length) {
            return;
        }
        if(partialSum > currentTarget){
            return;
        }
        /**
         * multiplier_qty is the inner loop that goes through different quantity amounts for different coins.
         */
        multiplier_qty(coins, currentTarget, currentIterator, partialSum, 0);
        /**
         * multiplier_coin calls itself with the next coin in the list.
         */
        multiplier_coin(coins, currentTarget, currentIterator + 1, partialSum);
    }

    /**
     * @param coins
     * @param currentTarget
     * @param currentIterator
     * @param partialSum
     * @param qty
     *
     * This is the inner nested loop in the recursion.
     * It increments the class static variable recCount when the sum matches target
     */
    public static void multiplier_qty(int[] coins, long currentTarget, int currentIterator,long partialSum, int qty) {
        /**
         * Base case using constraint of 2500 according to the max value of target
         *
         */
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
        /**
         *  Calculate the current multiplier for the current selected coin and the quantity value.
         *
         */
        int coin = coins[currentIterator];
        int currentMultiplier = coin * qty;
        /**
         *  The variable partialSum is used to send the value of the earlier coins.
         */
        if(partialSum+currentMultiplier == currentTarget){
            retCount++;
            return;
        }
        if(partialSum > currentTarget || partialSum+currentMultiplier > currentTarget){
            return;
        }
        /**
         *  Calls itself with the next quantity value
         */
        multiplier_qty(coins,currentTarget,currentIterator,partialSum,qty+1);
        /**
         *  Calls the next coin using the revised partial sum.
         */
        if((partialSum+currentMultiplier) > 0) {
            multiplier_prefix(coins,currentTarget,currentIterator+1,partialSum+currentMultiplier,0);
        }
    }

    /**
     * @param coins
     * @param currentTarget
     * @param currentIterator
     * @param partialSum
     * @param qty
     *
     * Used to call multiplier quantity with different partial sum values that are based on different quantities of earlier coins.
     *
     */
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
}

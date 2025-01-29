import java.util.ArrayList;

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
        ArrayList<Integer> coinsArr = new ArrayList<Integer>(coins);

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
        for(int i = 0; i < )
        return ret;
    }



}

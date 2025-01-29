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

    public static long count(int target, int[] coins){
        int ret = 0;

    }

    public static long multiplier(int coin, int number) {
        return coin * number;
    }


}

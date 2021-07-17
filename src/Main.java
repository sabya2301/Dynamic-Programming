public class Main {

    public static void main(String[] args) {
//        fibonacci(11);

//        System.out.println(climbingStairsRecursion(10));
//        System.out.println(climbingStairsDP(10));

//        System.out.println(robHouseRecursion(0, new int[]{1,4,3,5,100,101}, new int[7]));
//        System.out.println(robHousesTopDownApproah(new int[]{1,4,3,5,100,101}));

//        System.out.println(longestIncreasingSubsequence(new int[]{10,22,9,33,21,50,41,60,80,3}));
//        System.out.println(longestSumSubsequence(new int[]{10,22,9,33,21,50,41,60,80,3}));
//        System.out.println(longestBitonicSeq(new int[]{10,22,9,33,21,50,41,60,80,3}));


    }

//-----------------------------------------------------------------------------------
    // OPTIMISED FIBONACCI METHOD. S.C - O(1), T.C - O(n)
    public static void fibonacci(int n) {
        int a = 0, b = 1, c = 0;
        for (int i = 2; i < n; ++i) {
            c = a + b;
            a = b;
            b = c;
        }
        System.out.println(n + "-th fibonacci number is " + c);
    }


//-----------------------------------------------------------------------------------
    // CLIMBING STAIRS PROBLEM(can climb 1 or 2 stairs at a time. Find the maximum number of ways to reach a stair)
    // SC - O(N^2) TC- O(N^2)
    public static int climbingStairsRecursion(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        return (climbingStairsRecursion(n - 1) + climbingStairsRecursion(n - 2));
    }

    // SC - O(1), TC - O(n)
    public static int climbingStairsDP(int n) {
        int a = 1, b = 2, c = 0;
        if (n == 1) return a;
        if (n == 2) return b;
        for (int i = 3; i <= n; ++i) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }
//-----------------------------------------------------------------------------------

    //ROBBING HOUSES - Cannot rob two adjacent houses. Find max money that can be robbed.
    public static int robHouseRecursion(int i, int[] arr, int[] strg) {
        if (i >= arr.length) return 0;
        if (strg[i] != 0) return strg[i];
        int rob1 = robHouseRecursion(i + 1, arr, strg);
        int rob2 = robHouseRecursion(i + 2, arr, strg) + arr[i];
        int rob = Math.max(rob1, rob2);
        strg[i] = rob;
        return rob;
    }

    public static int robHousesTopDownApproah(int[] arr) {
        int[] strg = new int[arr.length + 1];
        strg[arr.length] = 0;
        strg[arr.length - 1] = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; --i) {
            strg[i] = Math.max(strg[i + 1], strg[i + 2] + arr[i]);
        }
        return strg[0];
    }

//-----------------------------------------------------------------------------------

    //LONGEST INCREASING SUBSEQUENCE IN AN ARRAY
    public static int longestIncreasingSubsequence(int[] arr) {
        int[] dp = new int[arr.length];
        int maxLIS = 0;
        for (int i = 0; i < arr.length; ++i) {
            int currMaxLIS = 0;
            for (int j = 0; j < i; ++j) {
                if (arr[i] > arr[j]) {
                    if (dp[j] > currMaxLIS)
                        currMaxLIS = dp[j];
                }
            }
            dp[i] = currMaxLIS + 1;
            if (dp[i] > maxLIS)
                maxLIS = dp[i];
        }
        for(int i=0; i<arr.length; ++i){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        for(int i=0; i<arr.length; ++i){
            System.out.print(dp[i] + "  ");
        }
        System.out.println();
        return maxLIS;
    }

//-----------------------------------------------------------------------------------
    //LONGEST SUM SUBSEQUENCE -> Print the largest sum possible such that numbers are in increasing order
    //SC - O(N), TC - O(N^2)
    public static int longestSumSubsequence(int[] arr) {
        int max = Integer.MIN_VALUE;
        int[] dp = new int[arr.length];

        for (int i = 0; i < arr.length; ++i) {
            for (int j = 0; j < i; ++j) {
                if (arr[i] > arr[j]) {
                    if ((arr[i] + dp[j]) > dp[i])
                        dp[i] = arr[i] + dp[j];
                }
            }
            if (dp[i] == 0)
                dp[i] = arr[i];
            if (dp[i] > max)
                max = dp[i];
        }
        return max;

    }
//-----------------------------------------------------------------------------------
    // LONGEST BITONIC SEQUENCE
    public static int longestBitonicSeq(int[] arr){
        int[] LIS = new int[arr.length];    //DP array to store Longest Increasing Seq. Array
        int[] LDS = new int[arr.length];    //DP array to store Longest Decreasing Seq. Array
        int maxBitonic = 0;

        for(int i=0; i<arr.length; ++i){    //computing the LIS
            int currMaxLis = 0;
            for(int j=0; j<i; ++j){
                if(arr[i] > arr[j]){
                    if(LIS[j] > currMaxLis)
                        currMaxLis = LIS[j];
                }
            }
            LIS[i] = currMaxLis + 1;
        }

        for(int i=arr.length-1; i>=0; --i){    //computing the LDS
            int currMaxLDS = 0;
            for(int j=arr.length-1; j>i; --j){
                if(arr[i] > arr[j]){
                    if(LDS[j] > currMaxLDS)
                        currMaxLDS = LDS[j];
                }
            }
            LDS[i] = currMaxLDS + 1;
        }

        for(int i=0; i<arr.length; ++i){
            if(LDS[i] + LIS[i] -1 > maxBitonic)
                maxBitonic = LDS[i] + LIS[i] -1;
        }

        for(int i=0; i<arr.length; ++i){
            System.out.print(arr[i] + " ");
        }
        System.out.println();


        for(int i=0; i<arr.length; ++i){
            System.out.print(LIS[i] + "  ");
        }
        System.out.println();

        for(int i=0; i<arr.length; ++i){
            System.out.print(LDS[i] + "  ");
        }
        System.out.println();
        return maxBitonic;

    }
//-----------------------------------------------------------------------------------
    /* MAX NO. OF NON-OVERLAPPING BRIDGE -> Given co-ordinates of bridges, find max no. of bridges that do no overlap
       [6,2],[4,3], [2,6], [1,5] -> x,y co-ordinates.
       ans -> 2
    */

    
}
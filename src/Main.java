import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
//        fibonacci(11);

//        System.out.println(climbingStairsRecursion(10));
//        System.out.println(climbingStairsDP(10));
//        System.out.println(climbStairsVariable(new int[]{3,3,0,2,2,3}));
//        System.out.println(minStepRequired(new int[]{3,2,4,2,0,2,3,1,2,2}));

//        System.out.println(robHouseRecursion(0, new int[]{1,4,3,5,100,101}, new int[7]));
//        System.out.println(robHousesTopDownApproach(new int[]{1,4,3,5,100,101}));

//        System.out.println(longestIncreasingSubsequence(new int[]{10,22,9,33,21,50,41,60,80,3}));
//        System.out.println(longestSumSubsequence(new int[]{10,22,9,33,21,50,41,60,80,3}));
//        System.out.println(longestBitonicSeq(new int[]{10,22,9,33,21,50,41,60,80,3}));

//        System.out.println(nonOverlappingBridges(new int[][]{{6, 2}, {4, 3}, {2, 6}, {1, 5}}));
//        System.out.println(russianDoll(new int[][]{ {17,5},{26,18} , {25,34}, {48,84}, {63,72}, {42,86} }));

//        System.out.println(palindromicSubstringCount("abccbc"));

//        System.out.println(distinctSubstringCount("abcbac"));

//        System.out.println(leastCostlyPath(new int[][]{
//                {2, 6, 1, 1, 3},
//                {9, 1, 1, 0, 5},
//                {0, 7, 5, 2, 0},
//                {4, 3, 0, 4, 7},
//                {2,0, 1, 4, 7}
//        }));
//        System.out.println(goldMineExtract(new int[][]{
//                    {3, 2, 3, 1},
//                    {2, 4, 6, 0},
//                    {5, 0, 1, 3},
//                    {9, 1, 5, 1}
//            }));

//        System.out.println(targetSumSubset(new int[]{4,2,3,1,7}, 4 ));
//        System.out.println(knapsack0_1(new int[]{2,5,1,3,4}, new int[]{15,14,10,45,30}, 9));
//        System.out.println(unboundedKnapsnack(new int[]{2,5,1,3,4}, new int[]{15,14,10,45,30}, 7));

//        System.out.println(stock_01(new int[]{7,1,5,3,6,4}));
//        System.out.println(stock_02(new int[]{7,1,5,3,6,4}));

//        System.out.println(maxSquareSubMatrix(new int[][]{
//                {0,1,0,1,0,1}, {1,0,1,0,1,0}, {0,1,1,1,1,1}, {0,0,1,1,1,0}, {1,1,1,1,1,1}
//        }));

//        System.out.println(artihmaticArrays(new int[]{2,5,9,12,15,18,22,26,30,34,36,38,40,41}));

//        System.out.println(coinChangeCombinations(new int[]{3, 2 ,5} , 7));
//        System.out.println(coinChangePermutations(new int[]{3, 2 ,5} , 7));

//        System.out.println(binaryStrings(3));
//        System.out.println(maxSumNoAdjacent(new int[]{5,10,10,100,5}));
        System.out.println(paintHouse(new int[][]{
                {1,5,7},
                {5,8,4} ,
                {3,2,9},
                {1,2,4}
        }));
    }

//-----------------------------------------------------------------------------------
    // 1) OPTIMISED FIBONACCI METHOD. S.C - O(1), T.C - O(n)
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
    // 2) CLIMBING STAIRS PROBLEM 1(can climb 1 or 2 stairs at a time. Find the maximum number of ways to reach a stair)
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
    // 3) CLIMBING STAIRS-2 -> Given an array representing no. of steps that can be jumped from that step, find paths
    // possible to reach the top
    public static int climbStairsVariable(int[] steps){
        int n = steps.length;
        int[] dp = new int[n+1];
        dp[n] = 1;
        for(int i=n-1; i>=0; --i){
            for(int j=1; j<=steps[i]; ++j){
                if(i+j < dp.length){
                    dp[i] += dp[i+j];
                }
            }
        }
        return dp[0];
    }

//-----------------------------------------------------------------------------------
    // 4) CLIMBING STAIRS-3 -> Minimum Steps required to reach the final step
    public static int minStepRequired(int [] steps){
        int n = steps.length;
        int[] dp = new int[n+1];
        for(int i=0; i<dp.length; ++i)
            dp[i]=-1;
        dp[n] = 0;
        for(int i=n-1; i>=0; --i){
            if(steps[i] < 0) continue;
            int min = Integer.MAX_VALUE;
            for(int j=1; j<=steps[i] && i+j <dp.length; ++j){
                if(dp[i+j] != -1)
                    min = Math.min(dp[i+j], min);
            }
            if(min != Integer.MAX_VALUE)
                dp[i] = min+1;
        }
        for(int i=0; i<steps.length; ++i) System.out.print(steps[i] + "  " );
        System.out.println();

        for(int i=0; i<dp.length; ++i) System.out.print(dp[i] + "  " );
        System.out.println();
        return dp[0];
    }

//-----------------------------------------------------------------------------------
    // 5) ROBBING HOUSES - Cannot rob two adjacent houses. Find max money that can be robbed.
    public static int robHouseRecursion(int i, int[] arr, int[] strg) {
        if (i >= arr.length) return 0;
        if (strg[i] != 0) return strg[i];
        int rob1 = robHouseRecursion(i + 1, arr, strg);
        int rob2 = robHouseRecursion(i + 2, arr, strg) + arr[i];
        int rob = Math.max(rob1, rob2);
        strg[i] = rob;
        return rob;
    }

    public static int robHousesTopDownApproach(int[] arr) {
        int[] strg = new int[arr.length + 1];
        strg[arr.length] = 0;
        strg[arr.length - 1] = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; --i) {
            strg[i] = Math.max(strg[i + 1], strg[i + 2] + arr[i]);
        }
        return strg[0];
    }

//-----------------------------------------------------------------------------------

    // 6) LONGEST INCREASING SUBSEQUENCE IN AN ARRAY
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
        for (int i = 0; i < arr.length; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < arr.length; ++i) {
            System.out.print(dp[i] + "  ");
        }
        System.out.println();
        return maxLIS;
    }

//-----------------------------------------------------------------------------------
    // 7) LONGEST SUM SUBSEQUENCE -> Print the largest sum possible such that numbers are in increasing order
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
    // 8) LONGEST BITONIC SEQUENCE
    public static int longestBitonicSeq(int[] arr) {
        int[] LIS = new int[arr.length];    //DP array to store Longest Increasing Seq. Array
        int[] LDS = new int[arr.length];    //DP array to store Longest Decreasing Seq. Array
        int maxBitonic = 0;

        for (int i = 0; i < arr.length; ++i) {    //computing the LIS
            int currMaxLis = 0;
            for (int j = 0; j < i; ++j) {
                if (arr[i] > arr[j]) {
                    if (LIS[j] > currMaxLis)
                        currMaxLis = LIS[j];
                }
            }
            LIS[i] = currMaxLis + 1;
        }

        for (int i = arr.length - 1; i >= 0; --i) {    //computing the LDS
            int currMaxLDS = 0;
            for (int j = arr.length - 1; j > i; --j) {
                if (arr[i] > arr[j]) {
                    if (LDS[j] > currMaxLDS)
                        currMaxLDS = LDS[j];
                }
            }
            LDS[i] = currMaxLDS + 1;
        }

        for (int i = 0; i < arr.length; ++i) {
            if (LDS[i] + LIS[i] - 1 > maxBitonic)
                maxBitonic = LDS[i] + LIS[i] - 1;
        }

        for (int i = 0; i < arr.length; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();


        for (int i = 0; i < arr.length; ++i) {
            System.out.print(LIS[i] + "  ");
        }
        System.out.println();

        for (int i = 0; i < arr.length; ++i) {
            System.out.print(LDS[i] + "  ");
        }
        System.out.println();
        return maxBitonic;

    }

//-----------------------------------------------------------------------------------
    /* 9) MAX NO. OF NON-OVERLAPPING BRIDGE -> Given co-ordinates of bridges, find max no. of bridges that do no overlap
       [6,2],[4,3], [2,6], [1,5] -> x,y co-ordinates.
       ans -> 2
    */
    // SC- O(N), T.C- O(N^2)
    public static int nonOverlappingBridges(int[][] arr) {

        class Bridges implements Comparable<Bridges> {  //Personalised comparator to sort the bridges array using their north coordinate.
            int north, south;

            public Bridges(int north, int south) {
                this.north = north;
                this.south = south;
            }

            @Override
            public int compareTo(Bridges bridge) {
                return this.north - bridge.north;
            }
        }

        Bridges[] bridges = new Bridges[arr.length];
        int[] LIS = new int[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            bridges[i] = new Bridges(arr[i][0], arr[i][1]);
        }
        Arrays.sort(bridges);
        int nonOverlappingBridgesCount = 0;
//        for(int i=0; i<arr.length; ++i){
//            System.out.println(bridges[i].north + " " + bridges[i].south);
//        }
//        System.out.println("=================");
        for (int i = 0; i < arr.length; ++i) {
            int currMax = 0;
            for (int j = 0; j < i; ++j) {
                if (bridges[i].south > bridges[j].south) {
                    if (LIS[j] > currMax)
                        currMax = LIS[j];
                }
            }
            LIS[i] = currMax + 1;
            if (LIS[i] > nonOverlappingBridgesCount)
                nonOverlappingBridgesCount = LIS[i];
        }
        return nonOverlappingBridgesCount;
    }

//-----------------------------------------------------------------------------------
    /* 10) RUSSIAN DOLL PROBLEM/ ENVELOPE PROBLEM -> Given the length and breadth of envelopes,
            find the maximum number of envelopes that can be nested    */
    // SC- O(N), T.C- O(N^2)
    public static int russianDoll(int[][] arr){
        class RussianDoll implements Comparable<RussianDoll>{  //Personalised comparator to sort the dolls array using their first dimension.
            int length, breadth;

            public RussianDoll(int length, int breadth) {
                this.length = length;
                this.breadth = breadth;
            }

            @Override
            public int compareTo(RussianDoll russianDoll) {
                if(this.length == russianDoll.length) return this.breadth - russianDoll.breadth;
                return this.length - russianDoll.length;
            }
        }

        RussianDoll[] russianDolls = new RussianDoll[arr.length];
        int[] LIS = new int[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            russianDolls[i] = new RussianDoll(arr[i][0], arr[i][1]);
        }
        Arrays.sort(russianDolls);
        int nestedDollsCount = 0;
        for (int i = 0; i < arr.length; ++i) {
            int currMax = 0;
            for (int j = 0; j < i; ++j) {
                if (russianDolls[i].breadth > russianDolls[j].breadth &&
                        russianDolls[i].breadth > russianDolls[j].breadth) { //both dimensions should be strictly lower
                    if (LIS[j] > currMax)
                        currMax = LIS[j];
                }
            }
            LIS[i] = currMax + 1;
            if (LIS[i] > nestedDollsCount)
                nestedDollsCount = LIS[i];
        }
        return nestedDollsCount;
    }

//-----------------------------------------------------------------------------------
    // 11) PALINDROMIC SUB-STRING
    // SC- O(N^2), TC- O(N^2)
    public static int palindromicSubstringCount(String str){
        int[][] dp = new int[str.length()][str.length()];
        int count = 0;
        String palindromicString = "";
        for(int g=0; g<str.length(); ++g){
            for(int i=0, j=g; j<str.length(); ++i, ++j){
                if(i==j){
                    dp[i][j] = 1;
                }

                else if(str.charAt(i) == str.charAt(j)){
                    if((j-i) == 1){
                        dp[i][j] = 1;
                    }
                    else if(dp[i+1][j-1] == 1){
                        dp[i][j] = 1;
                    }
                }
                if(dp[i][j] == 1){
                    count++;
                    palindromicString += str.substring(i,j+1) + "\n";
                }
            }
        }
        for(int i=0; i<str.length(); ++i){
            for(int j=0; j<str.length(); ++j){
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println(palindromicString);
        return count;
    }

//-----------------------------------------------------------------------------------
    /* 12) LEAST COSTLY PATH -> Given a 2D matrix, find the least costly path to move from top left corner to bottom left.
           You can only move in horizontal or vertical path. Cost of a cell is its value. */
    //watch riya bansal dp-4 for future reference
    public static String leastCostlyPath(int[][] arr){
        class MatrixCell{
            int i,j;
            String path;

            public MatrixCell(int i, int j, String path) {
                this.i = i;
                this.j = j;
                this.path = path;
            }
        }

        int[][] dp = new int[arr.length][arr[0].length];

        for(int i=dp.length-1; i>=0; --i){
            for(int j=dp[0].length-1; j>=0; --j){
                if((i== (dp.length-1)) && j == (dp[0].length-1)){
                    dp[i][j] = arr[i][j];
                } else if(i==dp.length-1){
                    dp[i][j] = arr[i][j] + dp[i][j+1];
                } else if(j == dp[0].length-1){
                    dp[i][j] = arr[i][j] + dp[i+1][j];
                } else{
                    dp[i][j] = arr[i][j] + Math.min(dp[i+1][j], dp[i][j+1]);
                }
            }
        }

        String resultantPath = dp[0][0] + "\n";

        //implementing BFS
        Queue<MatrixCell> queue = new LinkedList<MatrixCell>();
        queue.add(new MatrixCell(0,0,""));
        while(!queue.isEmpty()){
            MatrixCell currentCell = queue.remove();
            if((currentCell.i == (dp.length-1) && currentCell.j == (dp[0].length-1))){
                resultantPath += currentCell.path +"\n";
            } else if(currentCell.i == dp.length-1){
                queue.add(new MatrixCell(currentCell.i,currentCell.j+1, currentCell.path + "H"));
            } else if(currentCell.j == dp[0].length-1) {
                queue.add(new MatrixCell(currentCell.i+1, currentCell.j , currentCell.path + "V"));
            } else{
                if(dp[currentCell.i][currentCell.j+1] < dp[currentCell.i+1][currentCell.j]){
                    queue.add(new MatrixCell(currentCell.i, currentCell.j+1, currentCell.path+"H"));
                } else if(dp[currentCell.i][currentCell.j+1] > dp[currentCell.i+1][currentCell.j]){
                    queue.add(new MatrixCell(currentCell.i+1, currentCell.j, currentCell.path+"V"));
                } else{
                    queue.add(new MatrixCell(currentCell.i, currentCell.j+1, currentCell.path+"H"));
                    queue.add(new MatrixCell(currentCell.i+1, currentCell.j, currentCell.path+"V"));
                }
            }
        }
        return resultantPath;

    }
//-----------------------------------------------------------------------------------
    // 13) COUNT NUMBER OF DISTINCT SUBSTRINGS.
    // TC - O(N), SC- O(N)
    public static int distinctSubstringCount(String str){
        int[] dp = new int[str.length()+1];
        dp[0] = 1;
        HashMap<Character, Integer> lastIndex = new HashMap<Character, Integer>();
        for(int i=1; i<dp.length; ++i){
            Character c = str.charAt(i-1);
            if(lastIndex.containsKey(c)){
                dp[i] = (2 * dp[i-1]) - dp[lastIndex.get(c)-1];
            } else {
                dp[i] = 2 * dp[i-1];
            }
            lastIndex.put(c,i);
        }
        return dp[dp.length-1];
    }
//-----------------------------------------------------------------------------------
    // 14) MAX GOLD EXTRACTED -> Starting from anywhere on the first column, find the maximum gold that can be extracted,
    //      given that you can only move diagonally forward and horizantally forward.
    //      Also print all the possible paths.
    // TC - O(M*N), S.C - O(M*N)
    public static String goldMineExtract(int[][] arr){

        class GoldMineCell{
            int i, j;
            String path;

            public GoldMineCell(int i, int j, String path) {
                this.i = i;
                this.j = j;
                this.path = path;
            }
        }

        int[][] dp = new int[arr.length][arr[0].length];

        for(int j = dp[0].length-1; j>=0; --j){
            for(int i=dp.length-1; i>=0; --i){
                if(j == dp.length-1){
                    dp[i][j] = arr[i][j];
                } else if(i == 0){
                    dp[i][j] = arr[i][j] + Math.max(dp[i][j+1], dp[i+1][j+1]);
                } else if(i == dp.length-1){
                    dp[i][j] = arr[i][j] + Math.max(dp[i][j+1], dp[i-1][j+1]);
                } else{
                    dp[i][j] = arr[i][j] + Math.max(dp[i][j+1], Math.max(dp[i+1][j+1], dp[i-1][j+1]));
                }
            }
        }
        int firstRowMax = Integer.MIN_VALUE;
        for(int i=0; i<arr.length; ++i){
            if(dp[i][0] > firstRowMax)
                firstRowMax = dp[i][0];
        }

        Queue<GoldMineCell> queue = new LinkedList<GoldMineCell>();
        for(int i=0; i<dp.length; ++i){
            if(dp[i][0] == firstRowMax)
                queue.add(new GoldMineCell(i,0, ""));
        }
        String resultantPath = firstRowMax + "\n";

        while (!queue.isEmpty()){
            GoldMineCell currentCell = queue.remove();
            if(currentCell.j == dp[0].length-1){
                resultantPath += currentCell.path + "\n";
            }
            else if(currentCell.i == 0){
                if(dp[currentCell.i+1][currentCell.j+1] > dp[currentCell.i][currentCell.j+1]){
                    queue.add(new GoldMineCell(currentCell.i+1, currentCell.j+1, currentCell.path+"d3"));
                } else if(dp[currentCell.i+1][currentCell.j+1] < dp[currentCell.i][currentCell.j+1]){
                    queue.add(new GoldMineCell(currentCell.i, currentCell.j+1, currentCell.path+"d2"));
                } else{
                    queue.add(new GoldMineCell(currentCell.i+1, currentCell.j+1, currentCell.path+"d3"));
                    queue.add(new GoldMineCell(currentCell.i, currentCell.j+1, currentCell.path+"d2"));
                }
            }
            else if(currentCell.i == dp.length-1){
                if(dp[currentCell.i-1][currentCell.j+1] > dp[currentCell.i][currentCell.j+1]){
                    queue.add(new GoldMineCell(currentCell.i-1, currentCell.j+1, currentCell.path+"d1"));
                } else if(dp[currentCell.i-1][currentCell.j+1] < dp[currentCell.i][currentCell.j+1]){
                    queue.add(new GoldMineCell(currentCell.i, currentCell.j+1, currentCell.path+"d2"));
                } else{
                    queue.add(new GoldMineCell(currentCell.i-1, currentCell.j+1, currentCell.path+"d1"));
                    queue.add(new GoldMineCell(currentCell.i, currentCell.j+1, currentCell.path+"d2"));
                }
            } else{
                int maxGold = Math.max(dp[currentCell.i][currentCell.j+1],
                        Math.max(dp[currentCell.i-1][currentCell.j+1], dp[currentCell.i+1][currentCell.j+1]));
                if(dp[currentCell.i-1][currentCell.j+1] == maxGold)
                    queue.add(new GoldMineCell(currentCell.i-1,currentCell.j+1,currentCell.path+"d1"));
                if(dp[currentCell.i][currentCell.j+1] == maxGold)
                    queue.add(new GoldMineCell(currentCell.i,currentCell.j+1,currentCell.path+"d2"));
                if(dp[currentCell.i+1][currentCell.j+1] == maxGold)
                    queue.add(new GoldMineCell(currentCell.i+1,currentCell.j+1,currentCell.path+"d3"));
            }
        }

        for(int i=0; i<dp.length; ++i){
            for(int j=0; j<dp[0].length; ++j){
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return resultantPath;
    }

//-----------------------------------------------------------------------------------
    // 15) PRINT ALL SUBSETS OF AN ARRAY WHICH ADD UP TO A TARGET
    public static String targetSumSubset(int[] arr, int target){

        class Pair{
            int i, j;
            String path;

            public Pair(int i, int j, String path) {
                this.i = i;
                this.j = j;
                this.path = path;
            }
        }

        int[][] dp = new int[arr.length+1][target+1];
        dp[0][0] = 1;
        for(int i=1; i<dp.length; ++i){
            for(int j=0; j<dp[0].length; ++j){
                if(j==0){
                    dp[i][j] = 1;
                } else if((dp[i-1][j] == 1 || arr[i-1] == j )){
                    dp[i][j] = 1;
                }else if(j > arr[i-1] && dp[i-1][j-arr[i-1]] == 1){
                    dp[i][j] = 1;
                }
            }
        }

        int count=0;
        for(int i=0; i<dp.length; ++i){
            for(int j=0; j<dp[0].length; ++j){
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        String finalAns = "";
        Queue<Pair> queue = new LinkedList<Pair>();
        queue.add(new Pair(arr.length, target, ""));
        while(!queue.isEmpty()){
            Pair currentPair = queue.remove();
            if(currentPair.i == 0 || currentPair.j == 0){
                finalAns += currentPair.path + "\n";
                count++;
            } else {
                if(currentPair.j >= arr[currentPair.i-1]){
                    int included = dp[currentPair.i-1][currentPair.j - arr[currentPair.i-1]];
                    if(included == 1)
                        queue.add(new Pair(currentPair.i-1, currentPair.j-arr[currentPair.i-1],
                                currentPair.path+arr[currentPair.i-1]+" "));
                }
                int excluded = dp[currentPair.i-1][currentPair.j];
                if(excluded == 1){
                    queue.add(new Pair(currentPair.i-1, currentPair.j, currentPair.path));
                }
            }
        }
        return(count+"\n"+finalAns);

    }

//-----------------------------------------------------------------------------------
    // 16) 0-1 KNAPSACK PROBLEM
    public static String knapsack0_1(int[] values, int[] weights, int capacity){

        class Pair{
            int i, j;
            String path;

            public Pair(int i, int j, String path) {
                this.i = i;
                this.j = j;
                this.path = path;
            }
        }

        int[][] dp = new int[values.length+1][capacity+1];
        for(int i=1; i<dp.length; ++i){
            for(int j=0; j<dp[0].length; ++j){
                dp[i][j] = dp[i-1][j];
                if(j >= values[i-1]){
                    if(weights[i-1] + dp[i-1][j- values[i-1]] > dp[i-1][j])
                        dp[i][j] = weights[i-1] + dp[i-1][j- values[i-1]];
                }

            }
        }

        for(int i=0; i<dp.length; ++i){
            for(int j=0; j<dp[0].length; ++j)
                System.out.print(dp[i][j] + "  ");
            System.out.println();
        }
        System.out.println();
        String finalAnswer = "Maximum achievable value is " + dp[dp.length-1][dp[0].length-1] +
                "\ncontributed by values : ";
        Queue<Pair> queue = new LinkedList<Pair>();
        queue.add(new Pair(dp.length-1, dp[0].length-1, ""));
        while(!queue.isEmpty()){
            Pair currentPair = queue.remove();
            if(currentPair.i == 0 || currentPair.j == 0){
                finalAnswer += currentPair.path;
            } else {
                if(currentPair.j >= values[currentPair.i-1]){
                    int isIncluded = dp[currentPair.i-1][currentPair.j-values[currentPair.i-1]] + weights[currentPair.i-1];
                    if(isIncluded == dp[currentPair.i][currentPair.j]){
                        queue.add(new Pair(currentPair.i-1, currentPair.j-values[currentPair.i-1],
                                values[currentPair.i-1]+ " " + currentPair.path ));
                    }
                }
                int isExcluded = dp[currentPair.i-1][currentPair.j];
                if(dp[currentPair.i][currentPair.j] == isExcluded){
                    queue.add(new Pair(currentPair.i-1, currentPair.j, currentPair.path));
                }
            }
        }

        return finalAnswer;


    }
//-----------------------------------------------------------------------------------
    // 17) UNBOUNDED KNAPSACK PROBLEM
    public static int unboundedKnapsnack(int[] values, int[] weights, int target){
        int[] dp = new int[target+1];
        for(int i=0; i<dp.length; ++i){
            for(int j=0; j<weights.length; ++j){
                if(i >= values[j])
                dp[i] = Math.max(dp[i], dp[i- values[j]]+weights[j]);
            }
        }
        for(int ans: dp){
            System.out.print(ans + " ");
        }
        System.out.println();
        return dp[target];
    }
//-----------------------------------------------------------------------------------
    // 18) STOCK PROBLEM_1 - Given an array of traded prices, find the maximum profit possible
    public static String stock_01(int[] prices){
        int lowestPrice = Integer.MAX_VALUE, maxProfit = Integer.MIN_VALUE, buy = 0, sell = 0;

        for(int i=0; i<prices.length; ++i){
            if(prices[i] < lowestPrice){
                lowestPrice = prices[i];
                buy = prices[i];
            }
            int profitToday = prices[i]-lowestPrice;
            if(profitToday > maxProfit){
                maxProfit = profitToday;
                sell = prices[i];
            }
        }
        String ans = "Max Profit is Rs." + maxProfit + " when bought at Rs." + buy + " and sold at Rs." + sell;
        return ans;
    }

//-----------------------------------------------------------------------------------
    // 19) STOCK PROBLEM_2 - Same as above, except now you can do multiple transactions. But you need to buy, sell and then again buy...
    public static int stock_02(int[] prices){
        int buyDate=0, sellDate=0, totalProfit=0;
        for(int i=1; i<prices.length; ++i){
            if(prices[i] >= prices[i-1]){
                sellDate++;
            } else {
                totalProfit += prices[sellDate] - prices[buyDate];
                buyDate = i;
                sellDate = i;
            }
        }
        totalProfit += prices[sellDate] - prices[buyDate];
        return totalProfit;
    }
//-----------------------------------------------------------------------------------
    // 20) MAX SQUARE SUB MATRIX
    public static int maxSquareSubMatrix(int[][] arr){
        int[][] dp = new int[arr.length][arr[0].length];
        int maxSize = 0;
        for(int i=arr.length-1; i>=0; --i){
            for(int j=arr[0].length-1; j>=0; --j){
                if(arr[i][j] == 1){
                    if(i==arr.length-1 || j == arr[0].length-1)
                        dp[i][j] = arr[i][j];
                    else{
                        dp[i][j] = Math.min(dp[i+1][j+1], Math.min(dp[i+1][j], dp[i][j+1])) + 1;
                    }
                }
                if(maxSize < dp[i][j])
                    maxSize = dp[i][j];
            }
        }
        return maxSize;
    }
//-----------------------------------------------------------------------------------
    // 21) MAKE TWO STRINGS IDENTICAL -> Find the min. cost of making two strings identical, where cost of deleting a character from the string is given.
    public static void makeStringsIdentical(){

    }
//-----------------------------------------------------------------------------------
    // 22) COUNT NO. OF ARITHMETIC ARRAYS - Arrays containing atleast 3 elements and forming an AP
    public static int artihmaticArrays(int[] arr){
        int[] dp = new int[arr.length];
        int count=0;
        for(int i=2; i<dp.length; ++i){
            if(arr[i]-arr[i-1] == arr[i-1]-arr[i-2]){
                dp[i] = dp[i-1] + 1;
                count += dp[i];
            }
        }
        return count;
    }
//-----------------------------------------------------------------------------------
    // 23) COIN CHANGE COMBINATIONS - Given an unlimited supply of coins, find if a target is achievable
    public static String coinChangeCombinations(int[] coins, int amount){
        int[] dp = new int[amount+1];
        dp[0] = 1;
        for(int i=0; i<coins.length; ++i){
            for(int j=coins[i]; j<dp.length; ++j){
                dp[j] += dp[j-coins[i]];
            }
        }
        for(int combinations: dp){
            System.out.print(combinations + " ");
        }
        return ("\n"+ dp[amount] + " combinations are possible");
    }
//-----------------------------------------------------------------------------------
    // 24) COIN CHANGE PERMUTATIONS
    public static int coinChangePermutations(int[] coins, int amount){
        int[] dp = new int[amount+1];
        dp[0] = 1;
        for(int i=0; i<dp.length; ++i){
            for(int coin: coins){
                if(i >= coin)
                    dp[i] += dp[i-coin];
            }
        }
        return dp[amount];
    }
//-----------------------------------------------------------------------------------
    // 25) NUMBER OF BINARY STRINGS -> Given the length of the string, find the binary strings possible without having consecutive 0's
    public static int binaryStrings(int n){
        int[] dp0 = new int[n+1];
        int[] dp1 = new int[n+1];
        dp0[1] = dp1[1] = 1;
        for(int i=2; i<=n; ++i){
             dp1[i] = dp1[i-1] + dp0[i-1];
             dp0[i] = dp1[i-1];
        }
        return dp0[n] + dp1[n];
    }
//-----------------------------------------------------------------------------------
    // 26) MAX SUM SUBSEQUENCE OF ARRAY WHERE NO TWO ADJACENT ELEMENTS ARE ALLOWED.
    public static int maxSumNoAdjacent(int[] arr){
        int included=arr[0], excluded=0;
        for(int i=1; i<arr.length; ++i){
            int previousIncluded = included;
            included = arr[i] + excluded;
            excluded = Math.max(previousIncluded, excluded);
        }
        return Math.max(included, excluded);
    }
//-----------------------------------------------------------------------------------
    // 27) PAINT HOUSE- GIVEN COST OF COLORING A HOUSE WITH R,G,B FIND THE MIN COST SUCH THAT NO TWO CONSECUTIVE HOUSES HAVE THE SAME COLOR
    // EX: [[14,2,11] , [11,14,5], [14,13,10]] -> represents the cost of coloring house(n) with R,G,B respectively
    public static int paintHouse(int[][] arr){
        int [][] dp = new int[arr.length][arr[0].length];
        dp[0][0] = arr[0][0];
        dp[0][1] = arr[0][1];
        dp[0][2] = arr[0][2];
        for(int i=1; i<arr.length; ++i){
            dp[i][0] = arr[i][0] + Math.min(dp[i-1][1], dp[i-1][2]);
            dp[i][1] = arr[i][1] + Math.min(dp[i-1][0], dp[i-1][2]);
            dp[i][2] = arr[i][2] + Math.min(dp[i-1][0], dp[i-1][1]);
        }
        return Math.min(dp[dp.length-1][0], Math.min(dp[dp.length-1][1], dp[dp.length-1][2]));
    }
//-----------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------
}
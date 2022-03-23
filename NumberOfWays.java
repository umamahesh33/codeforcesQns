import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * https://codeforces.com/problemset/problem/466/C
 * 
 * we check wether over all sum can be divided into three parts or not... if yes 
 * to know how many psblities are there for a particular i...we should know how many
 * ways we we can choose the second contigious part...
 * 
 * if we choose second contigious part then the third automatically the equal part as these two
 * 
 * eg: 1 2 3 6 -6 -3 5 1 -3 3 isthe array
 * aux 0 1 0 0  0  1 0 0  0 0 this is the array that stores 1 at which sum has became totalSum/3
 * basically it denotes the i...so till that 1 overall sum was totSum/3....so to know how many
 * psbl subarrays....for every 1 we need to know how many j's we we can choose, simply a j can be
 * marked as 1 in another aux array...when sum from starting gaves 2*totSum/3 so that we can say that
 * this can be act as our j and when we see from 0 to i, i to j and j to n-1 will form a valid ans
 * 
 * so..
 * eg:  1 2 3 6 -6 -3 5 1 -3 3
 * aux  0 1 0 0  0  1 0 0  0 0
 * aux2 0 0 1 0  1  0 0 0  1 0
 * 
 * so we get this stage, so how to know for a particular 1 in aux how many 1's present after that
 * 1's index... so to achevie this we will caluclate suffix sum of aux2.. which looks like
 * 
 * aux2 3 3 2 2  2  1 1 1  1 0 
 * 
 * so we traverse the first array till <n-2 because (simple there should be three valid subarrays)
 * so at worst if i=n-3,j=n-2 and rem part was n-1
 * 
 * while traversing in aux array if we encounter a 1 we will add aux2[i+1] val to the ans count
 * 
 * TC:O(n) && SC:O(n)
 * 
 */

public class NumberOfWays {
    public static void main(String[] args) throws NumberFormatException, IOException {

        //Input..
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        long totalSum=0;//sum of all numbers in array

        int n=Integer.parseInt(br.readLine().trim());
        long arr[]=new long[n];
        String line[]=br.readLine().trim().split(" ");
        for(int i=0;i<n;i++){
            arr[i]=Long.parseLong(line[i]);
            totalSum+=arr[i];
        }

        if(totalSum%3!=0 || n<3){
            System.out.println(0);
            return;
        }

        int aux[]=new int[n];
        int aux2[]=new int[n];
        Arrays.fill(aux,0);
        Arrays.fill(aux2, 0);

        long req=totalSum/3;    //sum req for each part

        long sum=0;

        for(int i=0;i<n-1;i++){
           sum+=arr[i];
           if(sum==req){
               aux[i]=1;
           }

           if(sum==2*req){
               aux2[i]=1;
           }
        }

        for(int i=n-2;i>=0;i--){
            aux2[i]+=aux2[i+1];
        }

        long ans=0; // no of ways

        for(int i=0;i<n-2;i++){
            if(aux[i]==1){
                ans+=aux2[i+1];
            }
        }

        System.out.print(ans);

        
    }
}

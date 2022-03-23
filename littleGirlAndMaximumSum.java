import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * IMPORTANT: A question based on the processing queries..
 * 
 * underastand the question properly...terms to understand reordering, preforming queries
 * 
 * 
 * 
 * 
 * 
 * what is reordering : reordering is the re-arranging the array to a permutaion which gives us the
 * max sum when we perform q queries on that reordered array
 * 
 * question asks for the max possible sum btw li and ri that we can get with reordering the 
 * array and performing q queires on it...
 * 
 * so what is that reoredred or rearranged array that can give us the max sum when we perform q queires 
 * on it...
 * 
 * to get the max sum..the max element should be in a position where the index that was included many
 * times in all queries and similarly goes so 2nd max should be placed at second most times included index
 * and similarly goes on
 * 
 * so here problem boils down to finding the indexes that was included how many times after performing 
 * q queries
 * 
 * so here we can find the indexs count by performing the range processing queries technique
 * 
 * after finding indexs count 
 * sort both arr and queries arrays and simply find the multiplication of arr[i]*queries[i] from 0 to n
 * 
 * 
 * so to get the max sum
 */


public class littleGirlAndMaximumSum {
    public static void main(String[] args) throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        //input 
        String line1[]=br.readLine().trim().split(" ");
        int n=Integer.parseInt(line1[0]);
        int q=Integer.parseInt(line1[1]);
        String line2[]=br.readLine().trim().split(" ");
        long arr[]=new long[n+1];
        for(int i=1;i<=n;i++){
            arr[i]=Long.parseLong(line2[i-1]);
        }

        //to count the index participation
        long queries[]=new long[n+1];
        Arrays.fill(queries, 0);
        for(int i=0;i<q;i++){
            String line3[]=br.readLine().trim().split(" ");
            int left=Integer.parseInt(line3[0]);
            int right=Integer.parseInt(line3[1]);
            queries[left-1]-=1;
            queries[right]+=1;
        }

        //doing suffix sum
        for(int i=n-1;i>=0;i--){
            queries[i]=queries[i+1]+queries[i];
        }

        //sorting the both arrays
        Arrays.sort(queries);
        Arrays.sort(arr);

        //to track sum obtained from performing all queries
        long sum=0;
        for(int i=0;i<=n;i++){
            sum+=arr[i]*queries[i];
        }

        System.out.println(sum);

    }
}

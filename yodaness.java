import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// https://www.spoj.com/status/YODANESS,uma5/

//important based on the inversion count problem

//here for every testcase we are given two strngs one is the actual order and one was the 
//changed order , we need to caluclate how many pairs are changed

/**
 * so if we say original order was like sorted and changed was unsorted and we need to find the 
 * amount of unsortedness so, we apply the invesion count logic to get the count
 */

public class yodaness {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine().trim());
        while(t-->0){
            int n=Integer.parseInt(br.readLine().trim());
            String yodaStatement[]=br.readLine().trim().split(" ");
            String crctStatement[]=br.readLine().trim().split(" ");

            Map<String,Integer> hm=new HashMap<>();
            
            for(int i=0;i<n;i++){
                hm.put(crctStatement[i], i);
            }

            int arr[]=new int[n];
            for(int i=0;i<n;i++){
                int val=(int)hm.get(yodaStatement[i]);
                arr[i]=val;
            }

            System.out.println(mergeSort(arr,0,n-1));
        }
    }

    private static long mergeSort(int[] arr, int l, int r) {
        if(l==r) return 0;

        int m=(l+r)/2;
        long cnt1=mergeSort(arr, l, m);
        long cnt2=mergeSort(arr, m+1, r);

        long cnt3=merge(arr,l,m,r);
        return cnt1+cnt2+cnt3;
    }

    private static long merge(int[] arr, int l, int m, int r) {
        int tmp[]=new int[r-l+1];
        long count=0;
        int i=l;
        int j=m+1;
        int c=0;

        while(i<=m && j<=r){
            if(arr[i]<=arr[j]){
                tmp[c++]=arr[i++];
            }else{
                count+=(m-i+1);
                tmp[c++]=arr[j++];
            }
        }

        while(i<=m){
            tmp[c++]=arr[i++];
        }

        while(j<=r){
            tmp[c++]=arr[j++];
        }

        c=0;
        for(i=l;i<=r;i++){
            arr[i]=tmp[c++];
        }

        return count;
        
    }
}

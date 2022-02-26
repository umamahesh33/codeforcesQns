import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class XKsegments {
    public static void main(String[] args) throws IOException {

        //input
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        String line1[]=br.readLine().trim().split(" ");
        int n=Integer.parseInt(line1[0]);
        long x=Long.parseLong(line1[1]);
        long k=Long.parseLong(line1[2]);

        String line2[]=br.readLine().trim().split(" ");
        long arr[]=new long[n];
        for(int i=0;i<n;i++){
            arr[i]=Long.parseLong(line2[i]);
        }

        //total count or ans
        long countOfOrderedPairs=0;

        Arrays.sort(arr);

        //traversing for every element in sorted array
        for(int i=0;i<n;i++){
            long lb;
            long ub;

            //caluclating lower bound and upper bound
            if(arr[i]%x!=0){
                if(k==0){
                    lb=arr[i];
                    ub=lb+(x-arr[i]%x-1);
                }else{
                    lb=arr[i]+(x-(arr[i]%x))+(k-1)*x;
                    ub=lb+x-1;
                }
            }else{
                if(k==0){
                    lb=arr[i]+1;
                    ub=lb+(x-2);
                }else{
                    lb=arr[i]+(k-1)*x;
                    ub=lb+x-1;
                }
            }

            //applying binary search on the array
            int low=0;
            int high=n-1;
            int mid=-1;

            int lftInd=-1;
            int ritInd=-1;
            //figuring out left most index
            while(low<=high){
                mid=(low+high)/2;
                if(arr[mid]>ub){
                    high=mid-1;
                }else if(arr[mid]<lb){
                    low=mid+1;
                }else{
                    if(mid==0){
                        lftInd=mid;
                        break;
                    }else if(arr[mid-1]<lb){
                        lftInd=mid;
                        break;
                    }else{
                        high=mid-1;
                    }
                }
            }

            //figuring out right most index
            low=0;
            high=n-1;
            mid=-1;
            while(low<=high){
                mid=(low+high)/2;
                if(arr[mid]>ub){
                    high=mid-1;
                }else if(arr[mid]<lb){
                    low=mid+1;
                }else{
                    if(mid==n-1){
                        ritInd=mid;
                        break;
                    }else if(arr[mid+1]>ub){
                        ritInd=mid;
                        break;
                    }else{
                        low=mid+1;
                    }
                }
            }

            
           if(lftInd>=0 && ritInd>=0){
                countOfOrderedPairs+=(ritInd-lftInd+1);
           }
        }
        System.out.println(countOfOrderedPairs);
    }
}

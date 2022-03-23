import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class specialElection{
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine().trim());
        while(t-->0){
            int n=Integer.parseInt(br.readLine().trim());
            long s[]=new long[n];
            String str[]=br.readLine().trim().split(" ");
            for(int i=0;i<n;i++){
                s[i]=Long.parseLong(str[i]);
            }

            long sum[]=new long[n];
            sum[0]=s[0];
            for(int i=1;i<n;i++){
                sum[i]=sum[i-1]+s[i];
            }

            long aux[]=new long[n];
            Arrays.fill(aux, 0);
            for(int i=0;i<n;i++){
                //search left 
                int leftInd=searchLeft(i, s, sum);
                if(leftInd==0){
                    aux[i-1]+=1;
                }
                if(leftInd>0){
                    aux[i-1]+=1;
                    aux[leftInd-1]-=1;
                }
                // add 1 and -1 at required positions

                //search right
                int rightInd=searchRight(i, s, n, sum);
                if(rightInd>=0){
                    aux[rightInd]+=1;
                    aux[i]-=1;
                }
                //add 1 and -1 at required positions
            }

            long tmp=0;
            for(int i=n-1;i>=0;i--){
                tmp+=aux[i];
                aux[i]=tmp;
            }

            for(int i=0;i<n;i++){
                System.out.print(aux[i]+" ");
            }
        }
    }

    private static int searchLeft(int i,long[] s, long[] sum){
        int low=0;
        int high=i-1;
        int mid=-1;
        while(low<=high){
            mid=(low+high)/2;
            if(sum[i]-s[i]-sum[mid]>s[i]){
                low=mid+1;
            }else{
                if(mid==0 || sum[i]-sum[mid-1]-s[i]>s[i]){
                    return mid;
                }else{
                    high=mid-1;
                }
            }
        }
        return mid;
    }

    private static int searchRight(int i,long s[],int n, long[] sum){
        int low=i+1;
        int high=n-1;
        int mid=-1;
        while(low<=high){
            mid=(low+high)/2;
            if(sum[mid]-s[mid]-sum[i]>s[i]){
                high=mid-1;
            }else{
                if(mid==n-1 || sum[mid]-sum[i]>s[i]){
                    return mid;
                }else{
                    low=mid+1;
                }
            }
        }
        return mid;
    }
}
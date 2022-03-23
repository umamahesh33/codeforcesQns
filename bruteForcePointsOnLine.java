import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
 
public class bruteForcePointsOnLine{
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
 
        //input
        String line1[]=br.readLine().trim().split(" ");
        int n=Integer.parseInt(line1[0]);
        long d=Long.parseLong(line1[1]);
        
        long arr[]=new long[n];
        String line2[]=br.readLine().trim().split(" ");
        for(int i=0;i<n;i++){
            arr[i]=Long.parseLong(line2[i]);
        }
 
        //applying two ptr approach
        if(n<3){
            System.out.println(0);
            return;
        }
 
        long ans=0;
        int i=0;
        int j=2;
 
        while(j<n){
            if(arr[j]-arr[i]>d){
                i++;
                if(j-i<2){
                    j++;
                }
            }else{
                int k=j;
                int gap=0;
                while(k<n && arr[k]-arr[i]<=d){
                    k++;
                    gap++;
                }
 
                ans+=(gap*(gap+1))/2;
                i++;
                j++;
            }
        }
 
        if(j-i-1>=3){
            int x=j-i-1;
            ans+=(x*(x-1)*(x-2))/6;
        }
 
        System.out.println(ans);
    }
}

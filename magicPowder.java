/* package codechef; // don't place package name! */
import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
public class magicPowder
{
    public static void main (String[] args) throws java.lang.Exception
    {
        // your code goes here
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		//input
		String line1[]=br.readLine().trim().split(" ");
		String line2[]=br.readLine().trim().split(" ");
		String line3[]=br.readLine().trim().split(" ");
		
		int n=Integer.parseInt(line1[0]);
		long k=Long.parseLong(line1[1]);
		
		long recepie[]=new long[n];
		for(int i=0;i<n;i++){
			recepie[i]=Long.parseLong(line2[i]);
		}
		
		long stock[]=new long[n];
		for(int i=0;i<n;i++){
			stock[i]=Long.parseLong(line3[i]);
		}
		
		//finding range
		long low=0;
		long high=2*(long)Math.pow(10,9);
		long mid=-1;
		//applying binary search
		while(low<=high){
			mid=low+(high-low)/2;
			long powder=canWeBake(mid,recepie,stock,k,n);
			if(powder<0){
				high=mid-1;
			}else{
				long powder2=canWeBake(mid+1,recepie,stock,k,n);
				if(powder2<0){
					System.out.println(mid);
					break;
				}else{
					low=mid+1;
				}
			}
		}
    }
	
	private static long canWeBake(long mid,long[] recepie,long[] stock,long k,int n){
		for(int i=0;i<n;i++){
			long totalReq=mid*recepie[i];
            long req=0;
            if(stock[i]<totalReq){
                req=totalReq-stock[i];
            }
            k=k-req;
            if(k<0)return k;
		}
		return k;
	}
}
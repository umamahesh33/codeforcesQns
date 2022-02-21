import java.util.Scanner;

public class marvaloGauntsRing{


    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        int n=scanner.nextInt();
        long p=scanner.nextLong();
        long q=scanner.nextLong();
        long r=scanner.nextLong();

        long arr[]=new long[n];
        for(int i=0;i<n;i++){
            arr[i]=scanner.nextLong();
        }

        System.out.println(maxmizeTheExpression(arr,n,p,q,r));
    }

    private static long maxmizeTheExpression(long[] arr, int n, long p, long q, long r) {

        long smax[]=new long[n];
        smax[n-1]=r*arr[n-1];
        for(int i=n-2;i>=0;i--){
            smax[i]=Math.max(r*arr[i],smax[i+1]);
        }
        long pmax=p*arr[0];
        long ans=Long.MIN_VALUE;
        for(int i=0;i<n;i++){
            pmax=Math.max(pmax, p*arr[i]);
            long exp= pmax + q*arr[i] + smax[i];
            ans=Math.max(ans, exp);
        }
        return ans;
    }
}
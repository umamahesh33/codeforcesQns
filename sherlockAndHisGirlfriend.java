import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class sherlockAndHisGirlfriend{
    public static void main(String[] args) {
       Scanner scanner=new Scanner(System.in);
       int n=scanner.nextInt();

       int primes[]=new int[n+2];
       for(int i=0;i<n+2;i++){
           primes[i]=1;
       }
       primes[0]=0;
       primes[1]=0;

       for(int i=2;i*i<n+2;i++){
           for(int j=i;i*j<=n+2;j++){
               primes[i*j]=0;
           }
       }

        if(n==1 || n==2){
            System.out.println(1);
        }else{
            System.out.println(2);
        }

       int arr[]=new int[n+2];
       for(int i=0;i<n+2;i++){
           arr[i]=1;
       }

        for(int i=2;i*i<n+2;i++){
            if(primes[i]==1){
                for(int j=i;i*j<n+2;j++){
                    arr[i*j]=arr[i]+1;
                }
            }
        }

       for(int i=2;i<n+2;i++){
           System.out.print(arr[i]+" ");
       }
    }
}
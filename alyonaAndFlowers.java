
import java.util.Scanner;

public class alyonaAndFlowers {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int m=scanner.nextInt();

        int flowers[]=new int[n];
        for(int i=0;i<n;i++){
            flowers[i]=scanner.nextInt();
        }

        int subArrays[]=new int[m*2];
        for(int i=0;i<m*2;i+=2){
            subArrays[i]=scanner.nextInt();
            subArrays[i+1]=scanner.nextInt();
        }

        int prefixSum[]=new int[n];
        prefixSum[0]=flowers[0];
        for(int i=1;i<n;i++){
            prefixSum[i]=prefixSum[i-1]+flowers[i];
        }

        int mood=0;
        for(int i=0;i<m*2;i+=2){
            int current_mood=0;
            int l=subArrays[i];
            int r=subArrays[i+1];
            if(l==1){
                current_mood=prefixSum[r-1];
            }else{
                current_mood=prefixSum[r-1]-prefixSum[l-2];
            }

            if(current_mood>0){
                mood+=current_mood;
            }
        }
        System.out.println(mood);
    }
    
}

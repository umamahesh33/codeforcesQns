import java.util.Scanner;

public class pyramidOfGlasses {
    
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        int n=scanner.nextInt();
        int t=scanner.nextInt();
        int i=1;
        int sum=0;
        System.out.println(glassesFilled(n,t,i));
    }

    private static int glassesFilled(int n, int t,int i,int sum) {

        if()

        if(i<t){
            sum+=t-i;
            t=t-i;
            i=i+1;
        }
        glassesFilled(n, t, i, sum);

        
    }
}

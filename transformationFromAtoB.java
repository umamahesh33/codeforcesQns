import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class transformationFromAtoB {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        int a=scanner.nextInt();
        int b=scanner.nextInt();

        List<Integer> tmp=new ArrayList<>();
        recursionFunction(a,b,tmp);

        if(tmp.get(tmp.size()-1)==a){
            System.out.println("YES");

            System.out.println(tmp.size());

            for(int i=tmp.size()-1;i>=0;i--){
                System.out.print(tmp.get(i)+" ");
            }

        }else{
            System.out.println("NO");
        }

    }

    private static void recursionFunction(int a, int b,List<Integer> tmp)
    {
        if(b<a){
            tmp.add(b);
            return;
        }
        if(b==a){
            tmp.add(b);
            return;
        }

        tmp.add(b);
        if(b%2==0){
            recursionFunction(a, b/2, tmp);
        }
        if(b%10==1){
            recursionFunction(a, (b-1)/10 , tmp);
        }

    }
}

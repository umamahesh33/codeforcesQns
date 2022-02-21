import java.util.ArrayList;
import java.util.Scanner;

public class starSky {

    public static boolean checkStars(int x1,int y1,int x11,int y11,int x22,int y22){
        if(x11<=x1 && y11<=y1 && x22>=x1 && y22>=y1){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        long n,q;
        int c;
        n=scanner.nextLong();
        q=scanner.nextLong();
        c=scanner.nextInt();

        ArrayList<ArrayList<Integer>> stars=new ArrayList<>();

        for(int i=0;i<n;i++){
            ArrayList<Integer> tempList=new ArrayList<>();
            tempList.add(scanner.nextInt());
            tempList.add(scanner.nextInt());
            tempList.add(scanner.nextInt());
            stars.add(tempList);
        }

        ArrayList<ArrayList<Integer>> queries=new ArrayList<>();

        for(int i=0;i<q;i++){
            ArrayList<Integer> tempList=new ArrayList<>();
            tempList.add(scanner.nextInt());
            tempList.add(scanner.nextInt());
            tempList.add(scanner.nextInt());
            tempList.add(scanner.nextInt());
            tempList.add(scanner.nextInt());
            queries.add(tempList);
        }

        for(int i=0;i<q;i++){
            int count=0;
            for(int j=0;j<n;j++){
                if(checkStars(stars.get(j).get(0),stars.get(j).get(1),
                 queries.get(i).get(1), queries.get(i).get(2), 
                 queries.get(i).get(3), queries.get(i).get(4))){
                     int s=stars.get(j).get(2);
                     int t=queries.get(i).get(0);
                     count+=s+t;
                 }
            }
            if(count>c){
                System.out.println(count%(c+1));
            }else{
                System.out.println(count);
            }
        }
    }   
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class data{
    int val;
    int ind;
}

public class adaAndUnstableSort{
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine().trim());
        int arr[]=new int[n];
        String line1[]=br.readLine().trim().split(" ");
        for(int i=0;i<n;i++){
            arr[i]=Integer.parseInt(line1[i]);
        }
        List<data> list=new ArrayList<>();
        for(int i=0;i<n;i++){
            data d=new data();
            d.val=arr[i];
            d.ind=i;
            list.add(d);
        }

        Collections.sort(list,new Comparator<data>() {

            @Override
            public int compare(data o1, data o2) {
                if(o1.val==o2.val){
                    return o2.ind-o1.ind;
                }
                return o1.val-o2.val;
            }  
        });

        for(data d: list){
            System.out.print(d.ind+1+" ");
        }
    }
}
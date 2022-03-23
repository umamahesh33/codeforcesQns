import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
 
public class averageScore {
    public static void main(String[] args) throws IOException {
 
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        
        //input
        int n=Integer.parseInt(br.readLine().trim());
        String line[]=br.readLine().trim().split(" ");
        int a=Integer.parseInt(line[0]);
        int b=Integer.parseInt(line[1]);
 
        String line2[]=br.readLine().trim().split(" ");
        List<Data> list=new ArrayList<>();
        for(int i=0;i<n;i++){
            Data d=new Data(Integer.parseInt(line2[i]),i);
            list.add(d);
        }
 
        if(a==b){
            for(int i=0;i<a;i++){
                list.get(i).sub=1;
            }
 
            for(int i=a;i<n;i++){
                list.get(i).sub=2;
            }
 
            for(int i=0;i<n;i++){
                System.out.print(list.get(i).sub+" ");
            }
            return;
        }

        int min=Math.min(a, b);
        if(min==a){
            Collections.sort(list,new Comparator<Data>() {
                @Override
                public int compare(Data o1, Data o2) {
                    if(o1.val==o2.val){
                        return o2.ind-o1.ind;
                    }
                    return o1.val-o2.val;
                }    
            });

            for(int i=0;i<n-a;i++){
                list.get(i).sub=2;
            }
            for(int i=n-a;i<=n-1;i++){
                list.get(i).sub=1;
            }
        }else{
            Collections.sort(list,new Comparator<Data>() {
                @Override
                public int compare(Data o1, Data o2) {
                    return o1.val-o2.val;
                }    
            });

            for(int i=0;i<n-b;i++){
                list.get(i).sub=1;
            }
            for(int i=n-b;i<=n-1;i++){
                list.get(i).sub=2;
            }
        }
 
        Collections.sort(list,new Comparator<Data>() {
 
            @Override
            public int compare(Data o1, Data o2) {
                return o1.ind-o2.ind;
            }
        });
 
        for(int i=0;i<n;i++){
            System.out.print(list.get(i).sub+" ");
        }
    }
}
 
class Data{
    int val;
    int ind;
    int sub;
 
    Data(int val,int ind){
        this.val=val;
        this.ind=ind;
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ehabIsAnOddPerson {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine().trim());
        String str[]=br.readLine().trim().split(" ");
        List<Long> list=new ArrayList<>();
        for(int i=0;i<n;i++){
            list.add(Long.parseLong(str[i]));
        }

        boolean oddEleFound=false;
        boolean evenEleFound=false;
        for(int i=0;i<n;i++){
            if(evenEleFound && oddEleFound) break;
            if(list.get(i)%2==0){
                evenEleFound=true;
            }else{
                oddEleFound=true;
            }
        }

        if(evenEleFound && oddEleFound) {
            Collections.sort(list);
        }

        for(int i=0;i<list.size();i++){
            System.out.print(list.get(i)+" ");
        }
    }
}


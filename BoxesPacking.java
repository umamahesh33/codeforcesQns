import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//just find the max frequncy of a box that will be the ans...

//in our approach we ar placing smaller boxes into largers as many as psbl...
//and counting the visble boxes
public class BoxesPacking{
    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int n=Integer.parseInt(br.readLine().trim());

        Map<Integer,Integer> hm=new HashMap<>();
        List<Integer> ls=new ArrayList<>();

        String line[]=br.readLine().trim().split(" ");

        for(int i=0;i<n;i++){
            int num=Integer.parseInt(line[i]);

            if(hm.containsKey(num)){
                int count=hm.get(num);
                count+=1;
                hm.put(num, count);
            }else{
                ls.add(num);
                hm.put(num, 1);
            }
        }

        int ans=0;
        boolean flag=true;
        int i=0;

        while(flag && i<ls.size()){
            if(hm.get(ls.get(i))==0){
                i++;
            }else{
                int min=findLargeBoxes(i,ls,hm);
                
                if(min==Integer.MAX_VALUE){
                    flag=false;
                    ans+=hm.get(ls.get(i));
                }else{
                    min=Math.min(min, hm.get(ls.get(i)));
                    ans+=min;
                    
                    for(int j=i;j<ls.size();j++){
                        int key=ls.get(j);
                        int val=hm.get(key);
                        if(val!=0){
                            val=val-min;
                            hm.put(key, val);
                        }
                    }
                }
            }
        }

        System.out.println(ans);
    }

    private static int findLargeBoxes(int i, List<Integer> ls, Map<Integer, Integer> hm) {
        int min=Integer.MAX_VALUE;
        for(int j=i+1;j<ls.size();j++){
            if(hm.get(ls.get(j))!=0){
                min=Math.min(min,hm.get(ls.get(j)));
            }
        }
        return min;
    }
}
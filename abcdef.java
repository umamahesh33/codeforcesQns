import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


// some combinations involved 
//do not gor get the edge case as we are changing the eqn but d!=0 acc to original eqn
//calucalte answers of all psbl lhs and store the answer freq
//on rhs store every combination res in  list and traverse those list
//by searching for a key in lhsMap
//if we found a key for particular rhs--then there is freq no of combinations that lhs can have so count them to total count
//if revision req definetly watch video sorting module (name:- solve the eqn)

public class abcdef {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        //input
        int n=sc.nextInt();
        int s[]=new int[n];
        for(int i=0;i<n;i++){
            s[i]=sc.nextInt();
        }

        //lhs
        Map<Integer,Integer> hm=new HashMap<>();
        for(int a=0;a<n;a++){
            for(int b=0;b<n;b++){
                for(int c=0;c<n;c++){
                    int lhs=s[a]*s[b]+s[c];
                    if(hm.containsKey(lhs)){
                        int val=(int)hm.get(lhs);
                        val=val+1;
                        hm.put(lhs,val);
                    }else{
                        hm.put(lhs, 1);
                    }
                }
            }
        }

        List<Integer> rhsRes=new ArrayList<>();
        for(int d=0;d<n;d++){
            for(int e=0;e<n;e++){
                for(int f=0;f<n;f++){
                    if(s[d]!=0){
                        int rhs=s[d]*(s[e]+s[f]);
                        rhsRes.add(rhs);
                    }
                }
            }
        }

        int totalSexTupels=0;
        for(int i=0;i<rhsRes.size();i++){
            int key=rhsRes.get(i);
            if(hm.containsKey(key)){
                int val=(int)hm.get(key);
                totalSexTupels+=val;
            }
        }
        System.out.println(totalSexTupels);
    }
}

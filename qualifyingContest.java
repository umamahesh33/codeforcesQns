import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://codeforces.com/contest/659/problem/B

//just used custom sorting and map to store values
//if i found that 2nd and 3rd value are smae we need to put contest again there so ?
//or else print names of the winners in that paarticular region
//can be done simple by using custom comparator, don't wether may be another approach was there are not
class Participant{
    String name;
    int score;
    Participant(String name,int score){
        this.name=name;
        this.score=score;
    }
}
class qualifyingContest{
    public static void main(String[] args)throws NumberFormatException,IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String line1[]=br.readLine().trim().split(" ");
        int n=Integer.parseInt(line1[0]);
        int m=Integer.parseInt(line1[1]);

        //construction of map was done now we need to sort the all lists in the map according to region id
        Map<Integer,List<Participant>> hm=new HashMap<>();
        for(int i=0;i<n;i++){
            String line2[]=br.readLine().trim().split(" ");
            String name=line2[0];
            int regId=Integer.parseInt(line2[1]);
            int score=Integer.parseInt(line2[2]);
            Participant participant=new Participant(name,score);
            if(hm.containsKey(regId)){
                List<Participant> ls=hm.get(regId);
                ls.add(participant);
                hm.put(regId,ls);
            }else{
                List<Participant> tmp=new ArrayList<>();
                tmp.add(participant);
                hm.put(regId, tmp);
            }
        }

        // traversing though the map and getting the values and sorting them based on marks 
        //checking wethetr 2nd and 3rd got same marks or not if not 1 and 2 are winners if yes
        //we need to conduct the contest again there so ?
        for(Map.Entry ele: hm.entrySet()){
            List<Participant> ls=(List<Participant>) ele.getValue();
            Collections.sort(ls,new Comparator<Participant>() {
                @Override
                public int compare(Participant o1, Participant o2) {
                    if(o1.score==o2.score){
                        return o2.name.compareTo(o1.name);
                    }
                    return o2.score-o1.score;
                }  
            });

            if(ls.size()==2){
                System.out.println(ls.get(0).name+" "+ls.get(1).name);
            }else{

                int score2=ls.get(1).score;
                int score3=ls.get(2).score;
                if(score3<score2){
                    System.out.println(ls.get(0).name+" "+ls.get(1).name);
                }else{
                    System.out.println("?");
                }
            }
        }
    }
}
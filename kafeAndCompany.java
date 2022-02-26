import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * here we previously done a greedy approach by traversing the sorted objects accoring to money
 * but in that case we may missout some combinations like
 * freinds 5 and d=6
 * 5 11
 * 10 11
 * 11 11
 * 12 11
 * 13 11
 * 100 1
 * 
 * in this case we consider first two and say max=22 and from there we consider 12,13 and again max was 22
 * if we follow greedy approach it will not the crct method
 * we need to consider for every friend what is the how much frnd factor we get
 *  for 5 we get 5,10 == 22
 * for 10 we get 10,11,12==33 actual ans
 * similarly for 11 we get 11,12==22
 * so if thats why greedy allocation was not a good method
 * 
 * so we iterate n times and calculate what is the amount of frnd factor we get for every frnd
 * inside as the objects are sorted we can apply binary search on it
 * **tip we can caluclate prefix frndfactor sum to save time
 * after that in binary search we see for a particular frnd till which frnd we can call
 * and after that take the sum frm prefixFrndFactorSum and update curr max
 * update max=max(currmax,max)
 */
public class kafeAndCompany {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        //input
        String line1[]=br.readLine().trim().split(" ");
        int n=Integer.parseInt(line1[0]);
        long d=Long.parseLong(line1[1]);
        
        List<friend> list=new ArrayList<>();
        for(int i=0;i<n;i++){
            String line2[]=br.readLine().trim().split(" ");
            long m=Long.parseLong(line2[0]);
            long s=Long.parseLong(line2[1]);
            friend frnd=new friend(m, s);
            list.add(frnd);
        }

        Collections.sort(list,new Comparator<friend>() {
            @Override
            public int compare(friend o1, friend o2) {
                if(o1.money<o2.money) return-1;
                if(o1.money==o2.money) return 0;
                return 1;
            }
        });

        //have a prefix sum of soreted form all frnd factors
        long frndFactorSum[]=new long[list.size()];
        frndFactorSum[0]=list.get(0).frndFactor;
        for(int i=1;i<list.size();i++){
            frndFactorSum[i]=frndFactorSum[i-1]+list.get(i).frndFactor;
        }

        long max=Long.MIN_VALUE;
        for(int i=0;i<list.size();i++){
            long min=list.get(i).money;
            long currMax=-1;
            int low=i;
            int high=list.size()-1;
            int mid=-1;

            while(low<=high){
                mid=(low+high)/2;
                long curr=list.get(mid).money;
                if(curr-min>=d){
                    high=mid-1;
                }else{
                    if(mid==list.size()-1){
                        break;
                    }else if(list.get(mid+1).money-min>=d){
                        break;
                    }else{
                        low=mid+1;
                    }
                }
            }
            if(i==0){
                currMax=frndFactorSum[mid];
            }else{
                currMax=frndFactorSum[mid]-frndFactorSum[i-1];
            }
            max=Math.max(max, currMax);
        }
        System.out.println(max);
    }
}

class friend{
    long money;
    long frndFactor;

    friend(long money,long frndFactor){
        this.money=money;
        this.frndFactor=frndFactor;
    }
}

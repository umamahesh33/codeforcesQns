import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * IMPORTANT : PROCESSING RANGE QUERIES...
 * 
 * here question gives us the n tempratues ,k(admissable factor), q no of quries
 * 
 * 3 2 4
 * (n k q)
 * 91 94 
 * 92 97 
 * 97 99
 * 
 * Q starts
 * 92 94 
 * 93 97
 * 95 96 
 * 90 100
 * 
 * 
 *here question is saying that if a temparature is suggested by atleast k recepies from n recepies
 then it is admissable temprature

 here we have given q queries we need to say how many tempratues are admissable in that range
 let say== q= 92 94--->> 92 93 94 (in those three what are the admissable tempratures according to the given n ranges)

 so let say instead of caluclating for every query we precompute on the range of 1 to 200000 acc to question

so we can cluclate it by processing range queries technique

so lets in here we intrested in 90 to 100

so all are initialised with 0's and for every query we do +1 at right index and -1 at left-1 index

do suffix sum of it which gives how many recepies admit that particular temparature
so by doing this we get to know about every indiviudal temprature so now we can preform queries in btr

so cmng to quries we have li and ri how can we say that in a O(1) time by jus looking at the 
precomputed array bcoz traversing from li to ri and checking fot the temps >=k
will result in TLE bcoz: Q*(ri-li) in worst case it will be Q*N or Q^2

we can do it this way after precomputing , once again traverse the array and make all non admissable(<k) to 0
and all admissable tmps(>=k) to 1

and do prefix sum for it then while doing querys we just need to return the psum[right]-psum[left-1]
which goes in O(1)time

this all thing can be done in O(n+Q) time
sc: O(n)

 */
 
public class karenAndCoffee {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        //input
        String line1[]=br.readLine().trim().split(" ");
        int n=Integer.parseInt(line1[0]);
        int k=Integer.parseInt(line1[1]);
        int q=Integer.parseInt(line1[2]);
 
        List<temp> list=new ArrayList<>();
        for(int i=0;i<n;i++){
            String line2[]=br.readLine().trim().split(" ");
            temp t=new temp(Integer.parseInt(line2[0]),Integer.parseInt(line2[1]));
            list.add(t);
        }
 
        //initialising all array elements to zero, performing range queries technique
        int arr[]=new int[200000+1];
        Arrays.fill(arr, 0);
 
        for(int i=0;i<n;i++){
            arr[list.get(i).right]+=1;
            arr[list.get(i).left-1]-=1;
        }
 
        //suufix sum
        for(int i=200000-1;i>=0;i--){
            arr[i]=arr[i]+arr[i+1];
        }

        //making all valid tmps to 1 and non valid ones to 0
        for(int i=0;i<200001;i++){
            if(arr[i]>=k){
                arr[i]=1;
            }else{
                arr[i]=0;
            }
        }

        //prefix sum
        for(int i=1;i<200001;i++){
            arr[i]=arr[i]+arr[i-1];
        }
 
        //performing quries
        for(int i=0;i<q;i++){
            String line3[]=br.readLine().trim().split(" ");
            int left=Integer.parseInt(line3[0]);
            int right=Integer.parseInt(line3[1]);

            int count=arr[right]-arr[left-1];
            System.out.println(count);
        }
    }
}
 
//class to hold the data as a obj
class temp{
    int left;
    int right;
 
    temp(int left,int right){
        this.left=left;
        this.right=right;
    }
}

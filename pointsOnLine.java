import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Approach is ,can be done via binary search approach....
 * two ptr approach was first intialise and ptrs to i=0 and j=2...check untill arr[j]-arr[i]<=d and
 * and maintain a gap variable to track of till where of j for present i can be valid...
 * after that every time increase the count by n*(n+1)/2 this was due to
 * eg: 1 4 20 27 65 79....and so on let say
 * for 1 -> 20,27 are valid...
 * so how many can be formed with 1 4 20, 1 4 27, 1 20 27 so it will be gap*gap+1/2 
 * where gap is the elements count of 20,27==> 2 so 2*3/2==>3 the count which can be formed by one..
 * 
 * so here we need to observe that we should not initialse the ptr to again back for next element so..
 * if at present j was at 65 and after counting i..i++  ===>2 which val=4
 * 65-4 was also >d(50) soo we should add gap-1 * gap-1 +1 /2 and goes on
 * 
 * just do what question exactly asks and some cobinatarics are involved
 * 
 * be careful while considering the combinations /be care ful of duplicates
 * 
 * take care of integer overflowss...
 */

public class pointsOnLine{
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        //input
        String line1[]=br.readLine().trim().split(" ");
        int n=Integer.parseInt(line1[0]);
        long d=Long.parseLong(line1[1]);
        
        long arr[]=new long[n];
        String line2[]=br.readLine().trim().split(" ");
        for(int i=0;i<n;i++){
            arr[i]=Long.parseLong(line2[i]);
        }

        //applying two ptr approach

        //if length was lessthan 2 then return 0
        if(n<3){
            System.out.println(0);
            return;
        }

        long ans=0;
        int i=0;
        int j=2;
        long gap=0;

        while(j<n){

            //if with the present element - ele at i was <=d we do count gap(no of valid elements)
            if(arr[j]-arr[i]<=d){
                gap++;
                j++;
            }else{

                //if gap==0 and arr[j]-arr[i]>d then we do i++ and len btw was <2 we do j++ bcoz we need atleast three ele
                if(gap==0){
                    i++;

                    if(j-i<2){
                        j++;
                    }
                }

                //do this untill gap will become zero or untill arr[j]-arr[next i]<=d
                while(gap>0){
                    ans+=(gap*(gap+1))/2;
                    gap--;
                    i++;
                    if(arr[j]-arr[i]<=d){
                        break;
                    }
                }
            }
        }

        //if left over we caluclate them here ,like j came to n but there valid combinations then we do this
        if(gap>0){
            long x=j-i;
            ans+=(x*(x-1)*(x-2))/6;
        }

        System.out.println(ans);
    }
}
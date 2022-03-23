import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/*
shubham jalan:
q1 = p1 ^ ( 1 mod 1) ^ (1 mod 2 ) ….  ^(1 mod n )
q2 = p2 ^ ( 2 mod 1) ^ (2 mod 2 ) ….  ^(2 mod n )
.
.
.
qn = pn ^ ( n mod 1) ^ (n mod 2 ) …. ^(n mod n )
-----------------------------------------------------------------

For getting the final result, we need to XOR q1....qn. We can do column wise xor (instead of row wise XOR)
and then take final XOR, just like in regular addition.Is there a way to optimize the column wise sum?
(Hint: a^a= 0, for any a)


ME:

yes, sir now got it, how prefix xor helps...

we do prefix xor from 1 to n...and while calculating xor we see n/i for i th element, if it can form
even no of (0 to i-1) pairs we don't take it, if it forms odd times we take prefixXor[i-1] and along
with it we check any nums are leftover by n%i if left we consider their xor by prefixXor[n%i]....
and we do this from 1 to n and for p's also simultaneously...
TC: O(N), SC: O(n)
Am i correct sir..?



remarks : be carefull in taking prefixxor[i]/prefixxor[i+1]...prefixxor[(n%(i+1))]
*/

public class magicalFormulas {
    public static void main(String[] args) throws NumberFormatException, IOException {

        //input
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine().trim());
        int arr[]=new int[n];
        String input[]=br.readLine().trim().split(" ");
        for(int i=0;i<n;i++){
            arr[i]=Integer.parseInt(input[i]);
        }

        int prefixXor[]=new int[n+1];
        prefixXor[0]=0;
        for(int i=1;i<=n;i++){
            prefixXor[i]=(prefixXor[i-1]^i);
        }

        int ans=0;
        for(int i=0;i<n;i++){
            ans^=arr[i];

            if(i!=0 && (n/(i+1))%2!=0){
                ans^=prefixXor[i];
            }

            if(n%(i+1)!=0){
                ans^=prefixXor[n%(i+1)];
            }
        }

        System.out.println(ans);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//just understand the question well thats it...he hides the point that k queries was the numbers of
//operations

public class gregAndArray{
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        //input
        String line1[]=br.readLine().trim().split(" ");
        int n=Integer.parseInt(line1[0]);
        int m=Integer.parseInt(line1[1]);
        int k=Integer.parseInt(line1[2]);

        long arr[]=new long[n+1];
        String line2[]=br.readLine().trim().split(" ");
        for(int i=1;i<=n;i++){
            arr[i]=Long.parseLong(line2[i-1]);
        }

        List<operation> list=new ArrayList<>();
        for(int i=0;i<m;i++){
            String line3[]=br.readLine().trim().split(" ");
            operation op=new operation(Integer.parseInt(line3[0]),Integer.parseInt(line3[1]), Integer.parseInt(line3[2]));
            list.add(op);
        }

        long opFreq[]=new long[m+1];
        Arrays.fill(opFreq, 0);
        for(int i=0;i<k;i++){
            String line4[]=br.readLine().trim().split(" ");
            int left=Integer.parseInt(line4[0]);
            int right=Integer.parseInt(line4[1]);
            opFreq[left-1]-=1;
            opFreq[right]+=1;
        }

        for(int i=m-1;i>=0;i--){
            opFreq[i]=opFreq[i]+opFreq[i+1];
        }

        long tmp[]=new long[n+1];
        Arrays.fill(tmp, 0);
        for(int i=0;i<m;i++){
            tmp[list.get(i).left-1]-=list.get(i).d * opFreq[i+1];
            tmp[list.get(i).right]+=list.get(i).d * opFreq[i+1];
        }

        for(int i=n-1;i>=0;i--){
            tmp[i]=tmp[i]+tmp[i+1];
        }

        for(int i=1;i<=n;i++){
            System.out.print(arr[i]+tmp[i]+" ");
        }
    }
}

class operation{
    int left;
    int right;
    int d;

    operation(int left,int right,int d){
        this.left=left;
        this.right=right;
        this.d=d;
    }
}
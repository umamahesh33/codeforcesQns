import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class missingAPoint {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int t=Integer.parseInt(br.readLine().trim());
        while(t-->0){

            int n=Integer.parseInt(br.readLine().trim());
            int size=4*n-1;
            long x[]=new long[size];
            long y[]=new long[size];
            for(int i=0;i<size;i++){
                String input[]=br.readLine().trim().split(" ");
                long xCord=Long.parseLong(input[0]);
                long yCord=Long.parseLong(input[1]);
                x[i]=xCord;
                y[i]=yCord;
            }

            long val1=0;
            for(int i=0;i<size;i++){
                val1^=x[i];
            }

            long val2=0;
            for(int i=0;i<size;i++){
                val2^=y[i];
            }

            System.out.print(val1+" "+val2+"\n");
        }
    }
}

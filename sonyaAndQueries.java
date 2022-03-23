import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class sonyaAndQueries {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // input
        int t = Integer.parseInt(br.readLine().trim());
        Map<Long, Long> hm = new HashMap<>();
        while (t-- > 0) {

            String line[] = br.readLine().trim().split(" ");

            if (line[0].charAt(0) == '+') {
                long num=Long.parseLong(line[1]);
                long tmp=convert(num);
                hm.put(tmp, hm.getOrDefault(tmp, 0l)+1);
            } else if (line[0].charAt(0) == '-') {
                long num=Long.parseLong(line[1]);
                long tmp=convert(num);
                hm.put(tmp, hm.get(tmp)-1);
            } else {
                long ans=0;
                long num=Long.parseLong(line[1]);
                long tmp=convert(num);
                ans+=hm.getOrDefault(tmp, 0l);
                System.out.println(ans);
            }
        }
    }

    private static long convert(long num) {
        int i=0;
        long ans=0;
        while(num!=0){ 
            if((num%10)%2!=0){
                ans+=(long)Math.pow(2, i);
            }
            i++;
            num=num/10;
        }

        return ans;
    }
}

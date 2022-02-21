import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class colliders {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        String str=br.readLine();
        String s1[]=str.split(" ");
        int n=Integer.parseInt(s1[0]);
        int m=Integer.parseInt(s1[1]);

        int activated[]=new int[n+1];
        int primes[]=new int[n+1];

        for(int i=0;i<n+1;i++){
            activated[i]=-1;
            primes[i]=1;
        }
        primes[0]=0;
        primes[1]=0;

        for(int i=2;i<n+1;i++){
            if(primes[i]==1){
                for(int j=i;i*j<=n;j++){
                    primes[i*j]=0;
                }
            }
        }

        boolean status=true;
        int diffNum=-1;

        for(int i=0;i<m;i++){
            String str2=br.readLine();
            String s2[]=str2.split(" ");

            int num=Integer.parseInt(s2[1]);

            if(str2.charAt(0) == '+'){

                if(activated[num]!= -1){
                    System.out.println("Already on");
                }else{

                    if(num == 1){
                        activated[num]=num;
                        System.out.println("Success");
                    }else{
                    if(primes[num]==1){
                        if(diffNum%num == 0){
                            System.out.println("Conflict with "+diffNum);
                        }else{
                            activated[num]=num;
                            System.out.println("Success");
                        }
                    }else if(status == true){
                        activated[num]=num;
                        status=false;
                        diffNum=num;
                        System.out.println("Success");
                    }else{
                        System.out.println("Conflict with "+diffNum);
                    }
                }
            }
            }

            if(str2.charAt(0) == '-'){

                if(activated[num] == -1){
                    System.out.println("Already off");
                }else{
                    if(primes[num] == 0){
                        activated[num]= -1;
                        status=true;
                        diffNum=-1;
                        System.out.println("Success");
                    }else{
                        activated[num]=-1;
                        System.out.println("Success");
                    }
                }
            }
        }
    }
}

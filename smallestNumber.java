import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class smallestNumber{
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        long arr[]=new long[4];
        String str=br.readLine();
        String s1[]=str.split(" ");

        for(int i=0;i<4;i++){
            arr[i]=Long.parseLong(s1[i]);
        }

        String str2=br.readLine();
        String s2[]=str2.split(" ");
        char arr2[]=new char[3];

        for(int i=0;i<3;i++){
            arr2[i]=s2[i].charAt(0);
        }

        List<Long> result=new ArrayList<>();

        recursionFunc(arr, arr2, result);

        long min=Long.MAX_VALUE;
        for(int i=0;i<result.size();i++){
           min=Math.min(min, result.get(i));
        }

        System.out.println(min);
        
    }

    private static void recursionFunc(long arr[],char arr2[], List<Long> result){

        if(arr.length==1){
            result.add(arr[0]);
            return;
        }

        if(arr.length==4){

            long tmp[]=new long[3];
            if(arr2[0]=='+'){
                tmp[0]=arr[0]+arr[1];
            }else{
                tmp[0]=arr[0]*arr[1];
            }
            for(int i=1;i<3;i++){
                tmp[i]=arr[i+1];
            }
            recursionFunc(tmp, arr2, result);

            tmp[0]=arr[0];
            if(arr2[0]=='+'){
                tmp[1]=arr[1]+arr[2];
            }else{
                tmp[1]=arr[1]*arr[2];
            }
            tmp[2]=arr[3];
            recursionFunc(tmp, arr2, result);

            tmp[0]=arr[0];
            tmp[1]=arr[1];
            if(arr2[0]=='+'){
                tmp[2]=arr[2]+arr[3];
            }else{
                tmp[2]=arr[2]*arr[3];
            }
            recursionFunc(tmp, arr2, result);

            
            if(arr2[0]=='+'){
                tmp[0]=arr[0]+arr[3];
            }else{
                tmp[0]=arr[0]*arr[3];
            }
            tmp[1]=arr[1];
            tmp[2]=arr[2];
            recursionFunc(tmp, arr2, result);


            if(arr2[0]=='+'){
                tmp[0]=arr[0]+arr[2];
            }else{
                tmp[0]=arr[0]*arr[2];
            }
            tmp[1]=arr[1];
            tmp[2]=arr[3];
            recursionFunc(tmp, arr2,result);


            if(arr2[0]=='+'){
                tmp[1]=arr[1]+arr[3];
            }else{
                tmp[1]=arr[1]*arr[3];
            }
            tmp[0]=arr[0];
            tmp[2]=arr[2];
            recursionFunc(tmp, arr2, result);

        }

        if(arr.length==3){

            long tmp[]=new long[2];
            if(arr2[1]=='+'){
                tmp[0]=arr[0]+arr[1];
            }else{
                tmp[0]=arr[0]*arr[1];
            }
            for(int i=1;i<2;i++){
                tmp[i]=arr[i+1];
            }
            recursionFunc(tmp, arr2, result);

            tmp[0]=arr[0];
            if(arr2[1]=='+'){
                tmp[1]=arr[1]+arr[2];
            }else{
                tmp[1]=arr[1]*arr[2];
            }
            recursionFunc(tmp, arr2, result);

            if(arr2[1]=='+'){
                tmp[0]=arr[0]+arr[2];
            }else{
                tmp[0]=arr[0]*arr[2];
            }
            tmp[1]=arr[1];
            recursionFunc(tmp, arr2, result);
        }

        if(arr.length==2){
            long tmp[]=new long[1];

            if(arr2[2]=='+'){
                tmp[0]=arr[0]+arr[1];
            }else{
                tmp[0]=arr[0]*arr[1];
            }

            recursionFunc(tmp, arr2, result);

        }
    }
}
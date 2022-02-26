import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class toAddOrNotToAdd {
    public static void main(String[] args) throws IOException {
        //input
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String line1[]=br.readLine().trim().split(" ");
        int n=Integer.parseInt(line1[0]);
        int k=Integer.parseInt(line1[1]);
        
        String line2[]=br.readLine().trim().split(" ");
        long arr[]=new long[n];
        for(int i=0;i<n;i++){
            arr[i]=Long.parseLong(line2[i]);
        }

        Arrays.sort(arr);

        //caluclating the prefix Sum
        long prefixSum[]=new long[n];
        prefixSum[0]=arr[0];
        for(int i=1;i<n;i++){
            prefixSum[i]=prefixSum[i-1]+arr[i];
        }

        long prevMin=-1;//to track the minimum element with max count
        long prevCount=0;

        //traversing for every element in the array
        int i=0;
        while(i<n){
            long num=arr[i];
            int firstOcc=i;
            int lastOcc=i;

            //find last occ of the element
            int low=i;
            int high=n-1;
            int mid=-1;
            while(low<=high){
                mid=(low+high)/2;
                if(arr[mid]>num){
                    high=mid-1;
                }else if(arr[mid]<num){
                    low=mid+1;
                }else{
                    if(mid==n-1){
                        lastOcc=mid;
                        break;
                    }else if(arr[mid+1]>num){
                        lastOcc=mid;
                        break;
                    }else{
                        low=mid+1;
                    }
                }
            }
            

            //after finding the last occ we want to know how many elements can be converted into
            //the present num, so for that i applied binary search on the range of[o to firstOcc-1]
            //so that at particular mid we check wether sum fromthat mid to firstOcc plus k
            // was greater than or equal to lenBtw*num if it was lesser than we are intrested
            //in lesser len in btw so we move right

            // if it was greater than or equal the we check for mid-1 if eqn with mid-1 bcoms lesser
            // than we can consider len of firstOcc-mid
            //if eqn of mid-1 also gives greater than or equal then we are intrsted in increasing len
            //btw so we move to left

            //here we need to think that we should consider the elements that are leftmost to our curr ele
            long len=0;
            low=0;
            high=firstOcc-1;
            mid=-1;
            while(low<=high){
                mid=(low+high)/2;
                if(!isPossible(mid, num, k, prefixSum,firstOcc)){//if sumObt+k<lenBtw*num was true
                    low=mid+1;
                }else{
                    if(mid==0){ //if mid==0 and came to this condition there is no other check psbl so we
                                //here len was from 0 to first Occ
                        len=firstOcc;
                        break;
                    }
                    if(!isPossible(mid-1, num, k, prefixSum,firstOcc)){//if sumObt+k<lenBtw*num was true
                        len=i-mid;
                        break;
                    }else{
                        high=mid-1;//if sumObt+k<lenBtw*num was false
                    }
                }
            }


            //updating it if cnt was greater than prev
            len+=(lastOcc-firstOcc+1); 
            if(len>prevCount){
                prevCount=len;
                prevMin=num;
            }

            //incrmenting i to see the next element
            i=lastOcc+1;
        }
        System.out.println(prevCount+" "+prevMin);
    }

    private static boolean isPossible(int mid,long num,long k,long []prefixSum,int firstOcc){

        long sumObt;
        //if mid was not at zero index
        if(mid>0){
            sumObt=prefixSum[firstOcc]-num-prefixSum[mid-1];
        }else{
            sumObt=prefixSum[firstOcc]-num;
        }
        int len=firstOcc-mid;   //caluclating the len from our present mid to num
        if(sumObt+k<num*len) return false;  //if the toatl sum that we can make from there was not
        //equal to the distance btw them * num then we can not convert that  many elements as our 
        // num so we return false

        return true;//esle if sumObt+k was >= req to convert 'len' elements in between as oue num we return true
    }
}

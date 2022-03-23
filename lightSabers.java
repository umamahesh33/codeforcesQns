import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * here the question is asking for the sub array that conatins k1 color horses of color 1
 * k2 color horses of color 2 and similarly goes onn...till km horses of color m
 * 
 * we need to find the subarray that contains k1 horses of color1 and k2 horses of color2 and like that
 * we might need to remove some repeated horses whose freq was greater than req so we need a subarray
 * so that we should remove the min no of horses
 * 
 * this might be converted to a problem like samllest substring with all characters..(two ptrs)
 * 
 * so here we follow two ptr approach
 * 
 * 1 mainatain freq array..a freq array of size m+1 will be good because..all n elements are [1 to m]
 * 2 first find a window ,containing as per requirements of k[] array
 * 3 trim the first window...remove unwanted horses
 * 4 keep track of min no of horses to be removed
 * 5 do the same process of add element ,trim if psbl, update min
 * 
 * 
 * be care full of edgecases like if we could not form a combination then we should return -1
 * 
 * tc: o(n) and sc:o(m)
 */

public class lightSabers {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        //input..
        String line1[]=br.readLine().trim().split(" ");
        int n=Integer.parseInt(line1[0]);
        int m=Integer.parseInt(line1[1]);

        int arr[]=new int[n];
        String line2[]=br.readLine().trim().split(" ");
        for(int i=0;i<n;i++){
            arr[i]=Integer.parseInt(line2[i]);
        }

        String line3[]=br.readLine().trim().split(" ");
        int k[]=new int[m+1];
        for(int i=1;i<=m;i++){
            k[i]=Integer.parseInt(line3[i-1]);
        }


        //applying two ptr apporach from here...
        int freq[]=new int[m+1];    
        Arrays.fill(freq, 0);
        int extra=0;            //necessary to know how many should be removed from it
        int cnt=0;              //during first window caluclation we should know that we accuired all req elements now we can break the while loop
        int i=0;
        int j=0;

        int cnt2=0;             //cnt2 will hold the value of, no of differen color horses req
        for(int l=0;l<=m;l++){
            if(k[l]>0) cnt2++;
        }

        while(j<n && cnt<cnt2){         //cnt<cnt2 if cnt==cnt2 then we can say that all req color horses are came into window and we can break
            freq[arr[j]]++;
            if(freq[arr[j]]==k[arr[j]]){    //when the we have got exact freq of requiredness 
                cnt++;
            }

            if(freq[arr[j]]>k[arr[j]]){
                extra++;                    //if we have more freq than req this was extra in the window and we may remove this many horses
            }
            j++;
        }

        if(cnt<cnt2){
            System.out.println(-1);         //if there were no crct combintaions this happens and we return -1
            return;
        }

        while(freq[arr[i]]>k[arr[i]]){      //trimming from start,we trim this untill our freq combination of k[] doesn't broke
            freq[arr[i]]--;
            extra--;
            i++;
        }

        int min=extra;                      //update min as the elements that were present extra even after trimming

        while(j<n){
            //add element
            if(freq[arr[j]]>=k[arr[j]]){
                extra++;
                freq[arr[j]]++;
                j++;
            }else{
                freq[arr[j]]++;
                j++;
            }

            //trim
            while(freq[arr[i]]>k[arr[i]]){
                freq[arr[i]]--;
                extra--;
                i++;
            }

            //update extra
            min=Math.min(extra, min);
        }

        System.out.println(min);
    }
}

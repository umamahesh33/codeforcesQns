import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// very very important to know for all inversion count problems...

/*
Vivekanand Vivek
I'll suggest a very relatable thought from the conventional inversion count problem.

Inversion count problem asks for pairs of i, j such that i < j  but a[i] > a[j]. Let's call i < j as
our first condition and a[i] > a[j] as second. Our original array is already sorted on the basis of 
condition 1 by default, obviously that's how indexing is there in an array. Now all that we do is 
while applying merge sort we count for pairs obeying condition 2 and because the array was already 
originally sorted based on condition 1, so whatever we get finally would be our answer.

Similarly when you read the question you've asked doubt in, there again are 2 conditions i.e. S1 < S2 
but rev(S1) > rev(S2). They can be thought of as condition 1 and 2 respectively. So, how about first 
sorting the array on the basis of condition 1 and then sorting it again on basis of 2 and while doing
so just count the pairs obeying condition 2, and because they were already sorted on condition 1, 
what you get finally could be your answer.

NOTE: you might want to read and understand what I wrote few times alongwith some proper thoughts 
and examples. Also don't rush to conclusions, solutions or code. Rather think and formulate something 
of your own and revert back.
*/

// as per the comment we should have two conditions on applying invesrion count i<j and arr[i]>arr[j]



public class kaos{
    

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine().trim());
        List<String> list=new ArrayList<>();
        for(int i=0;i<n;i++){
            String str=br.readLine().trim();
            list.add(str);
        }

        Collections.sort(list);//sorting list to get as i<j ===> string[i]<string[j] lexicographically

        //we need to have the rank in integers according to the reversed value of that string
        //so we reverse all of them and see thier sorted order
        //and assign its rank in new array as we stored the originalIndex,where it was
        List<revString> list2=new ArrayList<>();
        for(int i=0;i<n;i++){
            StringBuilder str=new StringBuilder(list.get(i));
            str=str.reverse();
            String s=String.valueOf(str);
            revString r=new revString(s,i);
            list2.add(r);
        }

        Collections.sort(list2,new Comparator<revString>() {
            @Override
            public int compare(revString o1, revString o2) {
                return o1.str.compareTo(o2.str);
            }
        });//sorting the reversed strings

        int arr[]=new int[n];
        for(int i=0;i<n;i++){       
            arr[list2.get(i).originalIndex]=i;  //assigning the ranks
        }

        System.out.println(mergeSort(arr,0,n-1));//calling merge to find the inversion count

    }

    private static long mergeSort(int[] arr, int l, int r) {
        if(l==r) return 0;

        int m=(l+r)/2;
        long cnt1=mergeSort(arr, l, m);
        long cnt2=mergeSort(arr, m+1, r);

        long cnt3=merge(arr,l,m,r);

        return cnt1+cnt2+cnt3;
    }

    private static long merge(int[] arr, int l, int m, int r) {

        int tmp[]=new int[r-l+1];
        int i=l;
        int j=m+1;
        int c=0;
        long count=0;
        while(i<=m && j<=r){
            if(arr[i]<=arr[j]){
                tmp[c++]=arr[i++];
            }else{
                count+=(m-i+1);
                tmp[c++]=arr[j++];
            }
        }

        while(i<=m){
            tmp[c++]=arr[i++];
        }

        while(j<=r){
            tmp[c++]=arr[j++];
        }

        c=0;
        for(i=l;i<=r;i++){
            arr[i]=tmp[c++];
        }
        return count;
    }
}

class revString{
    String str;
    int originalIndex;

    revString(String str, int originalIndex){
        this.str=str;
        this.originalIndex=originalIndex;
    }
}

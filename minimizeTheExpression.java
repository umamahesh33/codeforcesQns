public class minimizeTheExpression {
    public static void main(String[] args) {
        int arr1[]={5, 8, 10, 15};
        int arr2[]={ 6, 9, 15, 78, 89};
        int arr3[]={2, 3, 6, 6, 8, 8, 10};

        int m=arr1.length;
        int n=arr2.length;
        int p=arr3.length;

        int ans=Integer.MAX_VALUE;
        int i=0,j=0,k=0;

        while(i<m && j<n && k<p){
            int max=Math.max(arr1[i],Math.max(arr2[j], arr3[k]));
            int min=Math.min(arr1[i], Math.min(arr2[j], arr3[k]));

            ans=Math.min(ans,max-min);

            if(min==arr1[i]){
                i++;
            }else if(min==arr2[j]){
                j++;
            }else{
                k++;
            }
        }

        System.out.println(ans);
    }
}

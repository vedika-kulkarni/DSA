class Solution {
    public static int[] productExceptSelf(int arr[]) {
        // code here
        int n = arr.length;
        int [] res = new int[n];
        for(int i=0; i<n; i++){
           res[i]=1;
        }
        int prefix = 1;
        for(int i=0; i<n; i++){
            res[i] = prefix;
            prefix *= arr[i];
        }
        
        int suffix = 1;
        for(int i = n-1; i>=0; i--){
            res[i] *= suffix;
            suffix *= arr[i];
        }
        return res;
    }
}

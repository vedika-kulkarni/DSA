class Solution {
    public ArrayList<Integer> getMinMax(int[] arr) {
        // code Here
        ArrayList<Integer> a = new ArrayList<>();
        int min = arr[0];
        int max = arr[0];
        
        for(int i=1; i<arr.length; i++){
            if(arr[i] > max){
                max = arr[i];
            }
            if(arr[i]<min){
                min = arr[i];
            }
        }
        a.add(min);
        a.add(max);
        return a;
    }
}

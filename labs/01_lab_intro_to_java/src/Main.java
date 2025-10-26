//01-unique-substring-finder
public static String longestUniqueSubstring(String s){
    StringBuilder sb = new StringBuilder();
    String longest="";
    for (int i=0; i < s.length(); i++){
        char c = s.charAt(i);
        int index=sb.indexOf(String.valueOf(c));
        if (index!=-1) {
            sb.delete(0, index+1);
        }
        sb.append(c);
        if (sb.length()>longest.length()){
            longest= sb.toString();
        }
    }
    return longest;
}
//02-task-distributor
public static int minDifference(int[] tasks){
    int n = tasks.length;
    int sum=0;
    for (int i=0; i < n; i++) {
        sum+=tasks[i];
    }
    boolean[] sums_to_n = new boolean[sum+1];
    sums_to_n[0]=true;

    for (int i=0; i<n; i++){
            for (int j=sum; j>=tasks[i]; j--) {
                if (sums_to_n[j - tasks[i]]) {
                    sums_to_n[j]=true;
                }
            }
    }
    for (int i=sum/2; i>=0; i--){
        if(sums_to_n[i]){
            return sum-i*2;
        }
    }
    return -1;
}
void main() {
    System.out.println(longestUniqueSubstring("abcabcbb")); //"abc"
    System.out.println(longestUniqueSubstring("bbbbb")); //"b"
    System.out.println(longestUniqueSubstring("pwwkew")); //"wke
    System.out.println(longestUniqueSubstring("abcdefg")); //"abcdefg"
    System.out.println(longestUniqueSubstring("x")); //"x"
    System.out.println(longestUniqueSubstring("")); //""

    System.out.println(minDifference(new int[]{1,2,3,4,5})); //1
    System.out.println(minDifference(new int[]{10,20,15,5})); //0
    System.out.println(minDifference(new int[]{7,3,2,1,5,4})); //0
    System.out.println(minDifference(new int[]{9,1,1,1})); //6
    System.out.println(minDifference(new int[]{})); //0
    System.out.println(minDifference(new int[]{120})); //120
    System.out.println(minDifference(new int[]{30, 30})); //0
}

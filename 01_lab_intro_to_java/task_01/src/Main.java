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

void main() {
    System.out.println(longestUniqueSubstring("abcabcbb"));
    System.out.println(longestUniqueSubstring("bbbbb"));
    System.out.println(longestUniqueSubstring("pwwkew"));
    System.out.println(longestUniqueSubstring("abcdefg"));
    System.out.println(longestUniqueSubstring("x"));
    System.out.println(longestUniqueSubstring(""));
}

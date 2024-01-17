public class Q1 {
    public static String reverseString(String input) {
        char chars[] = input.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        
        while (left < right) {
            // Swap charaters
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            
            left++;
            right--;
        }
        
        return new String(chars);
    }

    public static void main(String args[]) {
        String input = "retlaohS";
        String reversed = reverseString(input);
        System.out.println(reversed);
    }
}
public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque dq = new ArrayDeque();
        for(int i=0;i<word.length();i++){
            dq.addLast(word.charAt(i));
        }
        return dq;
    }
    private boolean isPalindromeHelper(Deque dq){
        if(dq.size()<=1)
            return true;
        if(dq.removeFirst() == dq.removeLast()){
            return isPalindromeHelper(dq);
        }
        else return false;
    }
    public boolean isPalindrome(String word){
        return isPalindromeHelper(wordToDeque(word));
    }
    public boolean isPalindrome(String word, CharacterComparator cc){
        return isPalindromeHelper(wordToDeque(word), cc);
    }
    private boolean isPalindromeHelper(Deque dq, CharacterComparator cc){
        if (dq.size() <= 1)
            return true;
        if(cc.equalChars((Character) dq.removeFirst(), (Character) dq.removeLast())){
            return isPalindromeHelper(dq, cc);
        }
        else return false;
    }
}

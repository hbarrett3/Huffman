import java.util.*;

public class Test {

    public static void main(String[] args) {
        
        String test1 = generateRandomString(10);
        System.out.println(test1);
        HashMap<Character, Integer> hashMap1 = countFrequencies(test1);
        printKeyValuePairs(hashMap1);

        /* 
        String test2 = generateRandomString(100);
        System.out.println(test2);
        HashMap<Character, Integer> hashMap2 = countFrequencies(test2);
        printKeyValuePairs(hashMap2);
        */

    }

    // generates random character
    private static char generateRandomCharacter(){
        return (char)('A' + Math.random()*26);
    }

    // generates random string
    private static String generateRandomString(int length){
        String string = "";

        for (int i = 0; i < length; i++){
            string += generateRandomCharacter();
        }
        return string;
    }

    // TO BE ADDED TO ANOTHER FILE
    // creates hashmap mapping characters to their frequencies
    private static HashMap<Character,Integer> countFrequencies(String word){

        HashMap<Character, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < word.length(); i++){
            if(hashMap.get(word.charAt(i)) == null){
                hashMap.put(word.charAt(i), 1);
            }
            else{
                int temp = hashMap.get(word.charAt(i));
                temp++;
                hashMap.put(word.charAt(i), temp);
            }
        }

        return hashMap;
    }

    private static void printKeyValuePairs(HashMap<Character, Integer> map) {
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
    }

}

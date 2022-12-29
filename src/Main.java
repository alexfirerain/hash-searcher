import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        String string = "Alibaba or Alibubab? I do not know!";
        String query = "b*b";

        answer(string, query);
    }

    public static void answer(String source,String pattern) {
        System.out.println("Поиск соответствий шаблону <<" + pattern + ">>\n в строке <<" + source + ">>:");
        List<String> result = findOccurrences(source, pattern);
        if (result.isEmpty())
            System.out.println("Соответствий не найдено.");
        else
            result.forEach(System.out::println);
    }

    private static List<String> findOccurrences(String source, String pattern) {
        List<String> result = new ArrayList<>();
        if (pattern.length() > source.length())
            return result;

        int patternHash = 0;
//        asterik_position = позиция '*' в pattern
        int asteriskPosition = pattern.indexOf('*');

//        for (char c : source.toCharArray()) {
//            if (c == '*') continue;
//            patternHash += c;
//        }

//        pattern_hash = сумма кодов символов в pattern без учёта *
        patternHash = source.chars().filter(i -> i != '*').sum();

        int windowHash = 0;
//        for start от 0 до длина(source) - длина(pattern) + 1
        for (int start = 0; start < source.length() - pattern.length(); start++) {
//            if start == 0:
            if (start == 0) {
                //  window_hash = сумма кодов первых длина(pattern) символов source
                windowHash = source.substring(start, start + pattern.length()).chars().sum();
                //  window_hash -= код символа в source на позиции asterik_position
                windowHash -= source.charAt(start + asteriskPosition);
                //    else:
            } else {
                //        window_hash -= код символа в source на позиции start-1
                windowHash -= source.charAt(start + source.charAt(start - 1));
                //        window_hash += код символа в source на позиции start+длина(pattern) - 1
                windowHash += source.charAt(start + pattern.length() - 1);
                //        window_hash -= код символа в source на позиции start-1+asterik_position
                windowHash -= source.charAt(start + asteriskPosition);
            }
        }
    //        if window_hash == pattern_hash:
        if(windowHash == patternHash) {
            //        for i от 0 до длина(pattern):
            for (int i = 0; i < pattern.length(); i++) {
                //        if pattern[i] != '*' И source[start + i] != pattern[i]:
                //        не подходит
                //        если подошёл, то добавим start в found
                //        window_hash += код символа в source на позиции start+asterik_position
            }
        }
//        return found

        return result;
    }


}
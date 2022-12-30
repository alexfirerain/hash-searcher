import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String string_1 = "Alibaba or Alibubab? I do not know!";
        String query_1 = "b*b";

        String string_2 = "Карл у Клары украл кораллы? Оскар как раз!";
        String query_2 = "к*р";

        String string_3 = "От топота копыт пыль по полю летит? Путь троп торопит!";
        String query_3 = "п*т";

        answer(string_1, query_1, true);
        answer(string_2, query_2, false);
        answer(string_3, query_3, false);
        answer(string_3, query_3, true);

    }

    public static void answer(String source, String pattern, boolean caseSensitive) {
        System.out.println("Поиск соответствий шаблону <<" + pattern + ">> в строке\n<<" + source + ">>:");
        if (!caseSensitive)
            System.out.println(("(без разбора регистра)"));
        List<String> result = findOccurrences(source, pattern, caseSensitive);
        if (result.isEmpty())
            System.out.println("Соответствий не найдено.");
        else
            result.forEach(System.out::println);
        System.out.println();
    }

    private static List<String> findOccurrences(String source, String pattern, boolean caseSensitive) {

        String internal = source;
        int windowSize = pattern.length();
        List<String> result = new ArrayList<>();

        if (windowSize > source.length()) {
            return result;
        }

        int asteriskPosition = pattern.indexOf('*');
        int patternHash = caseSensitive ?
                pattern.chars().filter(i -> i != '*').sum() :
                pattern.chars().filter(i -> i != '*').map(Character::toLowerCase).sum();
        int windowHash = 0;

        if (!caseSensitive)
            internal = internal.toLowerCase();

        for (int start = 0; start < source.length() - windowSize; start++) {

            if (start == 0) {
                windowHash = internal.substring(start, start + windowSize).chars().sum();
            } else {
                windowHash -= internal.charAt(start - 1);
                windowHash += internal.charAt(start + windowSize - 1);
            }
            if(windowHash - internal.charAt(start + asteriskPosition) == patternHash) {
                for (int i = 0; i < windowSize; i++) {
                    char c = pattern.charAt(i);
                    if (c != '*' && c != internal.charAt(start + i))
                        break;
                    if (i == windowSize - 1)
                        result.add(source.substring(start, start + windowSize));
                }
            }
        }

        return result;
    }


}
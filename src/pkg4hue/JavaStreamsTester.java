/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4hue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author raffa
 */
public class JavaStreamsTester {

    static List<String> strings = new ArrayList<>();
    static List<Integer> ints = new ArrayList<>();

    public static void init() {

        strings.add("FOO");
        strings.add("");
        strings.add("BAR");

        ints.add(2);
        ints.add(8);
        ints.add(4);
        ints.add(6);
        ints.add(2);
        ints.add(2);

    }

    public static void main(String[] args) {
        init();
        System.out.println(getCountEmptyString(strings));
        System.out.println(getCountLength3(strings));
        System.out.println(deletEmtyStringsUsingJava7(strings));
        System.out.println(getMergedString(strings, ";"));
        System.out.println(getSquares(ints));
        System.out.println(getMax(ints));
        System.out.println(getMin(ints));
        System.out.println(getSum(ints));
        System.out.println(getAverge(ints));

    }

    private static int getCountEmptyString(List<String> strings) {
        if (strings != null && !strings.isEmpty()) {
            return strings.stream().filter(s -> s.equals("")).collect(Collectors.toList()).size();
        }
        return strings.size();

    }

    private static int getCountLength3(List<String> strings) {
        if (strings != null && !strings.isEmpty()) {
            return strings.stream().filter(s -> s.length() == 3).collect(Collectors.toList()).size();
        }
        return strings.size();

    }

    private static List<String> deletEmtyStringsUsingJava7(List<String> strings) {
        if (strings != null && !strings.isEmpty()) {
            return strings.stream().filter(s -> !s.equals("")).collect(Collectors.toList());
        }
        return strings;

    }

    private static String getMergedString(List<String> strings, String separator) {
        if (strings != null && !strings.isEmpty()) {
            return strings.stream().reduce((a, b) -> {
                return a + separator + b;
            }).get();
        }
        return "";
    }

    private static List<Integer> getSquares(List<Integer> numbers) {

        return numbers.stream().map(s -> s * s).collect(Collectors.toList());
    }

    private static int getMax(List<Integer> numbers) {
        return numbers.stream().max((o1, o2) -> {
            return o1 - o2;
        }).get();
    }

    private static int getMin(List<Integer> numbers) {
        return numbers.stream().max((o1, o2) -> {
            return o2 - o1;
        }).get();
    }

    private static int getSum(List<Integer> numbers) {
        return numbers.stream().mapToInt(s -> s + s).sum();
    }

    private static int getAverge(List<Integer> numbers) {
        return (int) numbers.stream().mapToInt(i -> i).average().getAsDouble();
    }

}

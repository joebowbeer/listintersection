package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        List<Integer> a = Arrays.asList(5, 3, 1);
        List<Integer> b = Arrays.asList(6, 4, 7, 5);
        Comparator<Integer> c = new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return (o1.compareTo(o2));
            }
        };
        System.out.println(intersectA(a, b, c));
        System.out.println(intersectB(a, b, c));
    }

    public static <T> List<T> intersectA(List<T> a, List<T> b, Comparator<? super T> c) {
        Collections.sort(a, c);
        Collections.sort(b, c);
        List<T> res = new ArrayList<T>();
        int i, j;
        for (i = 0, j = 0; i < a.size() && j < b.size();) {
            T a0 = a.get(i);
            T b0 = b.get(j);
            int c0 = c.compare(a0, b0);
            if (c0 < 0) {
                i++;
            } else if (c0 > 0) {
                j++;
            } else {
                res.add(a0);
                i++;
                j++;
            }
        }
        return res;
    }

    public static <T> List<T> intersectB(List<T> a, List<T> b, Comparator<? super T> c) {
        Queue<T> qa = new PriorityQueue(a.size(), c);
        qa.addAll(a);
        Queue<T> qb = new PriorityQueue(b.size(), c);
        qb.addAll(b);
        List<T> res = new ArrayList<T>();
        while (!qa.isEmpty() && !qb.isEmpty()) {
            int i = c.compare(qa.peek(), qb.peek());
            if (i < 0) {
                qa.remove();
            } else if (i > 0) {
                qb.remove();
            } else {
                res.add(qa.remove());
                qb.remove();
            }
        }
        return res;
    }
}

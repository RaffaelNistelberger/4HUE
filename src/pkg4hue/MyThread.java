/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4hue;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author raffa
 */
public class MyThread implements Runnable {

    private List<Integer> list;
    private int divider;

    public MyThread(List<Integer> list, int teiler) {
        this.list = list;
        this.divider = teiler;
    }

    @Override
    public void run() {
        list = list.stream().filter(s -> s % divider == 0).collect(Collectors.toList());
        list.forEach(s -> System.out.println(s));
    }

}

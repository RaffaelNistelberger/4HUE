/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4hue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 *
 * @author raffa
 */
public class MyGaußscheThread implements Callable<Integer> {

    int untergrenze;
    int erg = 0;

    List<Integer> list = new ArrayList<>();

    public MyGaußscheThread(List<Integer> list, int untergrenze) {
        this.list = list;
        this.untergrenze = untergrenze;
    }

    @Override
    public Integer call() throws Exception {
        for (int j = 0; j < list.size(); j++) {
            erg += list.get(j);
        }
        return erg;
    }

}

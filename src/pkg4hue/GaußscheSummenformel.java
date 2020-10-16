/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4hue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author raffa
 */
public class GaußscheSummenformel {

    List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        GaußscheSummenformel m = new GaußscheSummenformel();

        int untergrenze = 1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("n>");
        int obergrenze = Integer.parseInt(scanner.nextLine());

        int newObergrenze = 100;
        for (int i = untergrenze; i <= obergrenze; i++) {
            m.list.add(i);
        }

        int chunk = (int) (obergrenze / 100 + 1);
        ExecutorService tpe = Executors.newFixedThreadPool(chunk);
        List<Integer> sublist = null;
        List<Future> listCalls = new ArrayList<>();

        int erg = 0;
        for (int i = 0; i < chunk; i++) {

            if (newObergrenze > obergrenze) {
                newObergrenze = obergrenze;
            }
            sublist = m.list.subList(untergrenze - 1, newObergrenze);

            MyGaußscheThread call = new MyGaußscheThread(sublist, untergrenze);
            listCalls.add(tpe.submit(call));

            untergrenze += 100;
            newObergrenze += 100;
        }

        for (Future listCall : listCalls) {
            try {
                erg += (int) listCall.get();
            } catch (InterruptedException ex) {
                Logger.getLogger(GaußscheSummenformel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExecutionException ex) {
                Logger.getLogger(GaußscheSummenformel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println(erg);
        tpe.shutdown();
    }

}

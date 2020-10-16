/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4hue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author raffa
 */
public class BeispielEins {

    int number = 0;
    final String fileName = "numbers.csv";
    List<Integer> list = new ArrayList<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BeispielEins m = new BeispielEins();
        Scanner scanner = new Scanner(System.in);
        System.out.println("chunks>");
        int chunks = Integer.parseInt(scanner.nextLine());
        System.out.println("divider>");
        int divider = Integer.parseInt(scanner.nextLine());

        m.lesen();

        ThreadPoolExecutor tpe = (ThreadPoolExecutor) Executors.newFixedThreadPool(chunks);
        List<Integer> sublist = null;
        int teilerFurTeilBereiche = m.list.size() / chunks;
        for (int i = 0; i < chunks; i++) {
            sublist = m.list.subList(i * teilerFurTeilBereiche, i * teilerFurTeilBereiche + teilerFurTeilBereiche);
            MyThread mt = new MyThread(sublist, divider);
            tpe.execute(mt);

        }
        tpe.shutdown();
    }

    public void auswahl() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Geben Sie die Summe mit!");
        int zahl = Integer.parseInt(scanner.nextLine());
        number = zahl;
    }

    public void lesen() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.fileName));) {

            String line = br.readLine();
            while (line != null) {

                String[] tmp = line.split(":");
                if (line.equals("")) {
                    line = br.readLine();
                    continue;
                }
                for (int i = 0; i < tmp.length; i++) {
                    if (stringLesen(tmp[i])) {
                        list.add(Integer.parseInt(tmp[i]));
                    }
                }
                line = br.readLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(BeispielEins.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean stringLesen(String line) {
        try {
            Integer.parseInt(line);
            return true;
        } catch (NumberFormatException n) {
            return false;
        }
    }

}

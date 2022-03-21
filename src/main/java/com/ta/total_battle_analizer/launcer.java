/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ta.total_battle_analizer;

import java.awt.AWTException;
import java.util.Scanner;
/**
 *
 * @author Artem
 */
public class launcer {
    public static void main(String[] args) throws AWTException, InterruptedException, Exception {
        Object[] info;
        tools t = new tools();
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("Введите количество сундуков: ");
            int click_count = in.nextInt();
            //t.RecordnezeText(0);
            Thread.sleep(7000);
            //t.makeScreenShot(99);
            //int click_count;//Количество сундуков которое открываем
            //click_count = 1;
            for (int i = 0; i < click_count; i++) {
                //Thread.sleep(1000);
                t.clickButton();
                t.makeScreenShot(i);
                info = t.RecordnezeText(i);
                t.writeTXT(info,i);
            }
        }
    }
    
}

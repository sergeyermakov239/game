package com.example.game;

import org.springframework.boot.SpringApplication;

import java.util.ArrayList;
import java.util.Scanner;

public class Game1Application {
    public static void main(String[] args) {
        SpringApplication.run(GameApplication.class, args);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество цифр в строчке");
        int n= scanner.nextInt();
        System.out.println("Введите строчку из цифр");
        ArrayList<Integer> t=new ArrayList<>();
        int k=0;                               // количество нулей
        for (int i=0;i<n;i++){
            int a= scanner.nextInt();
            if (a==0){
                k+=1;
            }
            t.add(i, a);
        };
        System.out.println(t);
        System.out.println(k);

        System.out.println("За ход вы можете 1) изменить в меньшую сторону одну из цифр, максимум до 0 (отрицательных величин в игре нет);\n" +
                "   2) стереть любой ноль и все цифры справа него, сократив таким образом длину полосы.");
        int g = 1;     //показывает, кто ходит в данный момент 1-я, 2-компьютер
        while (k>0){
            System.out.println("Ваш ход");
            System.out.println("Введите, какой ход вы делаете : 1 или 2");
            g=1;
            int f= scanner.nextInt();
            if (f==1){
                System.out.println("Введите номер цифры, которую вы хотите изменить (нумерация с 0) ");
                int index= scanner.nextInt();
                System.out.println("Введите, до какого значения вы хотите эту цифру уменьшить ");
                int  number1 = scanner.nextInt();
                if (number1>=t.get(index)){
                    System.out.println("Цифры нельзя увеличивать, введите ещё раз");
                    number1= scanner.nextInt();
                } else
                if (number1<0){
                    System.out.println("Отрицательных величин в игре нет, введите ещё раз");
                    number1= scanner.nextInt();
                } else{
                    t.set(index,number1);
                    if (number1==0){
                        k+=1;
                    }
                }


            } else if (f==2){
                System.out.println("Введите индекс нуля , справа от которого вы хотите стереть цифры ");
                int index=scanner.nextInt();
                if (t.get(index)!=0){
                    System.out.println("Вы должны были ввести индекс нуля, введите ещё раз ");
                    index=scanner.nextInt();
                }
                for (int i=t.size()-1;i>=index;i=i-1){
                    if (t.get(i)==0){
                        k=k-1;
                    };
                    t.remove(i);
                }
            }
            if (k>0) {
                System.out.println("Теперь строка выглядит так");
                System.out.println(t);
                System.out.println("Ход компьютера");
                g = 2;
                f = (int)(Math.round(Math.random() + 1));
                System.out.println(f);
                if (f == 1&&t.size()>k) {
                    int b=(int) (Math.random() * (t.size() - 1));
                    while (t.get(b)==0){
                        b=(int) (Math.random() * (t.size() - 1));
                    }
                    int index = b;
                    int a = (int) (Math.random() * t.get(index));
                    if (t.get(index) != 0) {
                        if (a == 0) {
                            k += 1;
                        }
                    }
                    t.set(index, a);
                } else  {
                    int index = t.lastIndexOf(0);
                    for (int i=t.size()-1;i>=index;i=i-1) {
                        if (t.get(i) == 0) {
                            k = k - 1;
                        }
                        t.remove(i);
                    }
                }
                System.out.println("Теперь строка выглядит так");
                System.out.println(t);
            }
        }
        if(g==1){
            System.out.println("Компьютер победил");
        } else {
            System.out.println("Вы победили!");
        }

    }
}

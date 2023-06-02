/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cpu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.IOException;

/**
 *
 * @author Osaf Ahmed
 */
public class CPU {

    /**
     * @param args the command line arguments
     */
    static ArrayList<Integer> PlayerPostion = new ArrayList<>();
    static ArrayList<Integer> CPUPostion = new ArrayList<>();

    public static void main(String[] args) {
        // TODO code application logic here
        char[][] GameBoard = {{' ', '|', ' ', '|', ' '},
        {'-', '+', '-', '+', '-'},
        {' ', '|', ' ', '|', ' '},
        {'-', '+', '-', '+', '-'},
        {' ', '|', ' ', '|', ' '}
        };
        PrintBoard(GameBoard);
        while (true) {
            System.out.println("---------------------");
            System.out.println("Player Turn: Enter Value between 1-9");
            int PlayerValue = new Scanner(System.in).nextInt();

            while (PlayerPostion.contains(PlayerValue) || CPUPostion.contains(PlayerValue)) {
                System.out.println("AlreadyTaken reEnter");
                PlayerValue = new Scanner(System.in).nextInt();
            }
            InsertValues(GameBoard, PlayerValue, "Player");

            String result = Winning();
            if (result.length() > 0) {
                PrintBoard(GameBoard);
                System.out.println(result);

                break;
            }

            Random rand = new Random();
            int CPUValue = rand.nextInt(9) + 1;
            while (PlayerPostion.contains(CPUValue) || CPUPostion.contains(CPUValue)) {

                CPUValue = rand.nextInt(9) + 1;
            }
            InsertValues(GameBoard, CPUValue, "CPU");

            result = Winning();
            if (result.length() > 0) {
                PrintBoard(GameBoard);
                System.out.println(result);
                break;
            }
            PrintBoard(GameBoard);
        }
    }

    public static void PrintBoard(char[][] GameBoard)  {
       
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println();
        for (char[] print : GameBoard) {
            System.out.println(print);
        }
    }

    public static void InsertValues(char[][] GameBoard, int postion, String User) {
        char symbol = ' ';
        if (User.equals("Player")) {
            symbol = 'X';
            PlayerPostion.add(postion);
        } else if (User.equals("CPU")) {
            symbol = '0';
            CPUPostion.add(postion);
        }

        switch (postion) {
            case 1:
                GameBoard[0][0] = symbol;
                break;
            case 2:
                GameBoard[0][2] = symbol;
                break;
            case 3:
                GameBoard[0][4] = symbol;
                break;
            case 4:
                GameBoard[2][0] = symbol;
                break;
            case 5:
                GameBoard[2][2] = symbol;
                break;
            case 6:
                GameBoard[2][4] = symbol;
                break;
            case 7:
                GameBoard[4][0] = symbol;
                break;
            case 8:
                GameBoard[4][2] = symbol;
                break;
            case 9:
                GameBoard[4][4] = symbol;
                break;
        }
    }

    public static String Winning() {
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);

        List topCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List botCol = Arrays.asList(3, 6, 9);

        List leftCross = Arrays.asList(1, 5, 9);
        List RightCross = Arrays.asList(3, 5, 7);

        List<List> winningCheck = new ArrayList<>();
        winningCheck.add(topRow);
        winningCheck.add(midRow);
        winningCheck.add(botRow);

        winningCheck.add(topCol);
        winningCheck.add(midCol);
        winningCheck.add(botCol);

        winningCheck.add(leftCross);
        winningCheck.add(RightCross);

        for (List i : winningCheck) {
            if (PlayerPostion.containsAll(i)) {
                return "Cong! You Win the Game";
            } else if (CPUPostion.containsAll(i)) {
                return "Sorry! CPU Win the Game";
            } else if (PlayerPostion.size() + CPUPostion.size() == 9) {
                return "Sorry! Game is draw";
            }

        }
        return "";
    }

}

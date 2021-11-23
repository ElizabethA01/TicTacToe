package com.company;

import java.util.*;

public class Main {

    // global variables
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args) {

        char [] [] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};

        int playerPos = 0;

        printGameBoard(gameBoard);

        while (true) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                try {
                    System.out.print("Enter your placement (1-9): ");
                    playerPos = scanner.nextInt();
                    while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
                        System.out.println("Position taken! Enter a correct position.");
                        playerPos = scanner.nextInt();
                    }
                        if (playerPos >= 1 && playerPos <= 9)
                            break;
                        System.out.println("Input a value between 1 and 9!");
                        continue;
                } catch (InputMismatchException e) {
                    System.out.println("This is not a value. Error: " + e.getMessage());
                    scanner.nextLine();
                    continue;

                }
            }
            placePiece(gameBoard, playerPos, "player");

            String result = checkWinner();
            if (result.length() >0) {
                System.out.println(result);
                printGameBoard(gameBoard);
                System.out.println("Game Over.");
                break;
            }

            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                cpuPos = rand.nextInt(9) + 1;
            }

            placePiece(gameBoard, cpuPos, "cpu");

            printGameBoard(gameBoard);

            result = checkWinner();
            if (result.length() >0) {
                System.out.println(result);
                printGameBoard(gameBoard);
                System.out.println("Game Over.");
                break;
            }

        }
    }

    public static String checkWinner() {
        List topRow = Arrays.asList(1,2,3);
        List middleRow = Arrays.asList(4,5,6);
        List bottomRow = Arrays.asList(7,8,9);
        List topCol = Arrays.asList(1,4,7);
        List middleCol = Arrays.asList(2,5,8);
        List bottomCol = Arrays.asList(3,6,9);
        List diagLeft = Arrays.asList(1,5,9);
        List diagRight= Arrays.asList(3,5,7);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(middleRow);
        winning.add(bottomRow);
        winning.add(topCol);
        winning.add(middleCol);
        winning.add(bottomCol);
        winning.add(diagLeft);
        winning.add(diagRight);

        for(List l: winning) {
            if(playerPositions.containsAll(l)) {
                return "Congratulations you won!";
            }
            else if (cpuPositions.containsAll(l)) {
                return "CPU win! Better luck next time";
            }
            else if (playerPositions.size() + cpuPositions.size() == 9)
                return "CAT!";
        }
        return "";
    }


    public static void printGameBoard(char [] [] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }

    }

    public static void placePiece(char [][] gameBoard, int position, String user) {
        char symbol = ' ';

        if (user.equals("player")) {
            symbol = 'X';
            playerPositions.add(position);
        }
        else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(position);
        }

        switch (position) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }
}

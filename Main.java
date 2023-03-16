import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static boolean win;

    public static void main(String[] args) {
        while (true){
            System.out.println("Please enter your number ");
            System.out.println("1-one player");
            System.out.println("2-two player");

            Scanner scanner = new Scanner(System.in) ;
            int typeGame = scanner.nextInt();

            String [] tableShow = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16"};

            ArrayList<Integer> arrayList = new ArrayList<Integer>(16);
            for (int i = 0; i < 16; i++) {
                arrayList.add(i+1);
            }


            for (int i = 0; i < 3; i++) {
                int random = (int) (Math.random() * arrayList.size());
                tableShow [arrayList.get(random)-1] = "#";
                arrayList.remove(random);
            }
            printTable(tableShow);

            int levelGame = 1;
            while (win == false && levelGame <14) {
                if (typeGame == 1){
                    if (levelGame % 2 == 1) {
                        int player = scanner.nextInt();
                        while (tableShow[player - 1] == "X" || tableShow[player - 1] == "#" || tableShow[player - 1] == "O") {
                            System.out.println("Pay attention!");
                            System.out.println("Please enter an other number");
                            player = scanner.nextInt();
                        }
                        tableShow[player - 1] = "O";
                        printTable(tableShow);
                        arrayList.remove((Integer) player);
                        win = checkBord(player - 1, tableShow);
                        if (win == true)
                            System.out.println("Congratulation!!  You won  ☺");
                    }else if (levelGame % 2 == 0) {
                        System.out.println("Computer is in the selection ");
                        int computer =  (int) (Math.random() * arrayList.size());
                        tableShow [arrayList.get(computer)-1] = "X";
                        printTable(tableShow);
                        win = checkBord(arrayList.get(computer)-1, tableShow);
                        if (win == true)
                            System.out.println("Computer won ☺☺");
                        arrayList.remove(computer);
                    }
                    levelGame++;
                }
                else if (typeGame == 2){
                    if (levelGame % 2 == 1){
                        System.out.println("Player1! please enter your number");
                        win = playerFunction(tableShow,"O");
                        if (win == true)
                            System.out.println("Congratulation Player1!!  You won  ☺");
                    }
                    else if (levelGame % 2 == 0) {
                        System.out.println("Player2! please enter your number");
                        win = playerFunction(tableShow,"X");
                        if (win == true)
                            System.out.println("Congratulation Player2!!  You won  ☺");
                    }
                    levelGame++;
                }
            }
            if (levelGame > 13 && win == false)
                System.out.println("Any one don't win");

            System.out.println("Would you like to play game again ?");
            System.out.println("1-Yes");
            System.out.println("2-No");
            int playAgain;
            playAgain = scanner.nextInt();
            if (playAgain == 2){
                break;
            }
            else
                win = false;
        }
    }

    public static boolean checkBord(int equalNumber, String[] tableShow) {
        int indexOfTS = 0;
        int row = 0;
        int column = 0;
        //boolean win = false;
        int[][] tableHide = new int[8][8];

        for (int i = 2; i < 6; i++) {
            for (int j = 2; j < 6; j++) {
                if (tableShow[indexOfTS] == "O")
                    tableHide[i][j] = 1;
                else if (tableShow[indexOfTS] == "X")
                    tableHide[i][j] = 2;
                if (indexOfTS == equalNumber) {
                    row = i;
                    column = j;
                }
                indexOfTS++;
            }
        }
        win  = checkCondition(row, column, tableHide);

        return win;
    }
    public static boolean checkCondition (int row ,int column,int [][] tableHide){
        if ((tableHide[row][column]==tableHide[row-1][column-1]) && (tableHide[row-2][column-2]==tableHide[row][column]))
            win = true;
        else if ((tableHide[row][column]==tableHide[row+1][column+1]) && (tableHide[row+2][column+2]==tableHide[row][column]))
        win = true;
        else if ((tableHide[row][column]==tableHide[row+1][column+1]) && (tableHide[row-1][column-1]==tableHide[row][column]))
            win = true;
        else if ((tableHide[row][column]==tableHide[row-1][column+1]) && (tableHide[row-2][column+2]==tableHide[row][column]))
            win = true;
        else if ((tableHide[row][column]==tableHide[row+1][column-1]) && (tableHide[row+2][column-2]==tableHide[row][column]))
            win = true;
        else if ((tableHide[row][column]==tableHide[row-1][column+1]) && (tableHide[row+1][column-1]==tableHide[row][column]))
            win = true;
        else if ((tableHide[row][column]==tableHide[row-1][column]) && (tableHide[row-2][column]==tableHide[row][column]))
            win = true;
        else if ((tableHide[row][column]==tableHide[row+1][column]) && (tableHide[row+2][column]==tableHide[row][column]))
            win = true;
        else if ((tableHide[row][column]==tableHide[row+1][column]) && (tableHide[row-1][column]==tableHide[row][column]))
            win = true;
        else if ((tableHide[row][column]==tableHide[row][column-1]) && (tableHide[row][column-2]==tableHide[row][column]))
            win = true;
        else if ((tableHide[row][column]==tableHide[row][column+1]) && (tableHide[row][column+2]==tableHide[row][column]))
            win = true;
        else if ((tableHide[row][column]==tableHide[row][column+1]) && (tableHide[row][column-1]==tableHide[row][column]))
            win = true;
        return win;

    }
    public static void printTable (String [] tableShow){
        System.out.printf("%-8s | %-8s | %-8s | %-8s %n ", tableShow[0], tableShow[1], tableShow[2], tableShow[3]);
        System.out.printf("---------------------------------------%n");
        System.out.printf("%-8s | %-8s | %-8s | %-8s %n ", tableShow[4], tableShow[5], tableShow[6], tableShow[7]);
        System.out.printf("---------------------------------------%n");
        System.out.printf("%-8s | %-8s | %-8s | %-8s %n ", tableShow[8], tableShow[9], tableShow[10], tableShow[11]);
        System.out.printf("---------------------------------------%n");
        System.out.printf("%-8s | %-8s | %-8s | %-8s %n ", tableShow[12], tableShow[13], tableShow[14], tableShow[15]);
        System.out.printf("======================================================================%n");
    }
    public static boolean playerFunction (String [] tableShow,String playerChar){
        Scanner scanner = new Scanner(System.in);
        int player = scanner.nextInt();
        while (tableShow[player - 1] == "X" || tableShow[player - 1] == "#" || tableShow[player - 1] == "O") {
            System.out.println("Pay attention!");
            System.out.println("Please enter an other number");
            player = scanner.nextInt();
        }
        tableShow[player - 1] = playerChar;
        printTable(tableShow);
        win = checkBord(player - 1, tableShow);
        return  win;
    }
}
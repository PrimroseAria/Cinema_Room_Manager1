package cinema;
import java.util.Scanner;

public class Cinema {
    public static void printRoom(String[][] newRoom, int rows, int seats){
        System.out.print("Cinema:");
        for(int k = 0; k < rows +1; k++) {
            for (int l = 0; l < seats + 1; l++) {
                if (l == 0) {
                    System.out.print("\n");
                    System.out.print(newRoom[k][l] + " ");
                } else {
                    System.out.print(newRoom[k][l] + " ");

                }
            }
        }
        System.out.print("\n");
    }

    public static void menu(){
        System.out.println("\n1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }
    public static void buyTicket(int rows, int seats, int selectedRow) {

        int frontHalf = rows / 2;

        if(rows * seats >= 60) {
            if (selectedRow <= frontHalf) {
                System.out.println("Ticket price: $10");

            } else {
                System.out.println("Ticket price: $8");
            }
        } else {
            System.out.println("Ticket price: $10");
        }
    }
    public static void statistics(int purchasedTickets, int totalTickets, int rows, int seats, int currentIncome) {

        float percentage = ((float) (purchasedTickets * 100) / totalTickets);
        int totalSeats = rows * seats;
        int totalIncome = 0;
        int frontRows= rows / 2;
        int backRows = rows - frontRows;
        int frontHalf = ((frontRows * seats) * 10) ;
        int backHalfPrice = ((backRows * seats) * 8);


        if(totalSeats >= 60) {
            totalIncome = (frontHalf + backHalfPrice);


        } else {
            totalIncome = (totalSeats * 10);
        }


        System.out.printf("Number of purchased tickets: %d%n", purchasedTickets);
        System.out.printf("Percentage: %.2f%s%n" , percentage, "%");
        System.out.printf("Current income: $%d%n", currentIncome);
        System.out.printf("Total income: $%d%n", totalIncome);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        String[][] room = new String[rows + 1][seats + 1];
        int purchasedTickets = 0;
        int totalTickets = (seats * rows);
        int frontHalf = rows / 2;
        int currentIncome = 0;


        for(int i = 0; i <= rows; i++){
            String letter = Integer.toString(i);
            for(int j = 0; j <= seats; j++){
                if(j == 0 && i == 0){
                    room[i][j] = " ";
                }
                else if(i == 0){
                    String start = Integer.toString(j);
                    room[i][j] = start;
                }
                else if(j == 0){
                    room[i][j] = letter ;
                }
                else{
                    room[i][j]= "S";
                }

            }
        }

        int num = 9;
        while(num > 0) {
            menu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    printRoom(room, rows, seats);
                    break;
                case 2:
                    int ticketLoop = 2;
                    while (ticketLoop != 0) {
                        System.out.println("\nEnter a row number:");
                        int selectedRow = scanner.nextInt();

                        System.out.println("Enter a seat number in that row:");
                        int selectedSeat = scanner.nextInt();

                        if (selectedRow > room.length -1 || selectedRow <= 0 || selectedSeat > room[room.length -1].length - 1 || selectedSeat <= 0) {
                            System.out.println("Wrong input!");
                        } else {
                            if (room[selectedRow][selectedSeat].equals("B")) {
                                System.out.println("That ticket has already been purchased!");

                            } else {

                                room[selectedRow][selectedSeat] = "B";
                                buyTicket(rows, seats, selectedRow);
                                purchasedTickets++;
                                System.out.println(purchasedTickets);
                                ticketLoop = 0;
                                if (selectedRow <= frontHalf){
                                    currentIncome = currentIncome + 10;
                                } else {
                                    currentIncome = currentIncome + 8;
                                }


                            }
                        }
                    }
                    break;
                case 3:
                    statistics(purchasedTickets, totalTickets, rows, seats, currentIncome);
                    break;
                case 0:
                    num = 0;
                    break;
            }
        }
    }
}
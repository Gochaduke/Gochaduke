package cinema;

import java.util.Scanner;

public class Cinema {

    public static int numTickets = 0;
    public static int totalIncome = 0;

    public static void ShowSeats(int seats, int rows, char[][] cinema){
        System.out.println("Cinema:\n");
        System.out.print(" ");
        for (int i = 1; i <= seats; i++)
            System.out.print(" " + i);
        System.out.print("\n");
        for (int i = 0; i < rows; ++i) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < seats; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public static void BuyTicket(int seats, int rows, char[][] cinema){
        boolean taken = false;
        boolean bought = false;
        Scanner scanner = new Scanner(System.in);
        int userRow = 0;
        int userSeat = 0;

        do{
            try {
                taken = false;
                System.out.println("Enter a row number: ");
                userRow = scanner.nextInt();
                System.out.println("Enter a seat number in that row:");
                userSeat = scanner.nextInt();
                if (cinema[userRow - 1][userSeat - 1] == 'B') {
                    System.out.println("That ticket has already been purchased!");
                    taken = true;
                }
                //taken = false;
            }
            catch(ArrayIndexOutOfBoundsException e){
                System.out.println("Wrong input!");
                taken = true;
            }

        }while(taken || (userRow > 9 || userSeat > 9));

        userRow--;
        userSeat--;
        cinema[userRow][userSeat] = 'B';
        userRow++;
        userSeat++;

        if (rows * seats < 60) {
            System.out.println("Ticket price: $10\n");
            totalIncome += 10;
        }
        else if (rows % 2 != 0) {
            if (userRow > rows / 2) {
                System.out.println("Ticket price: $8\n");
                totalIncome += 8;
            }
            else {
                System.out.println("Ticket price: $10\n");
                totalIncome += 10;
            }
        } else {
            if (userRow > rows / 2) {
                System.out.println("Ticket price: $8\n");
                totalIncome += 8;
            }
            else {
                System.out.println("Ticket price: $10\n");
                totalIncome += 10;
            }
        }
        ++numTickets;
    }

    public static void ShowStatistic(int seats, int rows, char[][] cinema){
        System.out.println("Number of purchased tickets: " + numTickets);
        System.out.printf("Percentage: %.2f", (float)(((float)numTickets / (float)(rows * seats)) * (float)100));
        System.out.println("%");
        System.out.println("Current Income " + "$" + totalIncome);
        System.out.print("Total Income: ");
        int sumseats = rows * seats;
        if (sumseats <= 60) {
            System.out.println("$" + sumseats * 10);
        } else if (rows % 2 != 0)
            System.out.println("$" + ((rows / 2) * seats * 10 + (rows / 2 + 1) * seats * 8));
        else
            System.out.println("$" + (sumseats / 2 * 10 + sumseats / 2 * 8));

    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");

        int rows = 0;
        // Calculating number of rows
        do {
            rows = scanner.nextInt();
            if (rows > 9)
                System.out.println("Invalid answer, Please enter number to 9.");
            else if (rows <= 9)
                break;
            System.out.println("Please, try again.");
        } while (rows > 4);
        System.out.println("Enter the number of seats in each row:");
        int seats = 0;

        // Calculating number of seats
        do {
            seats = scanner.nextInt();
            if (seats > 9)
                System.out.println("Invalid answer, Please enter number to 9.");
            if (seats <= 9)
                break;
            System.out.println("Please, try again.");
        } while (seats > 5);
        char[][] cinema = new char[rows][seats];

        // Initializing array
        for (int k = 0; k < rows; ++k)
            for (int l = 0; l < seats; l++)
                cinema[k][l] = 'S';

        int choice;
        do{
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    ShowSeats(seats, rows, cinema);
                    break;
                case 2:
                    BuyTicket(seats, rows, cinema);
                    break;
                case 3:
                    ShowStatistic(seats, rows, cinema);
                    break;
                case 0:
                    break;
                default:
                    break;
            }
        }while(choice != 0);

    }

}

package cinema;

import java.util.Scanner;

public class Cinema {
    public static char[][] cinemaHall;
    public static final char SEAT = 'S';
    public static final char BUSY = 'B';
    public static final Scanner scanner;
    public static final int frontHalf = 10;
    public static final int backHalf = 8;
    public static int currentIncome = 0;
    public static int currentTickets = 0;
    public static int totalIncome = 0;

    public static void main(String[] args) {
        createCinema();
        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    printCinemaHall(cinemaHall);
                    break;
                case 2:
                    takeTicket(cinemaHall);
                    break;
                case 3:
                    statistics(cinemaHall);
                    break;
                case 0:
                    return;
            }
        }
    }

    public static void createCinema() {
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();
        cinemaHall = new char[rows][seats];

        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < seats; ++j) {
                cinemaHall[i][j] = 'S';
            }
        }

    }

    public static void printMenu() {
        String details = String.format(
                "1. Show the seats%n" +
                        "2. Buy a ticket%n" +"3. Statistics%n" +"0. Exit%n");
        System.out.println(details);
    }

    public static void printCinemaHall(char[][] cinemaHall) {
        System.out.println("Cinema:");
        System.out.print(" ");

        int i;
        for(i = 1; i <= cinemaHall[0].length; ++i) {
            System.out.print(" " + i);
        }

        System.out.println();

        for(i = 0; i < cinemaHall.length; ++i) {
            System.out.print(i + 1);

            for(int j = 0; j < cinemaHall[0].length; ++j) {
                System.out.print(" " + cinemaHall[i][j]);
            }

            System.out.println();
        }
        System.out.println(" ");

    }

    public static void statistics(char[][] cinemaHall) {
        int totalPlaces = cinemaHall.length * cinemaHall[0].length;
        totalIncome = totalPlaces <= 60 ? totalPlaces * 10 : (cinemaHall.length / 2) * cinemaHall[0].length * 10 + (cinemaHall.length - cinemaHall.length / 2) * cinemaHall[0].length * 8;
        double percentage = (double) currentTickets / totalPlaces * 100;
        String statistics_text = String.format("Number of purchased tickets: %d%nPercentage: %.2f%%%nCurrent income: $%d%nTotal income: $%d%n", currentTickets, percentage, currentIncome, totalIncome);
        System.out.println(statistics_text);

    }

    public static void takeTicket(char[][] cinemaHall) {
        int totalPlaces = cinemaHall.length * cinemaHall[0].length;
        byte seatPrice;
        boolean loop = true;
        while (loop) {
            System.out.println("Enter a row number:");
            int rowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seatNumber = scanner.nextInt();
            if (rowNumber > cinemaHall.length || seatNumber > cinemaHall[0].length) {
                System.out.println("Wrong input!");
            } else if (cinemaHall[rowNumber - 1][seatNumber - 1] == 'B') {
                System.out.println("That ticket has already been purchased!");
            } else {
                loop = false;
                cinemaHall[rowNumber - 1][seatNumber - 1] = 'B';
                if (totalPlaces <= 60) {
                    seatPrice = frontHalf;
                } else {
                    int halfOfRows = cinemaHall.length / 2;
                    if (rowNumber <= halfOfRows) {
                        seatPrice = frontHalf;
                    } else {
                        seatPrice = backHalf;
                    }
                    currentIncome += seatPrice;
                    currentTickets++;
                }
                System.out.println("Ticket price: $" + seatPrice);

            }
        }

    }

    static {
        scanner = new Scanner(System.in);
    }
}

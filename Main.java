import java.util.Scanner;

public class Main {
    private static char[][] board;
    private static final int SIZE = 3;
    private static char currentPlayer;

    public static void main(String[] args) {
        board = new char[SIZE][SIZE];
        currentPlayer = 'X';
        initializeBoard();
        playGame();
    }

    // Initialise le plateau avec les numéros de case de 1 à 9
    private static void initializeBoard() {
        int counter = 1;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = (char)('0' + counter);
                counter++;
            }
        }
    }

    // Affiche le plateau de jeu
    private static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < SIZE; i++) {
            System.out.print("| ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Logique principale du jeu
    private static void playGame() {
        Scanner scanner = new Scanner(System.in);
        boolean gameEnded = false;
        while (!gameEnded) {
            printBoard();
            System.out.print("Joueur " + currentPlayer + ", entrez un numéro de case (1-9) : ");
            int move;
            try {
                move = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide, veuillez entrer un chiffre.");
                continue;
            }
            if (!makeMove(move)) {
                System.out.println("Mouvement non valide, réessayez.");
                continue;
            }
            if (checkWin()) {
                printBoard();
                System.out.println("Félicitations ! Le joueur " + currentPlayer + " a gagné !");
                gameEnded = true;
            } else if (isBoardFull()) {
                printBoard();
                System.out.println("Match nul !");
                gameEnded = true;
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
        scanner.close();
    }

    // Effectue un mouvement sur le plateau
    private static boolean makeMove(int move) {
        if (move < 1 || move > 9) {
            return false;
        }
        int row = (move - 1) / SIZE;
        int col = (move - 1) % SIZE;
        if (board[row][col] == 'X' || board[row][col] == 'O') {
            return false;
        }
        board[row][col] = currentPlayer;
        return true;
    }

    // Vérifie si le plateau est plein
    private static boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] != 'X' && board[i][j] != 'O') {
                    return false;
                }
            }
        }
        return true;
    }

    // Vérifie les conditions de victoire
    private static boolean checkWin() {
        // Vérification des lignes et colonnes
        for (int i = 0; i < SIZE; i++) {
            if ((board[i][0] == currentPlayer &&
                 board[i][1] == currentPlayer &&
                 board[i][2] == currentPlayer) ||
                (board[0][i] == currentPlayer &&
                 board[1][i] == currentPlayer &&
                 board[2][i] == currentPlayer)) {
                return true;
            }
        }
        // Vérification des diagonales
        if ((board[0][0] == currentPlayer &&
             board[1][1] == currentPlayer &&
             board[2][2] == currentPlayer) ||
            (board[0][2] == currentPlayer &&
             board[1][1] == currentPlayer &&
             board[2][0] == currentPlayer)) {
            return true;
        }
        return false;
    }
}

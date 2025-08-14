import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class SpotifyPlaylistManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> playlist = new ArrayList<>();
        Stack<ArrayList<String>> undoStack = new Stack<>();
        Stack<ArrayList<String>> redoStack = new Stack<>();

        int choice;
        do {
            System.out.println("\n==== Spotify Playlist Menu ====");
            System.out.println("1. Add song");
            System.out.println("2. Remove last song");
            System.out.println("3. Undo");
            System.out.println("4. Redo");
            System.out.println("5. View playlist");
            System.out.println("6. Exit");
            System.out.print("Choose an option (1-6): ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear input buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter song name to add: ");
                    String songToAdd = scanner.nextLine();
                    undoStack.push(new ArrayList<>(playlist)); // Save state
                    redoStack.clear(); // Clear redo history
                    playlist.add(songToAdd);
                    System.out.println("Added: " + songToAdd);
                    break;

                case 2:
                    if (!playlist.isEmpty()) {
                        undoStack.push(new ArrayList<>(playlist)); // Save state
                        redoStack.clear(); // Clear redo history
                        String removedSong = playlist.remove(playlist.size() - 1);
                        System.out.println("Removed: " + removedSong);
                    } else {
                        System.out.println("Playlist is empty. No song to remove.");
                    }
                    break;

                case 3:
                    if (!undoStack.isEmpty()) {
                        redoStack.push(new ArrayList<>(playlist)); // Save current to redo
                        playlist = undoStack.pop(); // Restore previous state
                        System.out.println("Undo successful.");
                    } else {
                        System.out.println("Nothing to undo.");
                    }
                    break;

                case 4:
                    if (!redoStack.isEmpty()) {
                        undoStack.push(new ArrayList<>(playlist)); // Save current to undo
                        playlist = redoStack.pop(); // Redo the change
                        System.out.println("Redo successful.");
                    } else {
                        System.out.println("Nothing to redo.");
                    }
                    break;

                case 5:
                    System.out.println("\nCurrent Playlist:");
                    if (playlist.isEmpty()) {
                        System.out.println("(empty)");
                    } else {
                        for (int i = 0; i < playlist.size(); i++) {
                            System.out.println((i + 1) + ". " + playlist.get(i));
                        }
                    }
                    break;

                case 6:
                    System.out.println("Exiting Spotify Playlist Manager.");
                    break;

                default:
                    System.out.println("Invalid option. Please choose 1-6.");
            }

        } while (choice != 6);

        scanner.close();
    }
}

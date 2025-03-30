import java.util.Random;
import java.util.Scanner;
import java.time.Duration;
import java.time.Instant;

public class Typing_SpeedTest {
    
    private static final String[] EASY_SENTENCES = {
        "The cat sleeps.", "A dog runs fast.", "Birds fly high.", "He eats food.", "She writes notes."
    };
    private static final String[] MEDIUM_SENTENCES = {
        "The scientist works in the laboratory.", "My friend plays football every evening.", "The quick brown fox jumps over the lazy dog.", "Typing helps improve speed and accuracy.", "Java programming is fun and useful."
    };
    private static final String[] HARD_SENTENCES = {
        "Artificial intelligence is revolutionizing modern technology and industries.",
        "Efficient typing skills can significantly enhance productivity and accuracy in various tasks.",
        "Programming in Java requires an understanding of object-oriented principles and memory management.",
        "The human brain processes information at an incredible speed, making multitasking possible.",
        "Space exploration has led to remarkable discoveries about our universe and its origins."
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Select difficulty level
        System.out.println("** Typing Speed Test **");
        System.out.println("Choose difficulty level: 1. Easy  2. Medium  3. Hard");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String[] sentencePool;
        double difficultyMultiplier;

        switch (choice) {
            case 1: // Easy
                sentencePool = EASY_SENTENCES;
                difficultyMultiplier = 1.0;
                break;
            case 2: // Medium
                sentencePool = MEDIUM_SENTENCES;
                difficultyMultiplier = 1.2;
                break;
            case 3: // Hard
                sentencePool = HARD_SENTENCES;
                difficultyMultiplier = 1.5;
                break;
            default:
                System.out.println("Invalid choice! Defaulting to Medium difficulty.");
                sentencePool = MEDIUM_SENTENCES;
                difficultyMultiplier = 1.2;
        }

        // Generate random paragraph (3 to 5 sentences)
        int numberOfSentences = random.nextInt(3) + 3;
        String paragraphToType = generateRandomParagraph(random, sentencePool, numberOfSentences);

        System.out.println("\n** Typing Speed Test **");
        System.out.println("Type the following paragraph as fast as you can:");
        System.out.println("\n\"" + paragraphToType + "\"");
        System.out.println("\nPress Enter when you are ready...");
        scanner.nextLine();

        // Start timer
        Instant startTime = Instant.now();
        System.out.println("\nStart Typing...");

        // Get user input
        String userTypedParagraph = scanner.nextLine().trim();
        Instant endTime = Instant.now();
        scanner.close();

        // Calculate stats
        long timeTaken = Duration.between(startTime, endTime).toSeconds();
        timeTaken = Math.max(timeTaken, 1);
        int totalWords = paragraphToType.split("\\s+").length;
        int typedWords = userTypedParagraph.split("\\s+").length;
        double wordsPerMinute = (typedWords / (double) timeTaken) * 60;

        // Accuracy calculation
        String[] originalWords = paragraphToType.split("\\s+");
        String[] typedWordsArray = userTypedParagraph.split("\\s+");
        int correctWords = 0;
        int minLength = Math.min(originalWords.length, typedWordsArray.length);

        for (int i = 0; i < minLength; i++) {
            if (originalWords[i].equals(typedWordsArray[i])) {
                correctWords++;
            }
        }

        double accuracy = ((double) correctWords / totalWords) * 100;
        double score = (wordsPerMinute * accuracy / 100) * difficultyMultiplier;

        // Display results
        System.out.println("\n===== RESULTS =====");
        System.out.printf("Typing Speed: %.2f WPM\n", wordsPerMinute);
        System.out.printf("Accuracy: %.2f%%\n", accuracy);
        System.out.printf("Time Taken: %d seconds\n", timeTaken);
        System.out.printf("Score: %.2f\n", score);
        System.out.println("====================");
    }

    private static String generateRandomParagraph(Random random, String[] sentencePool, int count) {
        StringBuilder paragraph = new StringBuilder();
        for (int i = 0; i < count; i++) {
            paragraph.append(sentencePool[random.nextInt(sentencePool.length)]).append(" ");
        }
        return paragraph.toString().trim();
    }
}


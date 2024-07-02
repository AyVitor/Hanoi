package NovoJava;
    import java.time.Duration;
    import java.time.Instant;
    
    public class Torredehanoi2 {
    
        
        private static long moveCount;
    
       
        public static void solveHanoi(int n, char from, char to, char aux) {
            if (n == 1) {
                moveCount++;
                return;
            }
            solveHanoi(n - 1, from, aux, to);
            moveCount++;
            solveHanoi(n - 1, aux, to, from);
        }
    
        
        public static void solveAndMeasure(int n) {
            moveCount = 0;
            Instant start = Instant.now();
            
            
            System.out.print("Processando...");
            
            Thread progressThread = new Thread(() -> {
                try {
                    while (moveCount == 0) {
                        System.out.print(".");
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            progressThread.start();
    
            solveHanoi(n, 'A', 'C', 'B');
            
            
            progressThread.interrupt();
            
    
            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
    
            long millis = timeElapsed.toMillis();
            long hours = millis / 3600000;
            long minutes = (millis % 3600000) / 60000;
            long seconds = (millis % 60000) / 1000;
            long milliseconds = millis % 1000;
    
            System.out.printf("Movimentandos os %d discos: %02d:%02d:%02d:%03d%n", n, hours, minutes, seconds, milliseconds);
            System.out.println("Total de movimentos: " + moveCount);
        }
    
        public static void main(String[] args) {
            
            int[] testCases = {1, 10, 20, 30, 40, 41}; 
    
            for (int n : testCases) {
                System.out.println("Em movimento " + n + " disco:");
                solveAndMeasure(n);
                System.out.println();
            }
        }
    }
    
    


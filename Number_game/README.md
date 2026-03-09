Number Game (Java Swing)

Features:
- Random number generation in configurable range (default 1..100)
- User guesses via GUI input
- Feedback: too high / too low / correct
- Limited attempts per round (configurable)
- Multiple rounds with quick Play Again prompts
- Scoreboard: rounds played, rounds won, total attempts used

Build and run (Windows):

1. Compile:

```bash
javac -d target/classes src/main/java/com/numbergame/NumberGame.java
```

2. Run:

```bash
java -cp target/classes com.numbergame.NumberGame
```

Notes:
- The GUI is built with Swing; run locally on a machine with a graphical environment.
- You can change the range and attempt limit from the top controls before starting a round.

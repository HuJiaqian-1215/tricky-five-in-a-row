# Tricky Five-in-a-Row

A two-player board game with a graphical user interface, written in Java (Swing) as a course assignment at ELTE (Eötvös Loránd University), Budapest.

## Rules

Classic five-in-a-row with a twist. Players X and O take turns; X starts.

- **Five in a line** (row, column or diagonal): the player wins.
- **Four in a line:** two of that player's signs are removed at random from the board.
- **Three in a line:** one of that player's signs is removed at random.
- If the board fills up with no winner, the game is a draw.

A new game can be started from the menu on a 6×6, 10×10 or 14×14 board (default: 10×10).

## Project structure

| File | Responsibility |
|---|---|
| `Main.java` | Entry point, opens the game window |
| `Game.java` | Game logic: turns, line detection, random removal, win/draw |
| `Board.java` | Board state: storing signs, checking if full, finding a player's signs |
| `Sign.java` | Enum for X, O and EMPTY |
| `GameGUI.java` | Main window and board-size menu |
| `BoardGUI.java` | Grid of buttons, click handling, result dialog |

## How to run

Requires Java (JDK 11 or newer). From the repository folder:

```bash
javac -d out *.java
java -cp out trickyfiveinrow.Main
```

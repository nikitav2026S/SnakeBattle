# SnakeBattle

Creating a Snake inspired battle game for Project 2.

# Compile and run

`java` and `javac` must be on your PATH. From the project folder, double-click a batch file (or run it in a terminal). Each one compiles `src` and starts the game.

- **Console game:** `Main.bat`
- **GUI game:** `GuiGame.bat`

# Reflection

## What changed from your specification?

- `Board` rejects a size smaller than 3 by 3.
- Bounds checking is `pointWithinBounds` instead of `isValidPoint`.

## What changed in the LinkedChain?

- Nothing.

## What happened to your unknowns?

- GUI remains a reach goal.

## What doesn't work yet?

- The snake has a bug where it updates its chain incorrectly.

# Next Steps

## What is left to finish the MVP?

- The snake needs to be fixed.
- The board needs better visuals to represent the game objects.

## Which stretch goals will you attempt?

- GUI

## What new tests will you need?

- GUI tests.
- Tests for ensuring the board prints as excpected.

## Who does what? Which team member owns each remaining item.

- Isaac - Finish board and implement GUI
- Darsh - Finish Item

# Attribution


| Section        | Team member(s) | Contribution |
| -------------- | -------------- | ------------ |
| Coordinate     | Isaac          | Author       |
| CoordinateTest | Isaac          | Author       |
| Board          | Isaac          | Author       |
| BoardTest      | Isaac          | Author       |
| Item           | Darsh          | Author       |
| Snake          | Tiffany        | Author       |
| SnakeTest      | Tiffany        | Author       |
| LinkedChain    |                |              |
| Main           | Tiffany        | Author       |


# Spec link

[https://docs.google.com/document/d/110QEpDuygqHg0_rWO-_dC-RYjn1YlGcwTabR-W-njdE/edit?usp=sharing](https://docs.google.com/document/d/110QEpDuygqHg0_rWO-_dC-RYjn1YlGcwTabR-W-njdE/edit?usp=sharing)

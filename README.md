# Simple Tic-Tac-Toe Game with Java Swing, Login, and Statistics

## Student Information
- Name     : Lucky Thierry Maulana Irham Attalla
- Student ID: 5026251150
- Class    : A

## Project Description
This project is a simple Tic-Tac-Toe game built using Java Swing GUI.
The application includes login feature, game statistics tracking, and Top 5 scorer display.
The player plays against the computer (random move AI).

## Features
- Login using database (PostgreSQL)
- Play Tic-Tac-Toe using Swing GUI
- Record wins, losses, draws, and score
- Display personal statistics
- Display Top 5 scorers using JTable

## Score System
| Result | Score |
|--------|-------|
| Win    | +10   |
| Draw   | +3    |
| Lose   | +0    |

## Database
- Database used: PostgreSQL
- Table: `players` (one table only)

## How to Run

### 1. Setup Database
- Install PostgreSQL
- Open pgAdmin or psql
- Run the file `database/schema.sql`

### 2. Configure DatabaseManager
Open `src/DatabaseManager.java` and change:
```java
private static final String PASSWORD = "isipasswordkamu"; // ganti dengan password PostgreSQL kamu
```

### 3. Add JDBC Driver
- Download `postgresql-42.x.x.jar` from https://jdbc.postgresql.org/download/
- Put it in the `lib/` folder
- In VSCode: right-click the `.jar` file → "Add to Referenced Libraries"

### 4. Run the Program
- Open `src/Main.java` in VSCode
- Click the ▶ Run button at the top right

## Class Explanation
| Class | Responsibility |
|-------|---------------|
| Main | Entry point, opens LoginFrame |
| DatabaseManager | Handles PostgreSQL JDBC connection |
| Player | Model class storing player data |
| PlayerService | Login, update statistics, get Top 5 |
| GameLogic | Game rules: move validation, winner check, computer move |
| LoginFrame | Swing window for login |
| MainMenuFrame | Swing window for main menu |
| GameFrame | Swing window for playing the game |
| StatisticsFrame | Swing window for personal statistics |
| TopScorersFrame | Swing window showing Top 5 scorers in JTable |

## Screenshots
(Add screenshots here after running the program)

## Video Link
YouTube: (Isi link YouTube kamu)

## GitHub Link
GitHub: (Isi link GitHub kamu)

# Brick Wall Breaker

A simple, fast, and colorful **Java Swing** take on the classic brick‑breaker arcade game.

This project features a blue paddle, a bouncing ball, layered bricks that require multiple hits, and an obstacle that sweeps across the screen as a moving obstacle. 

---

## ✨ Features

* **Layered bricks** with different strengths:

  * Red = 3 hits
  * Orange = 2 hits
  * Yellow = 1 hit
* **Smooth controls** with left/right arrow keys
* **Pause/Resume** with `P`
* **Game Over** overlay and **restart** with `Enter`
* **Moving obstacle** that bounces within a lane
* **Score tracking** and incremental **ball speed‑up** on brick hits
* Clean, readable code in small, focused classes

---

## 🎮 Controls

* **← / →** — Move paddle
* **P** — Pause / Resume
* **Enter** — Restart after Game Over

---

## 🧩 How it works (quick tour)

* `Main` — Creates a `JFrame`, mounts the `GamePanel`, and starts the UI thread.
* `GamePanel` — Core game loop via `javax.swing.Timer` (~60 FPS. Handles input, updates, collisions, scoring, win/lose, and rendering.
* `ObjectGame` — Base class for all on‑screen objects (position, size, color, velocity, bounds).
* `Paddle` — Player‑controlled rectangle constrained to the window width.
* `Brick` — Hit‑point based blocks with color/state transitions and a brief hit flash.
* `BirdObstacle` — Horizontal moving obstacle that bounces between `minX`/`maxX` and can collide with the ball.
* `Ball` — (Expected in your project) The moving ball with bounds, speed, and bounce logic. `GamePanel` calls `ball.update()`, `bounceY()`, `increaseSpeed()`, etc.


---


## 🚀 Build & Run

This project uses **no external dependencies**—just the JDK.

### Requirements

* **Java 17+** recommended (works with Java ≥ 14 due to modern switch syntax used in `GamePanel`).
* A terminal or IDE (IntelliJ IDEA, VS Code with Java extension, Eclipse, etc.).


### Run from an IDE

* Create a new **Java project**.
* Add the source files under the project’s source folder.
* Set **Main class** to `Main` and run.

---

## ⚙️ Key constants & quick tweaks

Open `GamePanel` to quickly tune gameplay:

* **Window size**: `setPreferredSize(new Dimension(720, 540))`
* **Paddle**: `new Paddle(300, 480, 120, 15, 720)` (x, y, width, height, windowWidth)
* **Ball**: starting position, size, speed in `Ball.java`
* **Bird**: `new BirdObstacle(100, 200, 50, 40, 3, 40, 680)`
* **Bricks**: grid layout built in the constructor; adjust rows, columns, spacing, and strength
* **Timer**: `new Timer(16, this)` (~60 FPS)

---


## 🧪 Collision & scoring notes

* Paddle/ball ball collisions invert the ball’s vertical velocity via `bounceY()`.
* Bricks decrement strength on hit; when strength reaches 0, they become invisible.
* Scoring (per `GamePanel`):

  * **+10** for destroying a brick
  * **+5** for a non‑fatal hit
* Each brick contact triggers `ball.increaseSpeed()` for a rising difficulty curve.

---

## 🧰 Troubleshooting

* **Nothing happens on arrow keys**: Make sure the panel is focusable and has focus (`setFocusable(true)`) and that the window is active.
* **Ball falls through paddle**: Ensure `Ball.getBounds()` is accurate and update order is `ball.update()` before collisions.
* **Game won’t restart**: Only `Enter` after `gameOver == true`. See `resetGame()` in `GamePanel`.

---



## 📄 License

Choose a license you’re comfortable with (e.g., MIT). Add a `LICENSE` file at the project root.

---

## 🙌 Credits

Built with pure **Java Swing**. Inspired by classic arcade brick‑breakers.

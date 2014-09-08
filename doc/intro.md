# Introduction to Game of Life

The universe of the Game of Life is an infinite two-dimensional orthogonal grid
of square cells, each of which is in one of two possible states, alive or dead.
Every cell interacts with its eight neighbours, which are the cells that are
horizontally, vertically, or diagonally adjacent. At each step in time, the
following transitions occur:

- Any live cell with fewer than two live neighbours dies, as if caused by
  under-population.
- Any live cell with two or three live neighbours lives on to the next
  generation.
- Any live cell with more than three live neighbours dies, as if by
  overcrowding.
- Any dead cell with exactly three live neighbours becomes a live cell, as if by
  reproduction.

The initial pattern constitutes the seed of the system. The first generation is
created by applying the above rules simultaneously to every cell in the
seed—births and deaths occur simultaneously, and the discrete moment at which
this happens is sometimes called a tick (in other words, each generation is a
pure function of the preceding one). The rules continue to be applied repeatedly
to create further generations.

## My view on the Game of Life

The Game of Life can probably be solved in a gazillion ways. You could base it
all on a fixed grid, loop all coordinates and do your magic, but I wanted to
focus on the active cells only and not be restricted to a fixed grid up-front.
How I view do solution is like the game mine-sweeper, but than inverse: A bomb
(active cell) radiates +1's around him. Where the radiation score on the active
cells hits 2 or 3 they live on (1st, 2nd and 3rd rule). Where the radiation on
an empty cell is exactly 3, it will become alive (4th rule).

If, for presentational reasons, it needs to fit in a fixed grid, then a simple
wrap with a modulus-height and modulus-width would do.

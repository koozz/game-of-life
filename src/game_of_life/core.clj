(ns game-of-life.core)

(defn ^:dynamic *wrap*
  "Allow dynamic rebinding of a wrapper, default no wrapping."
  [coord]
  (num coord))

(defn coord-and-neighbours
  "A single coordinate with its neighbours."
  [coord]
  (doall (for [direction (list dec identity inc)]
    (*wrap* (direction coord)))))

(defn cross-cell-coord-options
  "Cross all possible coordinates with each other."
  ([coords-x coords-y]                                      ; 2 dimensional
   (for [x coords-x y coords-y] [x y]))
  ([coords-x coords-y coords-z]                             ; 3 dimensional
   (for [x coords-x y coords-y z coords-z] [x y z])))

(defn cell-and-neighbours
  "Current cell with all its neighbours."
  [cell]
  (apply cross-cell-coord-options (for [coord cell] (coord-and-neighbours coord))))

(defn cell-neighbours
  "Current cell's neighbours."
  [cell]
  (filter #(not= cell %) (cell-and-neighbours cell)))

(defn heatmap
  "A radiation heat-map based on the active cells."
  [active-cells]
  (frequencies (mapcat cell-neighbours active-cells)))

(defn resurrect
  "Cells, either dead or alive, with 3 neighbours are resurrected."
  [radiation]
  (keys (filter #(= (val %) 3) radiation)))

(defn survive
  "Alive cells with 2 neighbours survive."
  [active-cells radiation]
  (let [survivors (keys (filter #(= 2 (val %)) radiation))]
    (for [active-cell active-cells survivor survivors
          :when (= active-cell survivor)]
      active-cell)))

(defn next-gen
  "Progresses life to the next generation."
  [active-cells]
  (let [radiation (heatmap active-cells)
        survivors (survive active-cells radiation)
        new-borns (resurrect radiation)]
    (concat survivors new-borns)))

(defn game
  "Game of life"
  [cells]
  (iterate next-gen cells))

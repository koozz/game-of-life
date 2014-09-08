(ns game-of-life.core-test
  (:require [clojure.test :refer :all]
            [game-of-life.core :refer :all]))

(deftest coord-and-neighbours-normal-test
  (testing "Coordinate and neighbours normal"
    (is (= (coord-and-neighbours 2) [1 2 3]))))

(deftest coord-and-neighbours-edge-no-wrap-test
  (testing "Coordinate and neighbours edge (no wrap)"
    (is (= (coord-and-neighbours 0) [-1 0 1]))))

; Example of a wrapper
(defn wrap10 [coord] (mod coord 10))
(deftest coord-and-neighbours-edge-wrap-10-test
  (testing "Coordinate and neighbours edge (wrap 10)"
    (is (= (binding [*wrap* wrap10] (coord-and-neighbours 0)) [9 0 1]))))

(deftest cell-and-neighbours-test-2d
  (testing "Cell and neighbours 2 dimensional"
    (is (= (cell-and-neighbours [2 2]) (list
                                         [1 1] [1 2] [1 3]
                                         [2 1] [2 2] [2 3]
                                         [3 1] [3 2] [3 3])))))
(deftest cell-neighbours-test-2d
  (testing "Cell neighbours 2 dimensional"
    (is (= (cell-neighbours [2 2]) (list
                                     [1 1] [1 2] [1 3]
                                     [2 1] [2 3]
                                     [3 1] [3 2] [3 3])))))

(deftest heatmap-test-2d
  (testing "Heatmap 2 dimensional"
    (is (= (heatmap (list [2 2] [2 4]))
           {[2 3] 2, [2 5] 1, [3 3] 2, [1 1] 1, [3 4] 1, [1 4] 1, [1 3] 2, [1 5] 1, [3 1] 1, [2 1] 1, [1 2] 1, [3 5] 1, [3 2] 1}))))

(deftest resurrect-test
  (testing "Resurrect"
    (is (= (resurrect {[2 1] 1, [2 2] 2, [2 3] 3, [2 4] 4, [2 5] 5})
           (list [2 3])))))

(deftest survive-test
  (testing "Survive"
    (is (= (survive (list [2 2] [2 3]) {[2 1] 1, [2 2] 2, [2 3] 3, [2 4] 2, [2 5] 5})
           (list [2 2])))))

(deftest next-gen-blinker1-test
  (testing "Next generation blinker 1"
    (is (= (sort (next-gen (list [2 2] [2 3] [2 4])))
           (sort (list [1 3] [2 3] [3 3]))))))

(deftest next-gen-blinker2-test
  (testing "Next generation blinker 2"
    (is (= (sort (next-gen (list [1 3] [2 3] [3 3])))
           (sort (list [2 2] [2 3] [2 4]))))))

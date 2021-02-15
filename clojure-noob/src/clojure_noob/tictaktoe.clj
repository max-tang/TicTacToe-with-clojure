(ns clojure-noob.tictaktoe)

;; 0 1 2
;; 3 4 5
;; 6 7 8
(defn initBoard []
  (vector-of :int 0 0 0 0 0 0 0 0 0))

(defn vacant? [board pos]
  (= (nth board pos)) 0)

(defn place [board pos sign]
  (assoc board pos sign))

(def combs [[0 1 2] [3 4 5] [6 7 8] [0 3 6] [1 4 7] [2 5 8] [0 4 8] [2 4 6]])

(defn allWith? [board sign comb]
  (every? true? (map = (mapv board comb) [sign sign sign])))

(defn win? [board sign]
  "Is the state of the board a winning state for 'sign"
  (def allWithSign? (partial allWith? board sign))
  (some allWithSign? combs))

(defn nextPlayerSign [sign]
  (cond
    (= sign 1) 2
    (= sign 2) 1))

(defn canWin? [board sign]
  "Is it possible for current player to win"
  (def nextSign (nextPlayerSign sign))
  (cond
    (win? board sign) true
    :else
    (reduce #(or
              (not (vacant board %))
              (not (canWin? (place board % nextSign) nextSign))))))

(ns clojure-noob.tictactoe)

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

(defn printBoard [board]
  (println (subvec board 0 3))
  (println (subvec board 3 6))
  (println (subvec board 6 9))
  (println))

(defn canWin? [board sign]
  "Is it possible for current player to win"
  (printBoard board)
  (def nextSign (nextPlayerSign sign))
  (cond
    (win? board sign) true
    :else
    (reduce (fn [flag pos]
              (and flag
                   (not
                    (and
                     (vacant? board pos)
                     (canWin? (place board pos nextSign) nextSign)))))
            true (into [] (range 0 9)))))
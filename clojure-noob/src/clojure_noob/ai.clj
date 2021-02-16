(ns clojure-noob.ai
  (:require [clojure-noob.tictactoe :refer :all]))

(defn toPrintLetter [c]
  (cond
    (= c 0) "."
    (= c 1) "x"
    (= c 2) "o"))

(defn printBoard [board]
  (println (map toPrintLetter (subvec board 0 3)))
  (println (map toPrintLetter (subvec board 3 6)))
  (println (map toPrintLetter (subvec board 6 9)))
  (println))

(defn nextPlayerSign [sign]
  (cond
    (= sign 1) 2
    (= sign 2) 1))

(defn minimax [board sign]
  (let [isVacant? (partial vacant? board)
        vacancies (filter isVacant? allPos)
        nextSign (nextPlayerSign sign)
        compF (if (= sign 2) < >)
        initScore (if (= sign 2) [-101 -1] [101 -1])]

    (cond
      (win? board nextSign) (if (= sign 2) [-100 -1] [100 -1])
      (win? board sign) (if (= sign 2) [100 -1] [-100 -1])
      (full? board) [0 -1]
      :else (reduce (fn [currentScore pos]
                      (def nextBoard2 (place board pos sign))
                      (def s (minimax nextBoard2 nextSign))
                      ;; (println (if (= sign 2) "maximize:" "minimize:") (toPrintLetter sign) [(first s) pos]) (printBoard board)
                      (if (compF (first currentScore) (first s)) [(first s) pos] currentScore))
                    initScore
                    vacancies))))

(defn aiMove [board sign]
  (def move (minimax board 2))
  (println move)
  (def aiMovePos (second move))
  (def boardAfter (place board aiMovePos 2))
  boardAfter)
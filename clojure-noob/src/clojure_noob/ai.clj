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
        nextSign (nextPlayerSign sign)]

    (cond (= sign 2) (cond
                       (win? board nextSign) [-100 -1]
                       (win? board sign) [100 -1]
                       (full? board) [0 -1]
                       :else (reduce (fn [currentScore pos]
                                       (def nextBoard2 (place board pos sign))
                                       (def s (minimax nextBoard2 nextSign))
                                       (println "maximize:" (toPrintLetter sign) [(first s) pos]) (printBoard board)
                                       (if (< (first currentScore) (first s)) [(first s) pos] currentScore))
                                     [-100 -1]
                                     vacancies))
          :else (cond
                  (win? board nextSign) [100 -1]
                  (win? board sign) [-100 -1]
                  (full? board) [0 -1]
                  :else (reduce (fn [currentScore pos]
                                  (let [nextBoard (place board pos sign)]
                                    (def s (minimax nextBoard nextSign))
                                    (println "minimize:" (toPrintLetter sign) [(first s) pos]) (printBoard board)
                                    (if (> (first currentScore) (first s)) [(first s) pos] currentScore)))
                                [100 -1]
                                vacancies)))))

(defn aiMove [board sign]
  (def move (minimax board 2))
  (println move)
  (def aiMovePos (second move))
  (def boardAfter (place board aiMovePos 2))
  boardAfter)
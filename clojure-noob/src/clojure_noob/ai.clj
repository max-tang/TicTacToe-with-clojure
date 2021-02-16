(ns clojure-noob.ai
  (:require [clojure-noob.tictactoe :refer :all]))

(defn nextPlayerSign [sign]
  (cond
    (= sign 1) 2
    (= sign 2) 1))

(defn terminateStateScore [board sign]
  (let [nextSign (nextPlayerSign sign)
        maximize? (= sign 2)]
    (cond (win? board nextSign) (if maximize? [-100 -1] [100 -1])
          (win? board sign) (if maximize? [100 -1] [-100 -1])
          (full? board) [0 -1])))

(defn minimax [board sign]
  (let [isVacant? (partial vacant? board)
        vacancies (filter isVacant? allPos)
        nextSign (nextPlayerSign sign)
        compF (if (= sign 2) < >)
        initScore (if (= sign 2) [-101 -1] [101 -1])]

    (if (gameOver? board)
      (terminateStateScore board sign)
      (reduce (fn [currentScore pos]
                (let [nextBoard (place board pos sign)
                      s (minimax nextBoard nextSign)]
                  (if (compF (first currentScore) (first s)) [(first s) pos] currentScore)))
              initScore
              vacancies))))

(defn aiMove [board sign]
  (let [move (minimax board 2)
        aiMovePos (second move)]
    (place board aiMovePos 2)))
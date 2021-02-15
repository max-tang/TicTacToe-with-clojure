(ns clojure-noob.ai
  (:require [clojure-noob.tictactoe :refer :all]))

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
    (reduce (fn [flag pos]
              (and flag
                   (cond
                     (vacant? board pos) (not (canWin? (place board pos nextSign) nextSign))
                     :else true)))
            true allPos)))

(defn bestMove [board sign]
  (def isVacant? (partial vacant? board))
  (def vacancies (filter isVacant? allPos))
  (def winningPos (filter (fn [pos]
                            (def boardAfter (place board pos sign))
                            (canWin? boardAfter sign)) vacancies))
  (cond
    (> (count winningPos) 0) (nth winningPos 0)
    (> (count vacancies) 0) (nth vacancies 0)
    :else -1))
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

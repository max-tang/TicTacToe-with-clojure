(ns clojure-noob.ai
  (:require [clojure-noob.tictactoe :refer :all]))

(defn nextPlayerSign [sign]
  (cond
    (= sign 1) 2
    (= sign 2) 1))

(defn maxScore [board sign]
  (def isVacant? (partial vacant? board))
  (def vacancies (filter isVacant? allPos))
  (def nextSign (nextPlayerSign sign))
  (cond
    (win? board sign) 100
    (full? board) 0
    :else (reduce (fn [currentScore pos]
                    (let [nextBoard1 (place board pos nextSign)]
                      (min currentScore
                           (- 0 (maxScore nextBoard1 nextSign)))))
                  100
                  vacancies)))

(defn bestMove [board sign]
  (def isVacant? (partial vacant? board))
  (def vacancies (filter isVacant? allPos))

  (def scores (map (fn [pos] [(maxScore (place board pos sign) sign) pos]) vacancies))
  ;; (println scores)

  (second
   (reduce (fn [best curr]
             (if (< (nth best 0) (nth curr 0)) curr best))
           [-200 -1]
           scores)))

(defn aiMove [board sign]
  (def aiMovePos (bestMove board sign))
  (def boardAfter (place board aiMovePos sign))
  boardAfter)
(ns clojure-noob.core
  (:require [clojure-noob.tictactoe :refer :all])
  (:require [clojure-noob.ai :refer :all])
  (:gen-class))

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

(defn humanGame []
  (loop [board (initBoard) sign 1]
    (printBoard board)
    (if (gameOver? board)
      (println "GG!")
      (let [pos (. Integer parseInt (read-line))]
        (if (not (vacant? board pos))
          (println "Cannot place there at " pos)
          (let [boardAfter (place board pos sign)]
            (recur boardAfter (nextPlayerSign sign))))))))

(defn vsAi []
  (loop [board (initBoard)]
    (printBoard board)
    (cond (gameOver? board) (println "GG!")
          :else (let [pos (. Integer parseInt (read-line))]
                  (def nextBoard
                    (if (not (vacant? board pos))
                      (let [] (println "Cannot place at " pos) board)
                      (let [boardAfter (place board pos 1)]
                        (if (gameOver? boardAfter) boardAfter (aiMove boardAfter 2)))))
                  (recur nextBoard)))))

(defn -main []
  (vsAi))

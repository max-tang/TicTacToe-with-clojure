(ns clojure-noob.core
  (:require [clojure-noob.tictactoe :refer :all])
  (:require [clojure-noob.ai :refer :all])
  (:gen-class))

(defn humanGame []
  (loop [board (initBoard) sign 1]
    (printBoard board)
    (cond (gameOver? board) (println "GG!")
          :else (let [pos (. Integer parseInt (read-line))]
                  (cond (not (vacant? board pos)) (println "Cannot place there ")
                        :else (let [boardAfter (place board pos sign)]
                                (recur boardAfter (nextPlayerSign sign))))))))

(defn -main []
  (humanGame))

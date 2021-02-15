(ns clojure-noob.tictactoe-test
  (:require [clojure.test :refer :all]
            [clojure-noob.tictactoe :refer :all]))

(deftest initBoardTest
  (testing "Should return an empty board"
    (is (= (initBoard) [0 0 0 0 0 0 0 0 0]))))

(deftest placeTest
  (testing "Placing a mark"
    (def board (initBoard))
    (is (= (place board 1 1) [0 1 0 0 0 0 0 0 0]))))

(deftest win?Test
  ;; 0 1 2
  ;; 2 1 0
  ;; 0 1 0
  (testing "A winning state"
    (def board [0 1 2 2 1 0 0 1 0])
    (is (= (win? board 1) true))))

(deftest win?Test2
  ;; 0 1 2
  ;; 2 1 0
  ;; 0 1 0
  (testing "Not a winning state for the anther player"
    (def board [0 1 2
                2 1 0
                0 1 0])
    (is (not= (win? board 2) true))))

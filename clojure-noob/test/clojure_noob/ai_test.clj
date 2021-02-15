(ns clojure-noob.ai-test
  (:require [clojure.test :refer :all]
            [clojure-noob.ai :refer :all]))

(deftest canWin?Test
  (testing "Test if current player can win after this move"
    (def board [0 1 2
                2 1 0
                0 1 0])
    (is (= (canWin? board 1) true))))

(deftest canWin?Test2
  (testing "Test if current player can win after this move"
    (def board [1 1 2
                2 1 0
                0 2 0])
    (is (= (canWin? board 2) false))))

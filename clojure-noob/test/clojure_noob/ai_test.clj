(ns clojure-noob.ai-test
  (:require [clojure.test :refer :all]
            [clojure-noob.ai :refer :all]))

(deftest aiMoveTest
  (testing "Test if current player can win after this move"
    (def board [0 0 0
                2 1 0
                0 1 0])
    (is (= (aiMove board 2)
           [0 2 0
            2 1 0
            0 1 0]))))
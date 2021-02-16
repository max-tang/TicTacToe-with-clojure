(ns clojure-noob.ai-test
  (:require [clojure.test :refer :all]
            [clojure-noob.ai :refer :all]))

;; (deftest aiMoveTest
;;   (testing "Test if current player can win after this move"
;;     (def board [0 0 0
;;                 2 1 0
;;                 0 1 0])
;;     (is (= (aiMove board 2)
;;            [0 2 0
;;             2 1 0
;;             0 1 0]))))

;; (deftest minimaxTest2
;;   (testing "Test if current player can win after this move"
;;     (def board [0 1 0
;;                 2 1 0
;;                 2 1 0])
;;     (is (= (minimax board 2 true 0)
;;            [-100 -1]))))

;; (deftest minimaxTest3
;;   (testing "Test if current player can win after this move"
;;     (def board [0 0 0
;;                 0 1 0
;;                 2 1 0])
;;     (is (= (minimax board 2 true 0)
;;            [0 1]))))

;; (deftest minimaxTest4
;;   (testing "Test if current player can win after this move"
;;     (def board [1 0 0
;;                 0 0 0
;;                 0 0 0])
;;     (is (= (minimax board 2 true 0)
;;            [0 4]))))

(deftest minimaxTest5
  (testing "Test if current player can win after this move"
    (def board [1 2 0
                0 1 0
                0 0 0])
    (is (= (minimax board 2)
           [-100 8]))))
(ns made-merits.test.service.score-calculator
  (:require [clojure.test :refer :all]
            [made-merits.service.score-calculator :refer :all]))

(deftest with-score-test
  (testing "Sum up user merit scores"
    (let [users [{:id 1, :merit-value 10} {:id 1, :merit-value 10} {:id 2, :merit-value 9}]
          expected-result [{:id 1 :score 20 :merits }
                          {:id 2 :merit-value 9}]]
      (is (= expected-result (with-score users))))))

; [:id 1 :name "Emile" :score 20 :merits [ {:name "foo" :value 10} ]

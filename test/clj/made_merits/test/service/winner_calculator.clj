(ns made-merits.test.service.winner-calculator
  (:require [clojure.test :refer :all]
            [made-merits.service.winner-calculator :refer :all]))

(deftest test-app
  (testing "Identify winner from a group of users"
    (let [users-with-scores [{:name "Fred" :score 10}
                             {:name "Sally" :score 9}]
          expected-result [{:name "Fred" :score 10 :status "winner"}
                          {:name "Sally" :score 9 :status "loser"}]]
      (println (with-winner users-with-scores))
      (is (= expected-result (with-winner users-with-scores))))))

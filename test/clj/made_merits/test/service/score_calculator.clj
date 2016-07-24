(ns made-merits.test.service.score-calculator
  (:require [clojure.test :refer :all]
            [made-merits.service.score-calculator :refer :all]))

(deftest with-score-test
  (testing "Sum up user merit scores"
    (let [users '({:user_id 1 :value 10 :name "made tea"}
                 {:user_id 1 :value 10 :name "blog post on time"}
                 {:user_id 2 :value 9 :name "made coffee"})

          expected-result '({:user_id 1 :score 20 :merits [{:value 10 :name "made tea"} {:value 10 :name "blog post on time"}]}
                           {:user_id 2 :score 9 :merits [{:value 9 :name "made coffee"}]})]

      (is (= expected-result (with-score users))))))

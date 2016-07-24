(ns made-merits.test.service.score-calculator
  (:require [clojure.test :refer :all]
            [made-merits.service.score-calculator :refer :all]))

(deftest with-score-test
  (testing "Sum up user merit scores"
    (let [users '({:value 10 :name "made tea" :first_name "Emile"}
                 {:value 10 :name "blog post on time" :first_name "Emile"}
                 {:value 9 :name "made coffee" :first_name "Alex"})

          expected-result '({:first_name "Emile" :score 20 :merits [{:value 10 :name "made tea"}
                                                                    {:value 10 :name "blog post on time"}]}
                           {:first_name "Alex" :score 9 :merits [{:value 9 :name "made coffee"}]})]

      (is (= expected-result (with-score users))))))

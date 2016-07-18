(ns made-merits.service.winner-calculator
  (:require [clojure.set :as set]))

(defn with-winner
  [users-with-scores]
  (let [max-score (apply max (map :score users-with-scores))]
    (into []
          (map (fn [user-with-score]
                 (assoc user-with-score
                        :status
                        (if (= max-score (:score user-with-score))
                          "winner"
                          "loser"))) users-with-scores))))

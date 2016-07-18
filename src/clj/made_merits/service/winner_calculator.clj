(ns made-merits.service.winner-calculator
  (:require [clojure.set :as set]
            [clojure.walk :as walk]))

(defn winners
  [users-with-scores]
  (let [max-score (apply max (map :score users-with-scores))]
    (vec (map (fn [user-with-score]
                (assoc user-with-score
                       :status
                       (if (= max-score (:score user-with-score))
                         "winner"
                         "loser")))
              users-with-scores))))

(defn with-winner
  [users-with-scores]
  (let [users-with-status (winners users-with-scores)]
    (if (not= 1
              (:winner
                (walk/keywordize-keys
                  (frequencies (map :status users-with-status)))))
      (map (fn [user-with-status] (assoc user-with-status :status "loser"))
           users-with-status)
      users-with-status)))

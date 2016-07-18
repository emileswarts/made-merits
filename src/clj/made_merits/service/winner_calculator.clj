(ns made-merits.service.winner-calculator
  (:require [clojure.set :as set]
            [clojure.walk :as walk]))

(defn- with-status
  [scored-users]
  (let [max-score (apply max (map :score scored-users))]
    (vec (map (fn [scored-user]
                (assoc scored-user
                       :status
                       (if (= max-score (:score scored-user))
                         "winner"
                         "loser")))
              scored-users))))

(defn- mark-everyone-as-a-loser
  [users-with-status]
  (map
    (fn [user-with-status]
      (assoc user-with-status :status "loser"))
    users-with-status))

(defn- more-than-one-winner?
  [users-with-status]
  (not= 1
        (:winner
          (walk/keywordize-keys
            (frequencies
              (map :status users-with-status))))))

(defn with-winner
  [scored-users]
  (let [users-with-status (with-status scored-users)]
    (if (more-than-one-winner? users-with-status)
      (mark-everyone-as-a-loser users-with-status)
      users-with-status)))


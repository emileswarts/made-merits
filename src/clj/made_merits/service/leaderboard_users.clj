(ns made-merits.service.leaderboard-users
    (:require [made-merits.service.winner-calculator :as winner-calculator]
              [made-merits.service.score-calculator :as score-calculator]))

(defn with-winner-first
  [scored-users-with-merits]
  (reverse (sort-by :score scored-users-with-merits)))

(defn presentable
  [db-users]
  (with-winner-first
    (winner-calculator/with-winner
      (score-calculator/with-score db-users))))

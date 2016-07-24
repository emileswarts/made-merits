(ns made-merits.service.leaderboard-users
    (:require [made-merits.service.winner-calculator :as winner-calculator]
              [made-merits.service.score-calculator :as score-calculator]))

  (defn presentable
    [db-users]
    (winner-calculator/with-winner (score-calculator/with-score db-users)))

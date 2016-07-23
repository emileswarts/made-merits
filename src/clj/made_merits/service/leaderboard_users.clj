(ns made-merits.service.leaderboard-users
    (:require [ made-merits.service.winner-calculator :as winner-calculator]))

  (defn presentable
    [db-users]
    (winner-calculator/with-winner (db/get-meritted-users)))

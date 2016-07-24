(ns made-merits.service.score-calculator)

(defn- unique-users
  [users-with-merits]
  (group-by :id users-with-merits))

(defn- user-with-score
  [unique-user]
  (map (assoc :score
              (map (fn [user-merits]
                (:merit-value (apply merge-with + user-merits)))
              (vals unique-user)))))

(defn with-score
  [users]
  (user-with-score (unique-users users)))

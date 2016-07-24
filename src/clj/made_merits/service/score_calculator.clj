(ns made-merits.service.score-calculator)

(defn- unique-users
  [users-with-merits]
  (group-by :user_id users-with-merits))

(defn- user-with-score
  [unique-user]
  (hash-map :user_id (:user_id (first unique-user))
            :merits (map #(dissoc % :user_id) unique-user)
            :score (->> unique-user
                     (map :value)
                     (reduce +))))

(defn with-score
  [users]
  (map user-with-score (vals (unique-users users))))

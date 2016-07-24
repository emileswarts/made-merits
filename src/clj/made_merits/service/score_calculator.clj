(ns made-merits.service.score-calculator)

(defn- unique-users
  [users-with-merits]
  (group-by :first_name users-with-merits))

(defn- user-with-score
  [unique-user]
  (hash-map :first_name (:first_name (first unique-user))
            :merits (map #(dissoc % :first_name) unique-user)
            :score (->> unique-user
                     (map :value)
                     (reduce +))))

(defn with-score
  [users]
  (map user-with-score (vals (unique-users users))))

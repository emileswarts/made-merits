(ns made-merits.service.github.boyscouting.persistor
  (:require [made-merits.service.github.boyscouting.detector :as detector]
            [made-merits.db.core :as db]))

(defn user-id
  [username]
  (db/user-id-from-github-username
    (hash-map
      :github-username username)))

(defn persist-result
  [row result]
  (db/persist-boyscouting-results!
    (hash-map
      :project (:project row)
      :pr_number (:number result)
      :user_id (:id (first (user-id (:user result)))))))

(defn persist-boyscouting-results
  [results]
  (doseq [row results]
    (doseq [result (:results row)]
      (if (empty? (db/new-boyscouting? (hash-map :pr_number (:number result))))
        (persist-result row result)))))

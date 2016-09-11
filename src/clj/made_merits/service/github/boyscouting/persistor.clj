(ns made-merits.service.github.boyscouting.persistor
  (:require [made-merits.service.github.boyscouting.detector :as detector]
            [made-merits.db.core :as db]))

(defn user-id
  [username]
  (db/user-id-from-github-username
    (hash-map
      :github-username username)))

(defn persist-boyscouting-results
  [results]
  (db/reset-boyscouting!)

  (doseq [row results]
    (doseq [result (:results row)]
      (db/persist-boyscouting-results!
        (hash-map
          :project (:project row)
          :user_id (:id (first (user-id (:user result))))
          :boyscouting_count (:boyscouting-count result))))))

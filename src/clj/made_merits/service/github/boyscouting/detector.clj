(ns made-merits.service.github.boyscouting.detector
  (:require [clojure.data.json :as json]))

(defn detect
  [project-prs-payload]
  (let [counted-result (boyscouting-count (json/read-str project-prs-payload))]
  (vec counted-result)))

(defn boyscouting-count
  [prs]
  (map
    #(hash-map :user (first %) :boyscouting-count (last %))
    (frequencies (map #(get % "login")
                      (map #(get % "user")
                           (filter #(.contains (get % "body") "🔥 ") prs))))))

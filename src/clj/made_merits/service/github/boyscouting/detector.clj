(ns made-merits.service.github.boyscouting.detector
  (:require [clojure.data.json :as json]))

(defn boyscouting-count
  [prs]
    (map
      #(hash-map
         :user (get (get % "user") "login")
         :number (get % "number"))
      (filter #(.contains (get % "body") "🔥 ") prs)))

(defn detect
  [project-prs-payload]
  (let [counted-result (boyscouting-count (json/read-str project-prs-payload))]
    (vec counted-result)))

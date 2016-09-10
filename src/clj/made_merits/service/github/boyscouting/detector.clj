(ns made-merits.service.github.boyscouting.detector
  (:require [clojure.data.json :as json]))

(defn detect
  [pr-payload]
  (let [counted-result (boyscouting-count (json/read-str pr-payload))]
  {:user "emileswarts" :boyscouting-count (if (nil? counted-result) 0 counted-result)}))

(defn boyscouting-count
  [prs]
  (first (vals (into {} (filter #(true? (key %)) (frequencies (map #(.contains (get % "body") "🔥 ") prs)))))))

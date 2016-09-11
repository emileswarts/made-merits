(ns made-merits.service.github.boyscouting.scraper
  (:require [made-merits.service.github.boyscouting.detector :as detector]
            [clj-http.client :as client]
            [clojure.string :as string]))

(def ^:private repos ["made-merits" "freestuffly"])

(defn- to-url
  [repo]
  (str "https://api.github.com/repos/emileswarts/" repo "/pulls?state=closed"))

(def ^:private urls
  (map to-url repos))

(defn results [] (map
    (fn [repo]
      (println (str "processing " repo))
      (hash-map :project repo :results (detector/detect (:body (client/get (to-url repo))))))
    repos))

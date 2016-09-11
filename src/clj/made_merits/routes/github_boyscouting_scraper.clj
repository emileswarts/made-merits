(ns made-merits.routes.github-boyscouting-scraper
  (:require [made-merits.layout :as layout]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :as response]
            [made-merits.db.core :as db]
            [made-merits.service.github.boyscouting.scraper :as scraper]
            [made-merits.service.github.boyscouting.persistor :as persistor]
            [clojure.java.io :as io]))

(defn scrape []
  (persistor/persist-boyscouting-results (scraper/results))
  (response/found "/"))

(defroutes boyscouting-routes
  (GET "/boyscouting/scrape" [] (scrape)))

(ns made-merits.handler
  (:require [compojure.core :refer [routes wrap-routes]]
            [made-merits.layout :refer [error-page]]
            [made-merits.routes.leaderboard :refer [leaderboard-routes]]
            [made-merits.routes.kudos :refer [kudos-routes]]
            [made-merits.routes.merits :refer [merits-routes]]
            [made-merits.routes.github-boyscouting-scraper :refer [boyscouting-routes]]
            [compojure.route :as route]
            [ring.middleware.basic-authentication :refer [wrap-basic-authentication]]
            [made-merits.env :refer [defaults]]
            [mount.core :as mount]
            [made-merits.middleware :as middleware]))

(mount/defstate init-app
                :start ((or (:init defaults) identity))
                :stop  ((or (:stop defaults) identity)))

(defn authenticated? [name pass]
  (and (= name (System/getenv "HTTP_USERNAME"))
       (= pass (System/getenv "HTTP_PASSWORD"))))

(def app-routes
  (routes
    (-> #'kudos-routes
        (wrap-routes middleware/wrap-formats))
    (-> #'leaderboard-routes
        (wrap-routes middleware/wrap-formats))
    (-> #'boyscouting-routes
        (wrap-routes middleware/wrap-formats))
    (-> #'merits-routes
        (wrap-routes middleware/wrap-csrf)
        (wrap-basic-authentication authenticated?)
        (wrap-routes middleware/wrap-formats))
    (route/not-found
      (:body
        (error-page {:status 404
                     :title "page not found"})))))


(defn app [] (middleware/wrap-base #'app-routes))

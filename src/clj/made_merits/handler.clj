(ns made-merits.handler
  (:require [compojure.core :refer [routes wrap-routes]]
            [made-merits.layout :refer [error-page]]
            [made-merits.routes.leaderboard :refer [leaderboard-routes]]
            [made-merits.routes.merits :refer [merits-routes]]
            [compojure.route :as route]
            [made-merits.env :refer [defaults]]
            [mount.core :as mount]
            [made-merits.middleware :as middleware]))

(mount/defstate init-app
                :start ((or (:init defaults) identity))
                :stop  ((or (:stop defaults) identity)))

(def app-routes
  (routes
    (-> #'merits-routes
        (wrap-routes middleware/wrap-csrf)
        (wrap-routes middleware/wrap-formats))
    (-> #'leaderboard-routes
        (wrap-routes middleware/wrap-formats))
    (route/not-found
      (:body
        (error-page {:status 404
                     :title "page not found"})))))


(defn app [] (middleware/wrap-base #'app-routes))

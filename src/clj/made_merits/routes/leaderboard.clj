(ns made-merits.routes.leaderboard
  (:require [made-merits.layout :as layout]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :as response]
            [made-merits.db.core :as db]
            [clojure.java.io :as io]))

(defn leaderboard []
  (layout/render
    "leaderboard.html" {:meritted_users (db/get-meritted-users)}))

(defroutes leaderboard-routes
  (GET "/" [] (leaderboard)))

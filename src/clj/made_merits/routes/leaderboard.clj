(ns made-merits.routes.leaderboard
  (:require [made-merits.layout :as layout]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :as response]
            [made-merits.db.core :as db]
            [clojure.java.io :as io]))

(defn meritted-users
  []
  (leaderboard-users/presentable (db/get-meritted-users)))

(defn leaderboard []
  (layout/render
    "leaderboard.html" {:meritted_users (meritted-users)}))

(defroutes leaderboard-routes
  (GET "/" [] (leaderboard)))

(ns made-merits.routes.merits
  (:require [made-merits.layout :as layout]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :as response]
            [made-merits.db.core :as db]
            [clojure.java.io :as io]))

(defn merits-page []
  (layout/render "merits.html" {:users (db/get-users) :merits (db/get-merits)}))

(defn add-merits [merit_id user_id]
  (db/add-merit-to-user {:merit_id merit_id :user_id user_id })
  (layout/render "merits.html" {:users (db/get-users) :merits (db/get-merits)}))

(defroutes merits-routes
  (GET "/merits" [] (merits-page))
  (POST "/merits" [merit_id user_id] (add-merits merit_id user_id)))

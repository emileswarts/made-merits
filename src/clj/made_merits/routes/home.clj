(ns made-merits.routes.home
  (:require [made-merits.layout :as layout]
            [compojure.core :refer [defroutes GET]]
            [ring.util.http-response :as response]
            [made-merits.db.core :as db]
            [clojure.java.io :as io]))

(defn home-page []
  (layout/render
    "home.html" {:docs (-> "docs/docs.md" io/resource slurp)}))

(defn merits-page []
  (layout/render "merits.html" {:users (db/get-users)}))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/merits" [] (merits-page)))

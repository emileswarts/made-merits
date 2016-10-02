(ns made-merits.routes.kudos
  (:require [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :as response]
            [made-merits.db.core :as db]
            [cheshire.core :as json]
            [clojure.string :as str]
            [clojure.java.io :as io]))

(defn create
  [params]
  (let [parsed-params (json/parse-string (slurp (:body params)))
        payload-array (str/split (get parsed-params "text") #" ")
        kudosed-user (first payload-array)
        kudosed-by (get parsed-params "username")
        reason (str/join " " (rest payload-array))]

    (if (= kudosed-user kudosed-by)
      {:status 200, :headers {}, :body "You cannot kudos yourself! This incident will be reported!"}

      (db/add-kudos {:username kudosed-user
                     :kudosed_by kudosed-by
                     :reason reason }))))

(defroutes kudos-routes
  (POST "/kudos" [params] (create)))

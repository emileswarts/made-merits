(ns made-merits.routes.kudos
  (:require [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :as response]
            [made-merits.db.core :as db]
            [cheshire.core :as json]
            [clojure.string :as str]
            [clojure.java.io :as io]))

(defn cheating-detected?
  [kudosed-user kudosed-by]
  (= kudosed-user kudosed-by))

(def cheating-detected-response
  {:status 200, :headers {}, :body "You cannot kudos yourself! This incident will be reported!"})

(defn kudosed-response
  [kudosed-by kudosed-user]
  (str kudosed-by " gave " kudosed-user " a merit! " kudosed-user " now has "
    (:kudos_count (first (db/kudos-count {:username kudosed-user}))) " merit"))

(defn create
  [params]
  (let [parsed-params (json/parse-string (slurp (:body params)))
        payload-array (str/split (get parsed-params "text") #" ")
        kudosed-user  (first payload-array)
        kudosed-by    (get parsed-params "username")
        reason        (str/join " " (rest payload-array))]

    (if (= kudosed-user "@Steve")
      {:status 200, :headers {}, :body "No merits for steve! He knows what he did..."}
    
    (if (cheating-detected? kudosed-user kudosed-by)
      cheating-detected-response

      (if (db/add-kudos {:username kudosed-user :kudosed_by kudosed-by :reason reason })
        (kudosed-response kudosed-by kudosed-user)))))

(defroutes kudos-routes
  (POST "/kudos" [params] (create)))

(ns made-merits.test.routes.kudos
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [made-merits.routes.kudos :as kudos-controller]
            [made-merits.db.core :as db]))

(defn truncate-kudos-table [f]
  (db/delete-all-kudoses)
  (f))

(use-fixtures :each truncate-kudos-table)

(deftest successful-post
  (is (= (kudos-controller/create (mock/content-type
                             (mock/request
                               :post
                               "/kudos"
                               (clojure.data.json/write-str {:username "@alex" :text "@emile so cool"} ))
                             "application/json"))
      "@alex gave @emile a merit! @emile now has 1 merit"))

  (let [kudos-result (first (db/get-kudoses))]
    (is (= "@emile" (:username kudos-result)))
    (is (= "@alex" (:kudosed_by kudos-result)))
    (is (= "so cool" (:reason kudos-result)))))

(deftest cant-kudos-yourself


  (let [kudos-result (first (db/get-kudoses))]
    (is (= (kudos-controller/create (mock/content-type
                             (mock/request
                               :post
                               "/kudos"
                               (clojure.data.json/write-str {:username "@alex" :text "@alex so cool"} ))
                             "application/json"))
           {:status 200, :headers {}, :body "You cannot kudos yourself! This incident will be reported!"}))))

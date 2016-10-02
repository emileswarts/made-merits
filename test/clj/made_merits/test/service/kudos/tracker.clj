(ns made-merits.test.routes.kudos
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [made-merits.handler :as handler]))

(deftest some-test
  (made-merits.handler (mock/request :post "/kudos"))
  {:status  200
   :headers {"content-type" "text/plain"}
   :body    "Your expected result"})


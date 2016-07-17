(ns made-merits.test.integration.merits
(:use [kerodon.core]
        [kerodon.test]
        [clojure.test])
(:require [clojure.java.io :as io]))

(deftest add-merits-to-user
  (-> (session ring-app)
      (visit "/merits")))

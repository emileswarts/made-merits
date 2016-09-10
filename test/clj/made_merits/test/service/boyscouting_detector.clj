(ns made-merits.test.service.boyscouting-detector
  (:require [clojure.test :refer :all]
            [made-merits.service.github.boyscouting.detector :refer :all]))

(deftest detected-boyscouting-test

  (testing "No match"
    (let [pr-payload "[
                        {
                          \"user\": {
                          \"login\": \"emileswarts\"
                        },
                          \"body\": \"testing github api\",
                        }
                     ]"]
      (is (= {:user "emileswarts" :boyscouting-count 0} (detect pr-payload)))))

  (testing "Single boyscouting detected"
    (let [pr-payload "[
                        {
                          \"user\": {
                          \"login\": \"emileswarts\"
                        },
                          \"body\": \"testing github api 🔥 \",
                        }
                     ]"]
      (is (= {:user "emileswarts" :boyscouting-count 1} (detect pr-payload)))))

  (testing "Multiple boyscouting detections"
    (let [pr-payload "[
                        {
                          \"user\": {
                            \"login\": \"emileswarts\"
                          },
                          \"body\": \"testing github api 🔥 \",
                        }, {
                          \"user\": {
                            \"login\": \"emileswarts\"
                          },
                          \"body\": \"Clean up documentation 🔥 \",
                        }, {
                          \"user\": {
                            \"login\": \"emileswarts\"
                          },
                          \"body\": \"Just some boring commit\",
                        },
                     ]"]
      (is (= {:user "emileswarts" :boyscouting-count 2} (detect pr-payload)))))
  )

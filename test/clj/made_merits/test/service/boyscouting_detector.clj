(ns made-merits.test.service.boyscouting-detector
  (:require [clojure.test :refer :all]
           [made-merits.service.github.boyscouting.detector :refer :all]))

(deftest detected-boyscouting-test

  (testing "No match"
    (let [pr-payload "[{
                        \"merged_at\": \"2011-01-26T19:01:12Z\",
                        \"user\": {
                          \"login\": \"emileswarts\"
                        },
                        \"body\": \"testing github api\",
                      }]"]
      (is (= [] (detect pr-payload)))))

  (testing "Single boyscouting detected"
    (let [pr-payload "[{
                        \"number\": 1347,
                        \"user\": {
                          \"login\": \"emileswarts\"
                        },
                          \"body\": \"testing github api 🔥 \",
                        }
                     ]"]
      (is (= [{:user "emileswarts" :number 1347}] (detect pr-payload)))))

  (testing "Multiple boyscouting detections"
    (let [pr-payload "[{
                          \"number\": 1347,
                          \"user\": {
                            \"login\": \"emileswarts\"
                          },
                          \"body\": \"testing github api 🔥 \",
                        }, {
                          \"number\": 1348,
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
      (is (= [{:user "emileswarts" :number 1347} {:user "emileswarts" :number 1348}] (detect pr-payload)))))

  (testing "Multiple boyscouting detections for multiple users"
    (let [pr-payload "[{
                     \"number\": 1347,
                     \"body\": \"testing github api 🔥 \",
                     \"user\": {
                        \"login\": \"emileswarts\"
                     },
                     }, {
                      \"number\": 1348,
                      \"body\": \"Clean up documentation 🔥 \",
                      \"user\": {
                        \"login\": \"chris\"
                      },
                     }, {
                      \"number\": 1349,
                      \"body\": \"Just some boring commit\",
                      \"user\": {
                          \"login\": \"emile\"
                        },
                     },
                     ]"]
      (is (= [{:user "emileswarts" :number 1347} {:user "chris" :number 1348}] (detect pr-payload)))))

  (testing "Multiple boyscouting detections for multiple users"
    (let [pr-payload "[{
                     \"number\": 1347,
                     \"body\": \"testing github api 🔥 \",
                     \"user\": {
                        \"login\": \"emileswarts\"
                      },
                     }, {
                     \"number\": 1348,
                     \"body\": \"refactor 🔥 \",
                     \"user\": {
                        \"login\": \"emileswarts\"
                      },
                     }, {
                      \"number\": 1349,
                      \"body\": \"Clean up documentation 🔥 \",
                      \"user\": {
                        \"login\": \"chris\"
                      },
                     }, {
                      \"number\": 1351,
                      \"body\": \"Just some boring commit\",
                      \"user\": {
                          \"login\": \"emile\"
                      }
                     }]"]
      (is (= [ {:user "emileswarts" :number 1347}
              {:user "emileswarts" :number 1348}
              {:user "chris" :number 1349} ] (detect pr-payload))))))

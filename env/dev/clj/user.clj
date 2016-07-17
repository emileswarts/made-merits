(ns user
  (:require [mount.core :as mount]
            made-merits.core))

(defn start []
  (mount/start-without #'made-merits.core/repl-server))

(defn stop []
  (mount/stop-except #'made-merits.core/repl-server))

(defn restart []
  (stop)
  (start))



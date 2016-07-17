(ns made-merits.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[made-merits started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[made-merits has shut down successfully]=-"))
   :middleware identity})

(ns made-merits.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [made-merits.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[made-merits started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[made-merits has shut down successfully]=-"))
   :middleware wrap-dev})

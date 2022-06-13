(ns user
  (:require
    [clojure.tools.namespace.repl :as tools-ns :refer [set-refresh-dirs]]
    [io.pedestal.http :as http]
    [dealer-api.core :as core]
    [clojure.tools.namespace.repl :refer [refresh]]))

;; For interactive development
(defonce server-atom (atom nil))

(defn go []
  (reset! server-atom
    (core/server))
  (prn "Server started on localhost")
  (prn "Enter (reset) to reload.")
  :started)

(defn halt []
  (http/stop @server-atom))

(defn reset []
  (halt)
  (refresh :after 'user/go))


(comment

  ;; after the repl is started, execute (go)
  (go)

  )

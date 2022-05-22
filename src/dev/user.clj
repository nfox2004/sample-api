(ns user
  (:require
    [clojure.tools.namespace.repl :as tools-ns :refer [set-refresh-dirs]]
    [io.pedestal.http :as http]
    [app.core :refer [service-map]]
    [monger.core :as mg]
    [monger.collection :as mc]
    [monger.credentials :as mcred])
  (:import org.bson.types.ObjectId))




(defn- refresh [& args]
  (apply tools-ns/refresh args))




(comment

  ;(require 'development)
  ;(in-ns 'development)

  (-> service-map http/create-server http/start)

  (let [host        "localhost"
        port        (Integer/parseInt "27017")
        database    "people"
        credentials (mcred/create
                      "root"
                      "admin"
                      "rootpassword")]
    (defn monger
      "Monger connection like a boss, better than before I guess."
      []
      (let [conn (mg/connect-with-credentials host port credentials)]
        (mg/get-db conn database))))

  (mc/insert-and-return (monger) "people" {:name "John" :age 30})

  (mc/find (monger) "people" {:name "John"})

  (mc/find-maps (monger) "people")

  (let [conn (mg/connect)]
    (mg/disconnect conn))

  )

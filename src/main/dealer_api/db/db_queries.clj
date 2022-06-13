(ns dealer-api.db.db-queries
  (:require [monger.collection :as mc]
            [monger.core :as mg]
            [monger.credentials :as mcred]))






#_ (let [host        "localhost"
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

#_ (defn disconnect
  (let [conn (mg/connect)]
    (mg/disconnect conn)))

(comment

  (mc/insert-and-return (monger) "people" {:name "John" :age 30})

  (mc/find (monger) "people" {:name "John"})

  (mc/find-maps (monger) "people")

  (defn shutdown
    (mg/disconnect conn))

  (let [conn (mg/connect)]
    (mg/disconnect conn)))



(ns dealer-api.drugs-service
  (:require [dealer-api.db.sql.drugs :as sql]
            [dealer-api.config :refer [db]]
            [io.pedestal.http :as http]))

(defn all-drugs [_]
  (sql/drugs db))

(defn create-drug [m]
  (if true #_(s/valid? ::drug new-drug)
    (sql/new-drug db m)
    {:error "Invalid drug"
     :input m}))



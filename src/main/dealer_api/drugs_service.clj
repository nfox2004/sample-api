(ns dealer-api.drugs-service
  (:require [dealer-api.db.sql.drugs :as sql]
            [dealer-api.config :refer [db]]
            [io.pedestal.http :as http]))

(defn all-drugs [_]
  (sql/drugs db))

(defn get-drugs [id]
  (try
    (sql/drug-by-id db {:id id})
    (catch Exception e (str "caught exception: " (.getMessage e)))))

(defn create-drug [m]
  (if true #_(s/valid? ::drug new-drug)
    (sql/new-drug db m)
    {:error "Invalid drug"
     :input m}))

;; TASK - write a spec
(defn update-drug! [{:keys [id] :as m}]
  (println "m " m)
  (if (= #{:price :name :id :availability} (-> m keys set))
    (sql/update-drug! db m)
    {:error "Drug id is required"
     :input m}))



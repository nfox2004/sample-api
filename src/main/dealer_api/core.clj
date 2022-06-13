(ns dealer-api.core
  (:require [io.pedestal.http :as http]
            [environ.core :refer [env]]
            [dealer-api.drugs-service :as ds]
            [io.pedestal.http.body-params :refer [body-params]]
            [io.pedestal.log :as log]))

(defn all-drugs [_]
  (http/json-response {:foo "bar"}))

(defn respond-hello [request]
  {:status 200
   :body   "Hello Cruel World"})

(defn all-drugs [_]
  (http/json-response (ds/all-drugs "x")))

(defn create-drug [request]
  (let [new-drug (select-keys (-> request :json-params) [:name :availability :price])
        {id :id :as new-drug} (ds/create-drug new-drug)]
    (if id
      (http/json-response {:msg "Drug created successfully."
                           :id  id})
      (assoc
        (http/json-response {:msg "Please send a valid drug."})
        :status 400))))

(def routes #{["/" :get respond-hello :route-name :hello-world]
              ["/drugs" :get all-drugs :route-name :get-drugs]
              ["/drugs" :post
               [(body-params) create-drug]
               :route-name :post-drugs]
              #_["/heroes/:hero" :get get-hero :route-name :get-hero]
              #_["/heroes" :get get-heroes :route-name :get-heroes]})


;; TASK - add put and delete and get by id


;; TASK - pull from config edn file
(def service-map {::http/routes routes                      ; Routes
                  ::http/type   :immutant
                  ::http/host   "0.0.0.0"
                  ::http/join?  false
                  ::http/port   5000})                      ; Service map

(defn server []
  (-> service-map http/create-server http/start))

(defn -main [_] (-> service-map http/create-server http/start)) ; Server Instance


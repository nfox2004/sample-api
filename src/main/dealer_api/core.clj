(ns dealer-api.core
  (:require [io.pedestal.http :as http]
            [environ.core :refer [env]]
            [dealer-api.drugs-service :as ds]
            [io.pedestal.http.body-params :refer [body-params]]
            [io.pedestal.http.route :as route]))

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

(defn get-drug [{{:keys [id]} :path-params}]
  (let [drug (ds/get-drugs (Integer/parseInt id))]
    (if drug
      (http/json-response drug)
      (assoc
        (http/json-response {:msg "Nothing found"})
        :status 404))))

(defn update-drug [{{:keys [id]} :path-params
                    :as          request}]
  (let [drug (select-keys (-> request :json-params) [:name :availability :price])
        {id :id} (ds/update-drug! (assoc drug :id (Integer/parseInt id)))]
    (if id
      (http/json-response {:msg "Drug updated successfully."
                           :id  id})
      (assoc
        (http/json-response {:msg "Please send a valid request."})
        :status 400))))

(def routes (route/expand-routes
              #{["/" :get respond-hello :route-name :hello-world]
                ["/drugs" :get all-drugs :route-name :get-drugs]
                ["/drugs" :post
                 [(body-params) create-drug]
                 :route-name :post-drugs]
                ["/drugs/:id" :put
                 [(body-params) update-drug]
                 :route-name :put-drugs]
                ["/drugs/:id" :get get-drug :route-name :get-drug]
                }))


;; TASK - delete

;; TASK - pull from config edn file
(def service-map {::http/routes routes                      ; Routes
                  ::http/type   :immutant
                  ::http/host   "0.0.0.0"
                  ::http/join?  false
                  ::http/port   5000})                      ; Service map

(defn server []
  (-> service-map http/create-server http/start))

(defn -main [_] (-> service-map http/create-server http/start)) ; Server Instance


;curl -X PUT --header 'Content-Type: application/json' \
;-d '{ "name": "Bigger Biceps", "price": 100, "availability": "10" }' \
;http://localhost:5000/drugs/11




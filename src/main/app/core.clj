(ns app.core
  (:require [io.pedestal.http :as http]
            [environ.core :refer [env]]))

(def heroes [{:name "Clark" :surname "Kent" :hero "Superman"}
             {:name "Bruce" :surname "Wayne" :hero "Batman"}
             {:name "James" :surname "Logan" :hero "Wolverine"}
             {:name "Steve" :surname "Rogers" :hero "Captain America"}
             {:name "Bruce" :surname "Banner" :hero "Hulk"}])

(defn hello-world [request]
  (let [extend      (get-in request [:query-params :extended])
        path-params (get-in request [:path-params :hero])])
  {})

(defn get-hero [{{:keys [hero]}     :path-params
                 {:keys [extended]} :query-params}]

(println ">> " extended)

  (if-let [hero (->> heroes
                  (filter #(= hero (:hero %)))
                  first)]
    {:status 200 :body (if extended hero (dissoc hero :hero))}
    {:status 404}))

(defn get-heroes [_] {:status 200 :body heroes})


(def routes #{["/" :get hello-world :route-name :hello-world]
              ["/heroes/:hero" :get get-hero :route-name :get-hero]
              ["/heroes" :get get-heroes :route-name :get-heroes]})

(def service-map {::http/routes routes                      ; Routes
                  ::http/type   :immutant
                  ::http/host   "0.0.0.0"
                  ::http/join?  false
                  ::http/port   (Integer. (or (env :port) 5000))}) ; Service map

;; TASK - use mount and move to star/stop
(defn -main [_] (-> service-map http/create-server http/start)) ; Server Instance
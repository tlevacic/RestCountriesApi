(ns app.api
  (:require [keechma.next.toolbox.ajax :refer [GET POST DELETE PUT]]
            [app.settings :as settings]
            [promesa.core :as p]))

(def default-request-config
  {:response-format :json
   :keywords? true
   :format :json})

(defn add-auth-header [req-params jwt]
  (if jwt
    (assoc-in req-params [:headers :authorization] (str "Token " jwt))
    req-params))

(defn add-params [req-params params]
  (if params
    (assoc req-params :params params)
    req-params))

(defn req-params [& {:keys [jwt data]}]
  (-> default-request-config
      (add-auth-header jwt)
      (add-params data)))

(defn process-all-countries [data]
  data)

(defn process-single-country [data]
  data)




(defn countries-get-all [search]
  (->> (GET (str "https://restcountries.eu/rest/v2/" search)
            (req-params))
       (p/map process-all-countries)))

(defn countries-get-single [search]
  (->> (GET (str "https://restcountries.eu/rest/v2/name/" search)
            (req-params))
       (p/map process-single-country)))


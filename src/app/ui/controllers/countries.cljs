(ns app.ui.controllers.countries
  (:require [keechma.next.controller :as ctrl]
            [keechma.next.controllers.pipelines :as pipelines]
            [keechma.next.controllers.entitydb :as edb]
            [keechma.next.controllers.dataloader :as dl]
            [oops.core :refer [ocall oset!]]
            [keechma.pipelines.core :as pp :refer-macros [pipeline!]]
            [app.api :as api]))

(derive :countries ::pipelines/controller)

(defn filter-data [data]
  (reduce (fn [end ele]
            (conj end (select-keys ele [:name :population :flag :capital :region])))
          []
          data))

(def pipelines
  {:keechma.on/start  (pipeline! [value {:keys [state* deps-state*] :as ctrl}]
                                 (dl/req ctrl :dataloader api/countries-get-all "all")
                                 (filter-data value)
                                 (reset! state* value))
   :search-by-country (pipeline! [value {:keys [state* deps-state*] :as ctrl}]
                                 (let [input (.. value -current -value)
                                       input# (if (empty? input) "all" (str "name/" input))]
                                   (pipeline! [value ctrl]
                                              (dl/req ctrl :dataloader api/countries-get-all input#)
                                              (filter-data value)
                                              (reset! state* value))))
   :filter-by-region (pipeline! [value {:keys [state* deps-state*] :as ctrl}]
                                (let [input value
                                      input# (if (empty? input) "all" (str "region/" input))]
                                  (pipeline! [value ctrl]
                                             (dl/req ctrl :dataloader api/countries-get-all input#)
                                             (filter-data value)
                                             (reset! state* value))))})

(defmethod ctrl/prep :countries [ctrl]
  (-> ctrl
      (pipelines/register pipelines)))

(defmethod ctrl/derive-state :countries [_ state {:keys [entitydb]}]
  state)
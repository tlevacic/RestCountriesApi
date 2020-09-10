(ns app.ui.controllers.country
  (:require [keechma.next.controller :as ctrl]
            [keechma.next.controllers.pipelines :as pipelines]
            [keechma.next.controllers.entitydb :as edb]
            [keechma.next.controllers.dataloader :as dl]
            [oops.core :refer [ocall oset!]]
            [keechma.pipelines.core :as pp :refer-macros [pipeline!]]
            [app.api :as api]))

(derive :country ::pipelines/controller)

(defn filter-data [data]
  (reduce (fn [end ele]
            (conj end (select-keys ele [:name :nativeName :subregion :languages :currencies :topLevelDomain :population :flag :capital :region])))
          []
          data))

(def pipelines
  {:keechma.on/start (pipeline! [value {:keys [state* deps-state*] :as ctrl}]
                                (let [subpage (:subpage (:router @deps-state*))]
                                  (pipeline! [value ctrl]
                                             (api/countries-get-single subpage)
                                             (reset! state* value))))})
(defmethod ctrl/prep :country [ctrl]
  (-> ctrl
      (pipelines/register pipelines)))

(defmethod ctrl/derive-state :country [_ state _]
  state)
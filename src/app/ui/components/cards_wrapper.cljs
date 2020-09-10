(ns app.ui.components.cards-wrapper
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [<> $]]
            [keechma.next.helix.core :refer [with-keechma]]
            [helix.hooks :as hooks]
            [app.ui.components.card :refer [Card]]
            [keechma.next.helix.classified :refer [defclassified]]
            [keechma.next.helix.lib :refer [defnc]]))

(defclassified CardsContainer :div "w-full flex flex-wrap justify-around mt-10")


(defnc CardsRenderer [props]
       ($ CardsContainer
          (map (fn [e]
                 ($ Card {:darkmode? (:darkmode? props) :key e :data e & props}))
               (:countries props))))


(def CardsWrapper (with-keechma CardsRenderer))


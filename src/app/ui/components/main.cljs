(ns app.ui.components.main
  (:require [helix.dom :as d]
            [keechma.next.helix.core :refer [with-keechma use-sub]]
            [keechma.next.helix.classified :refer [defclassified]]
            [helix.core :as hx :refer [$]]
            [app.ui.components.cards-wrapper :refer [CardsWrapper]]
            [app.settings :refer [regions]]
            ["semantic-ui-react" :refer (Dropdown Search)]
            [app.ui.components.search :refer [SearchWrap]]
            [keechma.next.helix.lib :refer [defnc]]))

(defclassified MainWrap :div "w-full h-full px-12 pt-8 mt-16")

(defnc MainRenderer [props]
       (let [countries (use-sub props :countries)]
         ($ MainWrap
            ($ SearchWrap {:darkmode? (:darkmode? props)})
            ($ CardsWrapper {:darkmode? (:darkmode? props) :countries countries & props}))))

(def Main (with-keechma MainRenderer))
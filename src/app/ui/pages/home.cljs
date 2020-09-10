(ns app.ui.pages.home
  "Example homepage 2 3"
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [$]]
            [keechma.next.helix.core :refer [with-keechma use-sub]]
            [keechma.next.helix.lib :refer [defnc]]
            ;;[keechma.next.helix.template :refer [defnt fill-slot configure]]
            [keechma.next.helix.classified :refer [defclassified]]
            [app.ui.components.main :refer [Main]]
            [keechma.next.controllers.router :as router]
            [app.ui.components.header :refer [Header]]
            [app.ui.components.hello :refer [Hello]]))

(defclassified HomepageWrapper :div "w-screen flex flex-col"
               (fn [{:keys [darkmode?]}] (if darkmode? " bg-veryDarkBlueDarkMode " " bg-veryLightGrayLightMode ")))

(defnc HomeRenderer [props]
       (let [darkmode? (use-sub props :darkmode)]
         ($ HomepageWrapper {:darkmode? darkmode?}
            ($ Header {:darkmode? darkmode? & props})
            ($ Main {:darkmode? darkmode? & props}))))

(def Home (with-keechma HomeRenderer))
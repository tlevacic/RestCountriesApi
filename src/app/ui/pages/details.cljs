(ns app.ui.pages.details
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [$]]
            [keechma.next.helix.core :refer [with-keechma use-sub]]
            [keechma.next.helix.lib :refer [defnc]]
            [app.ui.components.header :refer [Header]]
            [keechma.next.helix.classified :refer [defclassified]]
            [app.ui.components.main :refer [Main]]
            [app.ui.components.header :refer [Header]]
            [keechma.next.controllers.router :as router]
            [app.ui.components.hello :refer [Hello]]))

(defclassified DetailsWrapper :div "w-screen flex flex-col h-screen"
               (fn [{:keys [darkmode?]}] (if darkmode? " bg-veryDarkBlueDarkMode " " bg-veryLightGrayLightMode ")))


(defclassified MainWrap :div "w-full h-full px-12 pt-8 mt-16")

(defnc Property [props]
       (let [title (:title props)
             content (:content props)]
         (d/div {:class (str "font-bold py-2 " (if (:darkmode? props) " text-white " " text-black "))}
                (str title ": ")
                (d/span
                  {:class "text-darkGrayLightMode font-thin"}
                  content))))

(defnc DetailsRenderer [props]
       (let [country-info (first (use-sub props :country))
             darkmode? (use-sub props :darkmode)]
         ($ DetailsWrapper {:darkmode? darkmode?}
            ($ Header {:darkmode? darkmode? & props})
            ($ MainWrap
               (d/div {:class "flex flex-start"}
                      (d/button
                        {:class "bg-white cursor-pointer px-10 py-2 shadow-md rounded-lg"
                         :on-click #(router/redirect! props :router {:page "home"})}
                        "Back"))
               (d/div {:class "flex flex-row mt-12 w-full"}
                      (d/div {:class "w-5/12"
                              :style {:height "300px"}}
                             (d/img {:src (:flag country-info) :style {:maxWidth "300px" :maxHeight "200px"
                                                               :width "100%"}}))
                      (d/div {:class "w-7/12 pl-10"}
                             (d/p {:class (str "text-4xl font-bold " (if darkmode? " text-white " " text-black "))}
                                  (:name country-info))
                             (d/div {:class "flex flex-row"}
                                    (d/div
                                      ($ Property {:title "Native Name"
                                                   :content (:nativeName country-info)
                                                   :darkmode? darkmode?})
                                      ($ Property {:title "Population"
                                                   :content (:population country-info)
                                                   :darkmode? darkmode?})
                                      ($ Property {:title "Region"
                                                   :content (:region country-info)
                                                   :darkmode? darkmode?})
                                      ($ Property {:title "Sub Region"
                                                   :content (:subregion country-info)
                                                   :darkmode? darkmode?})
                                      ($ Property {:title "Capital"
                                                   :content (:capital country-info)
                                                   :darkmode? darkmode?}))
                                    (d/div
                                      ($ Property {:title "Top Level Domain"
                                                   :darkmode? darkmode?
                                                   :content (:topLevelDomain country-info)})))))))))

(def Details (with-keechma DetailsRenderer))
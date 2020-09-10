(ns app.ui.components.header
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [<> $]]
            [keechma.next.helix.core :refer [with-keechma dispatch]]
            [helix.hooks :as hooks]
            [keechma.next.helix.classified :refer [defclassified]]
            [keechma.next.helix.lib :refer [defnc]]))

(defclassified HeaderWrapper :div "fixed z-50 w-full h-16 shadow-lg items-center px-12 flex justify-between"
               (fn [{:keys [darkmode?] :as props}] (if darkmode? " bg-darkBlueDarkMode " " bg-white ")))


(defnc HeaderRenderer [props]
       ($ HeaderWrapper {:darkmode? (:darkmode? props)}
          (d/p {:class (str "font-bold " (if (:darkmode? props) " text-white " " text-black "))} "Where in the world?")
          (d/div {:class "flex justify-center items-center cursor-pointer"
                  :on-click #(dispatch props :darkmode :change-theme-mode (not (:darkmode? props)))}
            (d/i {:class (str "fa fa-moon-o mr-3 " (if (:darkmode? props) " text-white font-bold" " text-darkGrayLightMode "))})
            (d/p {:class (str "text-sm" (if (:darkmode? props) " text-white " " text-darkGrayLightMode "))}
                 "Dark Mode"))))


(def Header (with-keechma HeaderRenderer))


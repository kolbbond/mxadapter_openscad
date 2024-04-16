(ns mxadapter
  (:use [scad-clj.scad])
  (:use [scad-clj.model]))

; Dimensions 
(def fs2_dist_btw_plugs 7.00)
(def fs2_plug_width 2.40)
(def fs2_plug_thick 0.80)
(def fs2_plug_height 3.00)
(def bridge_width   8.00)
(def bridge_thick   10.00)
(def bridge_height  0.60)
(def mx_width  4.40)
(def mx_thick  0.80)
(def mx_height  4.00)
(def fs2_support_height 1.00)
(def fs2_support_thick  0.40)

; Make geometry without translation first
; (because we don't understand clojure state)
; (note the 0 suffix)

; bridge between adapter
; starts at 0
(def bridge0
  (cube bridge_thick bridge_width bridge_height))

(def fs2_support0; support for thin freestyle2 adapter
  (cube   bridge_width fs2_support_thick fs2_support_height))

(def fs2_plugs0 ; plugs into freestyle2 switch
  (union
   (translate [(/ (+ fs2_dist_btw_plugs 0) 2) 0 0]
              (cube fs2_plug_thick fs2_plug_width fs2_plug_height))
   (translate [(- (/ (+ fs2_dist_btw_plugs 0) 2)) 0 0]
              (cube fs2_plug_thick fs2_plug_width fs2_plug_height))))

(def mx_plugs0
  (union
   (cube mx_thick mx_width mx_height)
   (cube mx_width mx_thick mx_height)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; translations
; translate to connect to bridge
(def fs2_plugs
  (translate [0 0 (- (/ (+ bridge_height fs2_plug_height) 2))]
             fs2_plugs0))

(def fs2_support
  (translate [0 0 (- (/ (+ bridge_height fs2_support_height) 2))]
             fs2_support0))
(def bridge
  (union bridge0 fs2_support))

(def mx_plugs
  (translate [0 0 (/ (+ bridge_height mx_height) 2)]
             mx_plugs0))

; combine plugs
(def plugs_with_bridge
  (union fs2_plugs mx_plugs bridge))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; output to scad finally
(spit "models/mxadapter.scad"
      (write-scad plugs_with_bridge))

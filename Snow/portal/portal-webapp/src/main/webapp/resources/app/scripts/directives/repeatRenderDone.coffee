'use strict';
###
 @ngdoc directive
 @name snowApp.directive:renderDone
 @description
 # renderDone
###
angular.module('snowApp').directive('repeatRenderDone', ()->
    {
        restrict: 'A',
        priority: 10,
        link: (scope, element, attrs)->
            if scope.$last
                console.log 'Render Done!'
                !0
    }
);
